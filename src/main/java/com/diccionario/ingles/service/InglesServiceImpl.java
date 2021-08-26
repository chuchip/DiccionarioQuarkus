package com.diccionario.ingles.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.espanol.dto.EspanolOutputDto;
import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.espanol.entity.EspanolEntity;
import com.diccionario.ingles.dto.InglesInputDto;
import com.diccionario.ingles.dto.InglesOutputDto;
import com.diccionario.ingles.dto.InglesSimpleOutputDto;
import com.diccionario.ingles.entity.InglesEntity;
import com.diccionario.ingles.repository.InglesRepository;

@ApplicationScoped
public class InglesServiceImpl implements InglesService{
	@Inject 
	InglesRepository inglesRepository;
	
	@Transactional
	@Override
	public InglesSimpleOutputDto add(InglesInputDto inglesInputDto)
	{
		if (inglesRepository.count("palabra",inglesInputDto.getPalabra())>0)
			throw new NotAcceptableException("Palabra: '"+inglesInputDto.getPalabra()+ "' ya existe en ingles");
		InglesEntity inglesEntity= new InglesEntity(inglesInputDto);
		inglesRepository.persist(inglesEntity);
		return new InglesSimpleOutputDto(inglesEntity);
	}
	@Override
	public List<InglesOutputDto> getAll() {
		return inglesRepository.findAll().stream().map(e -> new InglesOutputDto(e)).collect(Collectors.toList());
	}
	@Override
	public Optional<InglesOutputDto> getPalabra(String palabra)
	{
		InglesEntity entity= (InglesEntity) inglesRepository.find("palabra",palabra).firstResult();
		if (entity==null)
			return Optional.empty();
		return Optional.of(new InglesOutputDto(entity));
	}
	@Transactional
	@Override
	public long delete(String palabra)
	{
		long elementosBorrados=inglesRepository.delete("palabra",palabra );
		if (elementosBorrados==0)
			throw new NotFoundException(String.format("No encontrada la palabra: %s", palabra));
		return elementosBorrados;
	}
	@Transactional
	@Override
	public InglesSimpleOutputDto update(String palabra, InglesInputDto inglesInputDto)
	{
		if (inglesInputDto.getPalabraEspanol()==null || inglesInputDto.getPalabraEspanol().isBlank())
			throw new NotAcceptableException("Palabra en español no puede ser nula");

		InglesEntity inglesEntity= (InglesEntity) inglesRepository.find("palabra",palabra).firstResultOptional().orElseThrow(NotFoundException::new);
		inglesEntity.actualiza(inglesInputDto);
		inglesEntity.setFecha_modif(new Date());

		return new InglesSimpleOutputDto(inglesEntity);
	}
	@Transactional
	@Override
	public long deleteAll()
	{
		long elementosBorrados=inglesRepository.deleteAll();
		return elementosBorrados;
	}
}
