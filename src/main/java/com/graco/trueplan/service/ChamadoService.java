package com.graco.trueplan.service;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graco.trueplan.entity.Chamado;
import com.graco.trueplan.entity.StatusChamado;
import com.graco.trueplan.enums.PRIORIDADE;
import com.graco.trueplan.repository.ChamadoRepository;
import com.graco.trueplan.web.dto.ChamadoDTO;

@Service
@Transactional
public class ChamadoService extends GenericService<Chamado, Long> {

	@Autowired
	private ChamadoRepository chamadoRepository;

	@Autowired
	DataSource dataSource;

	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	public Chamado save(Chamado chamado) {
		chamado.setDataAbertura(LocalDateTime.now());
		chamado.setDataChamado(LocalDate.now());
		return chamadoRepository.save(chamado);
	}

	@Transactional
	public Chamado update(Chamado chamado) {
		return chamadoRepository.save(chamado);
	}

	@Transactional
	public Chamado reagendar(Chamado chamado) {

		Chamado chamadoEstadoAtual = chamadoRepository.findById(chamado.getId()).orElse(null);

		if (chamadoEstadoAtual != null) {
			if (!chamadoEstadoAtual.getDataChamado().isBefore(chamado.getDataChamado())) {
				throw new RuntimeException("Nova data tem que ser maior que a data atual!");
			}
		}

		return chamadoRepository.save(chamado);
	}

	public List<ChamadoDTO> listarChamadoByDataChamado(Date dataChamado) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = dateFormat.format(dataChamado);
		
		String sql = "SELECT c.id AS id, cli.nome_fantasia AS nome_cliente, c.contato AS contato, "
				+ "c.telefone1 AS telefone1, c.telefone2 AS telefone2, c.descricao_problema AS descricao_problema, "
				+ "c.observacao AS observacao, cat.descricao AS descricao_categoria, status.id AS status_id, "
				+ "status.descricao AS status_descricao, status.cor_background AS cor_background, "
				+ "status.cor_letras AS cor_letras,c.data_chamado AS data_chamado,c.data_abertura AS data_abertura, "
				+ "c.data_finalizacao AS data_finalizacao,c.data_cancelamento AS data_cancelamento, "
				+ "c.prioridade AS prioridade,tec.nome AS nome_tecnico,tec2.nome AS nome_tecnico2,usu.nome "
				+ "FROM chamados c JOIN clientes cli ON c.cliente_id = cli.id "
				+ "JOIN categorias cat ON c.categoria_id = cat.id JOIN status_chamado status ON c.status_id = status.id "
				+ "JOIN usuarios usu ON c.usuario_id = usu.id LEFT JOIN tecnicos tec ON c.tecnico_id = tec.id "
				+ "LEFT JOIN tecnicos tec2 ON c.tecnico2_id = tec2.id WHERE c.data_chamado = " + "'"+ dataFormatada+ "'";

		return jdbcTemplate().query(sql, new ChamadoRowMapper());
	}

		public List<ChamadoDTO> listarChamados() {
			String sql = "SELECT c.id AS id, cli.nome_fantasia AS nome_cliente, c.contato AS contato, "
					+ "c.telefone1 AS telefone1, c.telefone2 AS telefone2, c.descricao_problema AS descricao_problema, "
					+ "c.observacao AS observacao, cat.descricao AS descricao_categoria, status.id AS status_id, "
					+ "status.descricao AS status_descricao, status.cor_background AS cor_background, "
					+ "status.cor_letras AS cor_letras,c.data_chamado AS data_chamado,c.data_abertura AS data_abertura, "
					+ "c.data_finalizacao AS data_finalizacao,c.data_cancelamento AS data_cancelamento, "
					+ "c.prioridade AS prioridade,tec.nome AS nome_tecnico,tec2.nome AS nome_tecnico2,usu.nome "
					+ "FROM chamados c JOIN clientes cli ON c.cliente_id = cli.id "
					+ "JOIN categorias cat ON c.categoria_id = cat.id JOIN status_chamado status ON c.status_id = status.id "
					+ "JOIN usuarios usu ON c.usuario_id = usu.id LEFT JOIN tecnicos tec ON c.tecnico_id = tec.id "
					+ "LEFT JOIN tecnicos tec2 ON c.tecnico2_id = tec2.id";

			return jdbcTemplate().query(sql, new ChamadoRowMapper());
		}

		@Transactional(readOnly = true)
		public List<Chamado> findByDataAbertura(Date date) {
			return chamadoRepository.selectByDataAbertura(date);
		}

		@Transactional(readOnly = true)
		public List<String> listDates() {
			return chamadoRepository.selectDataAbertura();
		}

		@SuppressWarnings("unchecked")
		public <T> T refreshById(Long id, String atributo, T novoValor) {
			Chamado chamado = chamadoRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

			try {
				Field field = chamado.getClass().getDeclaredField(atributo);
				field.setAccessible(true);
				field.set(chamado, novoValor);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				throw new RuntimeException("Erro ao acessar o atributo", e);
			}

			return (T) chamadoRepository.save(chamado);
		}
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
		dto.setDataCancelamento(rs.getTimestamp("data_cancelamento") != null
				? rs.getTimestamp("data_cancelamento").toLocalDateTime()
				: null);
		dto.setPrioridade(PRIORIDADE.fromValue(rs.getInt("prioridade")));
		dto.setNomeTecnico(rs.getString("nome_tecnico"));
		dto.setNomeTecnico2(rs.getString("nome_tecnico2"));
		dto.setUsuarioCriacao(rs.getString("nome"));
		return dto;
	}

}
