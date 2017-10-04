package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.parser.deserializer.PointDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PointDeserializerShould {

	@Test public void
	deserialize_point(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		Gson gson = gsonBuilder.create();
		String pointGeoJson = "{        \"type\": \"Point\",       \"coordinates\": [14.244158,   \n47.149861, 0.7890780]    }";
		
		PointDto pointDto = gson.fromJson(pointGeoJson, PointDto.class);
		assertThat(pointDto.getLongitude(), equalTo(14.244158));
		assertThat(pointDto.getLatitude(), equalTo(47.149861));
		assertThat(pointDto.getElevation(), equalTo(0.7890780));
	}
	
	@Test public void
	deserialize_point_empty_coordinates(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		Gson gson = gsonBuilder.create();
		String pointGeoJson = "{        \"type\": \"Point\",       \"coordinates\": []    }";
		
		PointDto pointDto = gson.fromJson(pointGeoJson, PointDto.class);
		assertThat(pointDto.getPosition().getNumbers().length, equalTo(0));
	}
	
	
}
