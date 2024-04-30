package com.hyvuonggia.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hyvuonggia.ecommerce.entity.Country;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
@CrossOrigin
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
