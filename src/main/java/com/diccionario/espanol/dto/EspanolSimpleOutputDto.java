package com.diccionario.espanol.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.diccionario.espanol.entity.EspanolEntity;
import com.diccionario.ingles.dto.*; 

@Data
@NoArgsConstructor
public class EspanolSimpleOutputDto {
	long id;
	String palabra;
	String descripcion;
	Date fechaAlta;
	Date fechaModificacion;
	
	public EspanolSimpleOutputDto(EspanolEntity entity)
	{
		if (entity==null)
			return;
		id=entity.id;
		palabra=entity.getPalabra();
		descripcion=entity.getDescripcion();
		fechaAlta=entity.getFecha_alta();
		fechaModificacion=entity.getFecha_modif();
	}
}
