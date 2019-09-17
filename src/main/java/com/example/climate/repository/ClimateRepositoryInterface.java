package com.example.climate.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.climate.model.Climate;

public interface ClimateRepositoryInterface extends CrudRepository<Climate, Integer> {
	List<Climate> findById(int id );
	
}
