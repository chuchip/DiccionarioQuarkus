package com.diccionario.espanol.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

import com.diccionario.espanol.entity.EspanolEntity;
import com.diccionario.ingles.dto.*;
import com.diccionario.ingles.entity.InglesEntity; 

@Data
public class EspanolOutputDto extends EspanolSimpleOutputDto {
	List<InglesSimpleOutputDto> palabrasIngles;
	
	public EspanolOutputDto(EspanolEntity entity)
	{
		super(entity);
		palabrasIngles=new ArrayList<>();
		for (InglesEntity inglesEntity: entity.getPalabrasIngles())
		{
			palabrasIngles.add(new InglesSimpleOutputDto(inglesEntity));
		}
	}
}
