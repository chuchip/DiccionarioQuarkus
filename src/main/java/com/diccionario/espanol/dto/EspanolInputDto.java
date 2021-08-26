package com.diccionario.espanol.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EspanolInputDto {
	@NotNull
	String palabra;
	@NotNull
	String descripcion;
}
