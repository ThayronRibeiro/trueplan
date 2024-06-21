package com.graco.trueplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graco.trueplan.entity.Categoria;
import com.graco.trueplan.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService extends AbstractService<Categoria, Categoria, Long>{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria save (Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Transactional(readOnly = true, timeout = 5000)
	public List<Categoria> findAll () {
		return categoriaRepository.findAll();		
	}
	
	public Categoria findById (Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
	
	public void deleteById (Long id) {
		categoriaRepository.deleteById(id);
	}
	
	
}
