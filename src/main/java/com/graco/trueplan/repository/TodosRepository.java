package com.graco.trueplan.repository;

import org.springframework.stereotype.Repository;

import com.graco.trueplan.entity.Todos;

@Repository
public interface TodosRepository extends GenericRepository<Todos, Long>{

}
