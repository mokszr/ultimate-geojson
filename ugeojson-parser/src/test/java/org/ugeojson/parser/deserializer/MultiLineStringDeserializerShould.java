package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.parser.deserializer.LineStringDeserializer;
import org.ugeojson.parser.deserializer.MultiLineStringDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MultiLineStringDeserializerShould {

	@Test public void
	deserialize_multiline(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(MultiLineStringDto.class, new MultiLineStringDeserializer());
		Gson gson = gsonBuilder.create();
		String multiLineStringGeoJson = "{  \"type\": \"MultiLineString\",  "
				+ "     \"coordinates\": [ "
				+ "           [  "
				+ "     [100.0, 0.0],"
				+ "     [101.0, 0.0]"
				+ "         ]"
				+ "                    ] }";
		
		MultiLineStringDto multiLineString = gson.fromJson(multiLineStringGeoJson, MultiLineStringDto.class);
		LineStringDto lineStringDto = multiLineString.getLines().get(0);
		assertThat(lineStringDto.getPositions().get(0).getLongitude(), equalTo(100.0));
		assertThat(lineStringDto.getPositions().get(0).getLatitude(), equalTo(0.0));
		assertThat(lineStringDto.getPositions().get(1).getLongitude(), equalTo(101.0));
		assertThat(lineStringDto.getPositions().get(1).getLatitude(), equalTo(0.0));
	}
	
	@Test public void
	deserialize_multiline_multiple_lines(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(MultiLineStringDto.class, new MultiLineStringDeserializer());
		Gson gson = gsonBuilder.create();
		String multiLineStringGeoJson = "{  \"type\": \"Polygon\",  "
				+ "     \"coordinates\": [ "
				+ "           [  "
				+ "     [100.0, 0.0],"
				+ "     [101.0, 0.0],"
				+ "     [101.0, 1.0],"
				+ "     [100.0, 1.0],"
				+ "     [100.0, 0.0] "
				+ "         ],"
				+"          [ "
                +"      [100.8, 0.8], "
                 +"     [100.8, 0.2], "
                 +"     [100.2, 0.2],"
                 +"     [100.2, 0.8],"
                 +"     [100.8, 0.8]"
                  +"       ] "
				+ "                    ] }";
		
		MultiLineStringDto multiLineString = gson.fromJson(multiLineStringGeoJson, MultiLineStringDto.class);
		assertThat(multiLineString.getLines().size(), equalTo(2));
		
		LineStringDto lineStringDto = multiLineString.getLines().get(1);
		assertThat(lineStringDto.getPositions().get(0).getLongitude(), equalTo(100.8));
		assertThat(lineStringDto.getPositions().get(0).getLatitude(), equalTo(0.8));
		assertThat(lineStringDto.getPositions().get(1).getLongitude(), equalTo(100.8));
		assertThat(lineStringDto.getPositions().get(1).getLatitude(), equalTo(0.2));
		assertThat(lineStringDto.getPositions().get(2).getLongitude(), equalTo(100.2));
		assertThat(lineStringDto.getPositions().get(2).getLatitude(), equalTo(0.2));
		assertThat(lineStringDto.getPositions().get(3).getLongitude(), equalTo(100.2));
		assertThat(lineStringDto.getPositions().get(3).getLatitude(), equalTo(0.8));
		assertThat(lineStringDto.getPositions().get(4).getLongitude(), equalTo(100.8));
		assertThat(lineStringDto.getPositions().get(4).getLatitude(), equalTo(0.8));
	}
	
}
