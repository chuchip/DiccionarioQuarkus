package com.diccionario.espanol.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.espanol.dto.EspanolOutputDto;
import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.espanol.entity.EspanolEntity;
import com.diccionario.ingles.repository.InglesRepository;

@ApplicationScoped
public class EspanolServiceImpl  implements EspanolService{
	@Inject
	InglesRepository inglesRepository;

	@Transactional
	@Override
	public EspanolSimpleOutputDto add(EspanolInputDto espanolInputDto)
	{		
		long nPalabras=EspanolEntity.count("palabra",espanolInputDto.getPalabra());
		if (nPalabras>0)
			throw new WebApplicationException("Palabra ya existe en diccionario",Response.Status.CONFLICT);
		EspanolEntity espanolEntity = new EspanolEntity(espanolInputDto);
		espanolEntity.persist();
		return new EspanolSimpleOutputDto(espanolEntity);
	}
	@Override
	public List<EspanolOutputDto> getAll()
	{
		return EspanolEntity.listAll().stream().map(m -> new EspanolOutputDto((EspanolEntity )m)).collect(Collectors.toList());
	}
	@Override
	public Optional<EspanolOutputDto> getPalabra(String palabra)
	{
		EspanolEntity entity= (EspanolEntity) EspanolEntity.find("palabra",palabra).firstResult();
		if (entity==null)
			return Optional.empty();
		return Optional.of(new EspanolOutputDto(entity));
	}
	@Transactional
	@Override
	public long delete(String palabra)
	{
		long elementosBorrados=EspanolEntity.delete("palabra",palabra );
		if (elementosBorrados==0)
			throw new NotFoundException(String.format("No encontrada la palabra: %s", palabra));
		return elementosBorrados;
	}
	@Transactional
	@Override
	public long deleteAll()
	{
		inglesRepository.deleteAll();
		long elementosBorrados=EspanolEntity.deleteAll();
		return elementosBorrados;
	}
	@Transactional
	@Override
	public EspanolSimpleOutputDto update(String palabra,EspanolInputDto espanolInputDto)
	{
		if (espanolInputDto.getDescripcion()==null || espanolInputDto.getDescripcion().isBlank())
			throw new NotAcceptableException("Descripcion no puede ser nula");

		EspanolEntity espanolEntity= (EspanolEntity) EspanolEntity.find("palabra",palabra).firstResultOptional().orElseThrow(NotFoundException::new);
		espanolEntity.setFecha_modif(new Date());
		espanolEntity.setDescripcion(espanolInputDto.getDescripcion());
		//espanolEntity.persist(); // No es necesario al estar en Transactional.
		return new EspanolSimpleOutputDto(espanolEntity);
	}
}
