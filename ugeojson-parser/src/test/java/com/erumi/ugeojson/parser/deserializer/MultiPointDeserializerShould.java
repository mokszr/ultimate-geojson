package com.erumi.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.MultiPointDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MultiPointDeserializerShould {

	@Test public void
	deserialize_linestring(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPointDto.class, new MultiPointDeserializer());
		Gson gson = gsonBuilder.create();
		String lineStringGeoJson = "{        \"type\": \"MultiPoint\",       \"coordinates\": [           [100.0, 4.0],          [101.0, 9.0]      ]   }";
		
		MultiPointDto multiPoint = gson.fromJson(lineStringGeoJson, MultiPointDto.class);
		assertThat(multiPoint.getPositions().get(0).getLongitude(), equalTo(100.0));
		assertThat(multiPoint.getPositions().get(0).getLatitude(), equalTo(4.0));
		assertThat(multiPoint.getPositions().get(1).getLongitude(), equalTo(101.0));
		assertThat(multiPoint.getPositions().get(1).getLatitude(), equalTo(9.0));
 
	}
	
	@Test public void
	deserialize_linestring_empty_coordinates(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPointDto.class, new MultiPointDeserializer());
		Gson gson = gsonBuilder.create();
		String multiPointGeoJson = "{        \"type\": \"MultiPoint\",       \"coordinates\": [   ]   }";
		
		MultiPointDto multiPoint = gson.fromJson(multiPointGeoJson, MultiPointDto.class);
		assertThat(multiPoint.getPositions().size(), equalTo(0));
	}
	
	
}
