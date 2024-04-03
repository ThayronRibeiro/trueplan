package com.graco.trueplan.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graco.trueplan.entity.Chamado;
import com.graco.trueplan.repository.ChamadoRepository;

@Service
@Transactional
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;

	public Chamado save(Chamado chamado) {
		chamado.setDataAbertura(LocalDateTime.now());
		chamado.setDataChamado(LocalDate.now());
		return chamadoRepository.save(chamado);
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();

	}
	
	@Transactional(readOnly = true)
	public List<Chamado> findByDataAbertura (Date date) {
		return chamadoRepository.selectByDataAbertura(date);
	}
	
	public Chamado findById (Long id) {
		return chamadoRepository.findById(id).orElse(null);
	}
	
	public List<String> listDates () {
		return chamadoRepository.selectDataAbertura();		
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T refreshById (Long id, String atributo, T novoValor) {
		Chamado chamado = chamadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));
		
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
