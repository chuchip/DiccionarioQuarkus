package com.diccionario.ingles.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.diccionario.ingles.entity.InglesEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class InglesRepository  implements PanacheRepository<InglesEntity>
{
	   public Optional<InglesEntity> findByName(String name){
	       return find("name", name).firstResultOptional();
	   }
}
