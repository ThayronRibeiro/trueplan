package com.graco.trueplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.graco.trueplan.repository.GenericRepository;

public class GenericService<T, ID> {
	
	@Autowired
	private GenericRepository<T, ID> genericRepository;
	
	public T save(T entity) {
		return genericRepository.save(entity);
	}
	
	public List<T> findAll () {
		return genericRepository.findAll();
	}
	
	public T findById(ID id) {
		return genericRepository.findById(id).orElse(null);
	}
	
	public void deleteById (ID id) {
		genericRepository.deleteById(id);
	}
	
	public T update(T entity) {
		return genericRepository.save(entity);
	}
}
