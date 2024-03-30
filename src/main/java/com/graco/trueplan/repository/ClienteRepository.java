package com.graco.trueplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graco.trueplan.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
