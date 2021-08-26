package com.diccionario.ingles.dto;

import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.ingles.entity.InglesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class InglesOutputDto extends InglesSimpleOutputDto{
    EspanolSimpleOutputDto espanolSimpleOutputDto;

    public InglesOutputDto(InglesEntity inglesEntity)
    {
        super(inglesEntity);
        espanolSimpleOutputDto=new EspanolSimpleOutputDto(inglesEntity.getEspanolEntity());
    }
}
