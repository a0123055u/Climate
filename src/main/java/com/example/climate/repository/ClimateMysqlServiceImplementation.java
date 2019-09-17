package com.example.climate.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.climate.model.Climate;
@Service
public class ClimateMysqlServiceImplementation implements ClimateMysqlService {
	
	 @Autowired
	    private ClimateRepositoryInterface climateRepositoryInterface;

	@Override
	public List<String> getAllClimateParameters(int id) {
		List<String> result = new ArrayList<String>();
		List<Climate> climateParams = climateRepositoryInterface.findById(id);
        for (Climate climate : climateParams) {
        	result.add(climate.getLatitude());
        	result.add(climate.getLongitute());
        	result.add(climate.getId());
        	result.add(climate.getTemperature());
        	result.add(climate.getOzone());
        	result.add(climate.getSummary());        	
        	result.add(climate.getTime());
        	
            result.add(climate.getApparentTemperature());
        }
        return result;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClimateParameter(String latitude, String longitute, String time, String summary, String ozone, String temperature) {
		// TODO Auto-generated method stub
		Climate climate = new Climate();
		climate.setLatitude(latitude);
		climate.setLongitute(longitute);
		climate.setTemperature(temperature);
		climate.setTime(time);
		climate.setOzone(ozone);
		climate.setSummary(summary);
		climateRepositoryInterface.save(climate);
		
	}

}
