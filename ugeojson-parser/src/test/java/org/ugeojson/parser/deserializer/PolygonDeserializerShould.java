package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.PolygonDto;
import org.ugeojson.parser.deserializer.LineStringDeserializer;
import org.ugeojson.parser.deserializer.PolygonDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PolygonDeserializerShould {

	@Test public void
	deserialize_polygon(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		Gson gson = gsonBuilder.create();
		String lineStringGeoJson = "{  \"type\": \"Polygon\",  "
				+ "     \"coordinates\": [ "
				+ "           [  "
				+ "     [100.0, 0.0],"
				+ "     [101.0, 0.0],"
				+ "     [101.0, 1.0],"
				+ "     [100.0, 1.0],"
				+ "     [100.0, 0.0] "
				+ "         ]"
				+ "                    ] }";
		
		PolygonDto polygon = gson.fromJson(lineStringGeoJson, PolygonDto.class);
		LineStringDto lineStringDto = polygon.getLinearRings().get(0);
		assertThat(lineStringDto.getPositions().get(0).getLongitude(), equalTo(100.0));
		assertThat(lineStringDto.getPositions().get(0).getLatitude(), equalTo(0.0));
		assertThat(lineStringDto.getPositions().get(1).getLongitude(), equalTo(101.0));
		assertThat(lineStringDto.getPositions().get(1).getLatitude(), equalTo(0.0));
		assertThat(lineStringDto.getPositions().get(2).getLongitude(), equalTo(101.0));
		assertThat(lineStringDto.getPositions().get(2).getLatitude(), equalTo(1.0));
		assertThat(lineStringDto.getPositions().get(3).getLongitude(), equalTo(100.0));
		assertThat(lineStringDto.getPositions().get(3).getLatitude(), equalTo(1.0));
		assertThat(lineStringDto.getPositions().get(4).getLongitude(), equalTo(100.0));
		assertThat(lineStringDto.getPositions().get(4).getLatitude(), equalTo(0.0));
	}
	
	@Test public void
	deserialize_polygon_multiple_ring(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		Gson gson = gsonBuilder.create();
		String lineStringGeoJson = "{  \"type\": \"Polygon\",  "
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
		
		PolygonDto polygon = gson.fromJson(lineStringGeoJson, PolygonDto.class);
		assertThat(polygon.getLinearRings().size(), equalTo(2));
		
		LineStringDto lineStringDto = polygon.getLinearRings().get(1);
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
