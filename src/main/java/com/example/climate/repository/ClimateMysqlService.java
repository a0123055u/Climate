package com.example.climate.repository;

import java.util.List;

import javax.persistence.Column;

public interface ClimateMysqlService {
	public List<String> getAllClimateParameters(int id);
	
	public void addClimateParameter(String latitude,String longitute, String time, String summary, String ozone, String temprature);
	
}
