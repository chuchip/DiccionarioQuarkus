package com.diccionario.ingles.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.espanol.dto.EspanolOutputDto;
import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.ingles.dto.InglesInputDto;
import com.diccionario.ingles.dto.InglesOutputDto;
import com.diccionario.ingles.dto.InglesSimpleOutputDto;
import com.diccionario.ingles.service.InglesService;

import java.util.List;


@Path("/ingles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InglesController {
	@Inject 
	InglesService inglesService;
	
	@POST
	public Response  add(@Valid InglesInputDto inglesInputDto)
	{		
		return Response
				.accepted(inglesService.add(inglesInputDto))
				.build();
	}
	@GET
	public List<InglesOutputDto> getAll() {
		return inglesService.getAll();
	}

	@GET
	@Path("/{palabra}")
	public Response  getPalabra(@PathParam("palabra") String palabra) {
		var response= inglesService.getPalabra(palabra);
		if (response.isEmpty())
			return Response.status(Response.Status.NOT_FOUND).entity("No encontrada palabra: "+palabra).build();
		return Response.ok( response.get()).build();
	}

	@DELETE
	@Path("/{palabra}")
	public Response delete(@PathParam("palabra") String palabra) {
		long nElementos=inglesService.delete(palabra);
		if ( nElementos>0)
				return Response.ok().entity("Elemento borrado").build();
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("/{palabra}")
	public InglesSimpleOutputDto update(@PathParam("palabra") String palabra, InglesInputDto inglesInputDto ) {
		return inglesService.update(palabra, inglesInputDto);
	}
}
