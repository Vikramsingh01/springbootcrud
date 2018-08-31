package com.example.restAssuredSample.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restAssuredSample.bean.Country;
import com.example.restAssuredSample.service.CountryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//@Path("/countries")
@RestController
@RequestMapping("/countries/")
public class CountryController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	CountryService countryService = new CountryService();

	@GetMapping(value = "/")
	@ResponseBody
	public Object getCountries() {

		List<Country> listOfCountries = countryService.getAllCountries();
		return gson.toJson(listOfCountries);
	}

	 @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@RequestMapping(value = "/temp", method = RequestMethod.GET)
	@ResponseBody
	// public Object getCountryById(@RequestParam("id") int id) {
	public Object getCountryById(@PathVariable int id) {
		return gson.toJson(countryService.getCountry(id));
	}

	@PostMapping
	@ResponseBody
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}

	@PutMapping(value="/{id}")
	@ResponseBody
	public Country updateCountry(@PathVariable int id,@RequestBody  Country country) {
		return countryService.updateCountry(id, country);

	}

	@DeleteMapping(value="/{id}")
	@ResponseBody
	public void deleteCountry(@PathVariable int id, HttpServletResponse response) {
		countryService.deleteCountry(id);
		response.setStatus(200);
	}

}
