package com.diccionario.espanol.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.espanol.dto.EspanolOutputDto;
import com.diccionario.espanol.dto.EspanolSimpleOutputDto;
import com.diccionario.espanol.service.EspanolService;

@Path("/espanol")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspanolController {
	@Inject
	EspanolService espanolService;


    @POST
	public Response  add(@Valid EspanolInputDto espanolInputDto)
	{	
		EspanolSimpleOutputDto espanolSimpleOutputDto= espanolService.add(espanolInputDto);
		return Response.accepted( espanolSimpleOutputDto).build();
	}
	
    @GET
    public List<EspanolOutputDto> getAll() {
    	return espanolService.getAll();
    }

    @GET
    @Path("/{palabra}")
    public Response  getPalabra(@PathParam("palabra") String palabra) {
        var response= espanolService.getPalabra(palabra);
        if (response.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("No encontrada palabra: "+palabra).build();
        return Response.ok( response.get()).build();
    }

    @DELETE
    @Path("/{palabra}")
    public void delete(@PathParam("palabra") String palabra) {
    	 espanolService.delete(palabra);    		
    }
    
    @PUT
    @Path("/{palabra}")
    public EspanolSimpleOutputDto update(@PathParam("palabra") String palabra,EspanolInputDto espanolInputDto ) {
    	return espanolService.update(palabra, espanolInputDto);
    }
    @DELETE
    public Response delete() {
        long nEle=espanolService.deleteAll();
        return Response.ok(nEle+" registros borrados").build();
    }
}
