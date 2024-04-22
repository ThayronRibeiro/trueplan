package com.graco.trueplan.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.graco.trueplan.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long>{

	@Query(value = "select to_char(cast(data_chamado as date), 'DD-MM-YYYY') as data from chamados group by cast(data_chamado as date)\r\n"
			+ "			order by cast(data_chamado as date) DESC", nativeQuery = true)
	public List<String> selectDataAbertura();
	
	@Query(value = "select * from chamados where cast(data_chamado as date) = cast(:date as date) order by id ASC", nativeQuery = true)
	public List<Chamado> selectByDataAbertura(Date date);
}
