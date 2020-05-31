package com.nasa_apod.spring.nasa;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nasa_apod.spring.model.NasaJson;

@Service("NasaRest")

public class NasaRest {

	@Autowired
	RestTemplate restTemplate;

	public NasaJson getNasaAPODToday() {
		NasaJson nasa = restTemplate.getForObject(
				"https://api.nasa.gov/planetary/apod?api_key=xpp6bj4qgYwO5shXxXlRxkG2B3MqAyIjzRIrX9Y3", NasaJson.class);

		return nasa;
	}

	public List<NasaJson> getNasaAPODS() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		NasaJson nasa = new NasaJson();
		HttpEntity<NasaJson> entity = new HttpEntity<NasaJson>(nasa, headers);
		ResponseEntity<List<NasaJson>> result = restTemplate.exchange(
				"https://api.nasa.gov/planetary/apod?api_key=xpp6bj4qgYwO5shXxXlRxkG2B3MqAyIjzRIrX9Y3&count=8",
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<NasaJson>>() {
				});

		List<NasaJson> listAPODS = result.getBody();
		listAPODS = listAPODS.stream().filter(apod -> apod.getMedia_type().equalsIgnoreCase("image"))
				.collect(Collectors.toList());

		return listAPODS;
	}

}
