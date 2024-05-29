package com.graco.trueplan.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.graco.trueplan.entity.Chamado;
import com.graco.trueplan.entity.StatusChamado;
import com.graco.trueplan.enums.PRIORIDADE;
import com.graco.trueplan.web.dto.ChamadoDTO;

@Repository
public interface ChamadoRepository extends GenericRepository<Chamado, Long> {

	@Autowired
    default DataSource getDataSource() {
        return getDataSource();
    }

	default JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	

	@Query(value = "select to_char(cast(data_chamado as date), 'DD-MM-YYYY') as data from chamados group by cast(data_chamado as date)\r\n"
			+ "			order by cast(data_chamado as date) DESC", nativeQuery = true)
	public List<String> selectDataAbertura();

	@Query(value = "select * from chamados where cast(data_chamado as date) = cast(:date as date) order by id ASC", nativeQuery = true)
	public List<Chamado> selectByDataChamado(Date date);
	
	

	public default List<ChamadoDTO> selectChamadoByDataChamado(Date dataChamado) {
		String sql = "SELECT c.id AS id, cli.nome_fantasia AS nomeCliente,c.contato AS contato,"
				+ "c.telefone1 AS telefone1, c.telefone2 AS telefone2, c.descricao_problema AS descricaoProblema,"
				+ "c.observacao AS observacao,cat.descricao AS descricaoCategoria,status.id AS status_id,"
				+ "status.descricao AS status_descricao,status.cor_background AS status_cor_background,"
				+ "status.cor_letras AS status_cor_letras,c.data_chamado AS dataChamado,c.data_abertura AS dataAbertura,"
				+ "c.data_finalizacao AS dataFinalizacao,c.data_cancelamento AS dataCancelamento,"
				+ "c.prioridade AS prioridade,tec.nome AS nomeTecnico,tec2.nome AS nomeTecnico2,usu.nome FROM"
				+ "chamados c JOIN clientes cli ON c.cliente_id = cli.id"
				+ "JOIN categorias cat ON c.categoria_id = cat.id JOIN status_chamado status ON c.status_id = status.id"
				+ "JOIN usuarios usu ON c.usuario_id = usu.id LEFT JOIN tecnicos tec ON c.tecnico_id = tec.id"
				+ "LEFT JOIN tecnicos tec2 ON c.tecnico2_id = tec2.id WHERE c.data_chamado = :dataChamado";

		return jdbcTemplate().query(sql, new ChamadoRowMapper());
	};

	public default List<ChamadoDTO> selectChamados() {
		String sql = "SELECT " + "c.id AS id, " + "cli.nome_fantasia AS nomeCliente, " + "c.contato AS contato, "
				+ "c.telefone1 AS telefone1, " + "c.telefone2 AS telefone2, "
				+ "c.descricao_problema AS descricaoProblema, " + "c.observacao AS observacao, "
				+ "cat.descricao AS descricaoCategoria, " + "status.id AS status_id, "
				+ "status.descricao AS status_descricao, " + "status.cor_background AS status_cor_background, "
				+ "status.cor_letras AS status_cor_letras, " + "c.data_chamado AS dataChamado, "
				+ "c.data_abertura AS dataAbertura, " + "c.data_finalizacao AS dataFinalizacao, "
				+ "c.data_cancelamento AS dataCancelamento, " + "c.prioridade AS prioridade, "
				+ "tec.nome AS nomeTecnico, " + "tec2.nome AS nomeTecnico2, " + "usu.nome AS nomeUsuario "
				+ "FROM chamados c " + "JOIN clientes cli ON c.cliente_id = cli.id "
				+ "JOIN categorias cat ON c.categoria_id = cat.id "
				+ "JOIN status_chamado status ON c.status_id = status.id "
				+ "JOIN usuarios usu ON c.usuario_id = usu.id " + "LEFT JOIN tecnicos tec ON c.tecnico_id = tec.id "
				+ "LEFT JOIN tecnicos tec2 ON c.tecnico2_id = tec2.id";

		return jdbcTemplate().query(sql, new ChamadoRowMapper());

	};

}

class ChamadoRowMapper implements RowMapper<ChamadoDTO> {
	@Override
	public ChamadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChamadoDTO dto = new ChamadoDTO();
		dto.setId(rs.getLong("id"));
		dto.setNomeCliente(rs.getString("nome_cliente"));
		dto.setContato(rs.getString("contato"));
		dto.setTelefone1(rs.getString("telefone1"));
		dto.setTelefone2(rs.getString("telefone2"));
		dto.setDescricaoProblema(rs.getString("descricao_problema"));
		dto.setObservacao(rs.getString("observacao"));
		dto.setDescricaoCategoria(rs.getString("descricao_categoria"));

		StatusChamado status = new StatusChamado();
		status.setId(rs.getLong("status_id"));
		status.setDescricao(rs.getString("status_descricao"));
		status.setCorBackground(rs.getString("cor_background"));
		status.setCorLetras(rs.getString("cor_letras"));
		dto.setStatus(status);

		dto.setDataChamado(rs.getDate("data_chamado").toLocalDate());
		dto.setDataAbertura(rs.getTimestamp("data_abertura").toLocalDateTime());
		dto.setDataFinalizacao(
				rs.getTimestamp("data_finalizacao") != null ? rs.getTimestamp("data_finalizacao").toLocalDateTime()
						: null);
		dto.setDataCancelamento(
				rs.getTimestamp("data_cancelamento") != null ? rs.getTimestamp("data_cancelamento").toLocalDateTime()
						: null);
		dto.setPrioridade(PRIORIDADE.valueOf(rs.getString("prioridade")));
		dto.setNomeTecnico(rs.getString("nome_tecnico"));
		dto.setNomeTecnico2(rs.getString("nome_tecnico2"));
		dto.setNomeUsuario(rs.getString("usuario_criacao"));
		return dto;
	}

}