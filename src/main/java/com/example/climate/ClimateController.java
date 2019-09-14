package com.example.climate;

import java.util.HashMap;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.climate.service.ClimateService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


//import com.google.gson.JsonObject;

@Controller
public class ClimateController {
	@Value("${spring.application.name}")
	String appName;	
	@Value("${spring.application.name1}")
	String appName1;
//on load of application redirecting
	@GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
	}
//	This method acts as a url directing for post from UI 
	@RequestMapping(value = "/get-temp", method = RequestMethod.POST)
	@ResponseBody	
	public String homePage1(@RequestBody String id, Model model) {
		
		ClimateService cs = new ClimateService();
		String jsonResponse = cs.getOrCreateClimateParameter(id.toString());		
				
		return jsonResponse;
		
	}

	
	@RequestMapping(value = "/get-location-page", method = RequestMethod.GET)
    public String findByLocationPage(Model model) {
        model.addAttribute("appName", appName1);
        return "findByLocationPage";
	}
	
	@RequestMapping(value = "/get-climate-by-location", method = RequestMethod.POST)
	@ResponseBody	
	public String findClimateByLocation(@RequestBody String data) {
		String jsonResponse = null;
	JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
	String  city = obj.get("city").getAsString();
	String country = obj.get("country").getAsString();
		
		
		System.out.print("city"+"==== "+data+"  "+city+" "+country);
		ClimateService cs = new ClimateService();
		if(city != null && country != null) {			
			jsonResponse = cs.getOrCreateClimateParameterByLocation(city, country);	
		}
		System.out.println("response-->"+jsonResponse);
				
		return jsonResponse;		
	}
 
}
