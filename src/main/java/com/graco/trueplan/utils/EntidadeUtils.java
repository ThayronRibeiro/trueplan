package com.graco.trueplan.utils;

import java.util.HashMap;
import java.util.Map;

import com.graco.trueplan.enums.GENERO_CLASSE;


public class EntidadeUtils {

	private static final Map<String, GENERO_CLASSE> generoEntidadeMap = new HashMap<>();

    static {
    	generoEntidadeMap.put("Cargo", GENERO_CLASSE.MASCULINO);
    	generoEntidadeMap.put("Categoria", GENERO_CLASSE.FEMININO);
        generoEntidadeMap.put("Chamado", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Cliente", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Comentario", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Configuracao", GENERO_CLASSE.FEMININO);
        generoEntidadeMap.put("Empresa", GENERO_CLASSE.FEMININO);
        generoEntidadeMap.put("Endereco", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Historico", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("StatusChamado", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Tecnico", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Todos", GENERO_CLASSE.MASCULINO);
        generoEntidadeMap.put("Usuario", GENERO_CLASSE.MASCULINO);
    }
   

    public static GENERO_CLASSE getGeneroEntidade(String nomeClasse) {
        return generoEntidadeMap.getOrDefault(nomeClasse, GENERO_CLASSE.MASCULINO); // Default para MASCULINO
    }
}
