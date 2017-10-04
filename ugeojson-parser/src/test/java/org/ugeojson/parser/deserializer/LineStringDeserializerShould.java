package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.parser.deserializer.LineStringDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LineStringDeserializerShould {

	@Test public void
	deserialize_linestring(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		Gson gson = gsonBuilder.create();
		String lineStringGeoJson = "{        \"type\": \"LineString\",       \"coordinates\": [           [100.0, 4.0],          [101.0, 9.0]      ]   }";
		
		LineStringDto lineString = gson.fromJson(lineStringGeoJson, LineStringDto.class);
		assertThat(lineString.getPositions().get(0).getLongitude(), equalTo(100.0));
		assertThat(lineString.getPositions().get(0).getLatitude(), equalTo(4.0));
		assertThat(lineString.getPositions().get(1).getLongitude(), equalTo(101.0));
		assertThat(lineString.getPositions().get(1).getLatitude(), equalTo(9.0));
 
	}
	
	@Test public void
	deserialize_linestring_empty_coordinates(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		Gson gson = gsonBuilder.create();
		String lineStringGeoJson = "{        \"type\": \"LineString\",       \"coordinates\": [   ]   }";
		
		LineStringDto lineString = gson.fromJson(lineStringGeoJson, LineStringDto.class);
		assertThat(lineString.getPositions().size(), equalTo(0));

	}
	
	
}
