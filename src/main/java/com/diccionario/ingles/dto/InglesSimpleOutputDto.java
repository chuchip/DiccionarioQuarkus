package com.diccionario.ingles.dto;

import java.util.Date;

import com.diccionario.ingles.entity.InglesEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InglesSimpleOutputDto {
	long id;
	String palabra;
	String palabraEspanol;
	Date fechaAlta;
	Date fechaModificacion;
	
	public InglesSimpleOutputDto(InglesEntity inglesEntity)
	{
		if (inglesEntity==null)
			return;
		id=inglesEntity.getId();
		palabra=inglesEntity.getPalabra();
		palabraEspanol=inglesEntity.getEspanolEntity().getPalabra();
		fechaAlta=inglesEntity.getFecha_alta();
		fechaModificacion=inglesEntity.getFecha_modif();
	}
}
