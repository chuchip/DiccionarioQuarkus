package com.diccionario.espanol.service;

import java.util.List;
import java.util.Optional;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.espanol.dto.EspanolOutputDto;
import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.espanol.entity.EspanolEntity;

public interface EspanolService {

	EspanolSimpleOutputDto add(EspanolInputDto espanolInputDto);
	List<EspanolOutputDto> getAll();
	Optional<EspanolOutputDto> getPalabra(String palabra);
	long delete(String palabra);
	EspanolSimpleOutputDto update(String palabra,EspanolInputDto espanolInputDto);
	long deleteAll();
}
