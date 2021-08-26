package com.diccionario.ingles.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;

import com.diccionario.espanol.entity.EspanolEntity;
import com.diccionario.ingles.dto.InglesInputDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class InglesEntity {

	 @Id
	 @GeneratedValue
	 long id;
	 
	 @Column(nullable = false)
	 String palabra;
	 
	 @ManyToOne (fetch = FetchType.LAZY)
	 @JoinColumn(name="palabra_espanol")
	 EspanolEntity espanolEntity;
	 
	 @Column(nullable = false)
	 Date fecha_alta = new Date();
	 Date fecha_modif;
	 
	 public InglesEntity(InglesInputDto inglesInputDto)
	 {
		 actualiza(inglesInputDto);
	 }
	 public void actualiza(InglesInputDto inglesInputDto)
	 {
		 if (inglesInputDto==null)
			 return;
		 if (inglesInputDto!=null)	palabra=inglesInputDto.getPalabra();
		 var p=EspanolEntity.find("palabra",inglesInputDto.getPalabraEspanol());
		 if ( p.count()==0)
			 throw new NotAcceptableException("Palabra no encontrada en español");
		 espanolEntity=p.firstResult();
	 }
}
