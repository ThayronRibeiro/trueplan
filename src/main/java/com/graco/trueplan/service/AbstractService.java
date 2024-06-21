package com.graco.trueplan.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.graco.trueplan.exception.EntidadeNaoEncontradaException;
import com.graco.trueplan.enums.GENERO_CLASSE;
import com.graco.trueplan.repository.GenericRepository;
import com.graco.trueplan.utils.EntidadeUtils;

public class AbstractService<T, DTO, ID> {
	
	@Autowired
	private GenericRepository<T, ID> genericRepository;
	
	Class<T> classe = getGenericTypeClass();
	GENERO_CLASSE genero = EntidadeUtils.getGeneroEntidade(classe.getSimpleName());
	
	public T save(T entity) {
		return genericRepository.save(entity);
	}
	
	public List<T> findAll () {
		return genericRepository.findAll();
	}
	
	public T findById(ID id) {
		return genericRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(getErrorMessage()));
	}
	
	public void deleteById (ID id) {
		genericRepository.deleteById(id);
	}
	
	public T update(T entity) {
		return genericRepository.save(entity);
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getGenericTypeClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length > 0) {
                return (Class<T>) typeArguments[0];
            }
        }
        throw new IllegalArgumentException("A classe não está parametrizada com tipo genérico");
    }
	
	@SuppressWarnings("unchecked")
	public Class<DTO> getGenericTypeClassDTO() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments != null && typeArguments.length > 0) {
                return (Class<DTO>) typeArguments[0];
            }
        }
        throw new IllegalArgumentException("A classe não está parametrizada com tipo genérico");
    }
	
	private String getErrorMessage() {
		String mensagem = classe.getSimpleName() + " não encontrado!";
        if (genero == GENERO_CLASSE.FEMININO) {
            mensagem = classe.getSimpleName() + " não encontrada!";
        }
        return mensagem;
	}
}
