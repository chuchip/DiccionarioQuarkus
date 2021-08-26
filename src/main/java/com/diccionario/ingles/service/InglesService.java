package com.diccionario.ingles.service;

import com.diccionario.ingles.dto.InglesInputDto;
import com.diccionario.ingles.dto.InglesOutputDto;
import com.diccionario.ingles.dto.InglesSimpleOutputDto;

import java.util.List;
import java.util.Optional;

public interface InglesService {
	InglesSimpleOutputDto add(InglesInputDto inglesInputDto);
	List<InglesOutputDto> getAll();
	Optional<InglesOutputDto> getPalabra(String palabra);
	long delete(String palabra);
	InglesSimpleOutputDto update(String palabra, InglesInputDto inglesInputDto);
	long deleteAll();
}
