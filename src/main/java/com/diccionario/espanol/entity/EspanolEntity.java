package com.diccionario.espanol.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.diccionario.espanol.dto.EspanolInputDto;
import com.diccionario.ingles.entity.InglesEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class EspanolEntity  extends PanacheEntity {

	 @Column(nullable = false)
	 String palabra;
	 String descripcion;
	 @Column(nullable = false)
	 Date fecha_alta = new Date();
	 Date fecha_modif;
	 
	 @OneToMany(mappedBy = "espanolEntity",  cascade = CascadeType.ALL,
		        orphanRemoval = true) 
	 List<InglesEntity> palabrasIngles;
	 
	 public EspanolEntity(EspanolInputDto input)
	 {
		 actualiza(input);
	 }
	 public void actualiza(EspanolInputDto input)
	 {
		 if (input==null)
			 return;
		 if (input.getPalabra()!=null) palabra=input.getPalabra();
		 if (input.getDescripcion()!=null) descripcion=input.getDescripcion();
		 
	 }
}
