package com.graco.trueplan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.graco.trueplan.entity.StatusChamado;

public interface StatusChamadoRepository extends JpaRepository<StatusChamado, Long>{

	@Query(value = "select * from status_chamado order by id", nativeQuery = true)
	public List<StatusChamado> findAll();
	
}
