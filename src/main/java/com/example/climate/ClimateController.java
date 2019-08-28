package com.example.climate;

import org.springframework.beans.factory.annotation.Value;
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
//import com.google.gson.JsonObject;

@Controller
public class ClimateController {
	@Value("${spring.application.name}")
	String appName;	
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
}
