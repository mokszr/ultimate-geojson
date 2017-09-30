package com.erumi.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiPolygonDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MultiPolygonDeserializerShould {

	@Test public void
	deserialize_multipolygon(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPolygonDto.class, new MultiPolygonDeserializer());
		Gson gson = gsonBuilder.create();
		String lmultiPolygonGeoJson = "{  \"type\": \"MultiPolygon\",  "
				+ "     \"coordinates\":[ [ "
				+ "           [  "
				+ "     [100.0, 0.0],"
				+ "     [101.0, 0.0],"
				+ "     [101.0, 1.0],"
				+ "     [100.0, 1.0],"
				+ "     [100.0, 0.0] "
				+ "         ]"
				+ "                    ] ]}";
		
		MultiPolygonDto multiPolygon = gson.fromJson(lmultiPolygonGeoJson, MultiPolygonDto.class);
		PolygonDto polygon = multiPolygon.getPolygons().get(0);
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
	deserialize_multipolygon_multiple_polygon(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPolygonDto.class, new MultiPolygonDeserializer());
		Gson gson = gsonBuilder.create();
		String multiPolygonGeoJson = "{  \"type\": \"MultiPolygon\",  "
				+ "     \"coordinates\":[ [ "
				+ "           [  "
				+ "     [100.0, 0.0],"
				+ "     [101.0, 0.0],"
				+ "     [101.0, 1.0],"
				+ "     [100.0, 1.0],"
				+ "     [100.0, 0.0] "
				+ "         ] ], ["
				+"          [ "
                +"      [100.8, 0.8], "
                 +"     [100.8, 0.2], "
                 +"     [100.2, 0.2],"
                 +"     [100.2, 0.8],"
                 +"     [100.8, 0.8]"
                  +"       ] ]"
				+ "                    ] }";
		
		MultiPolygonDto multiPolygon = gson.fromJson(multiPolygonGeoJson, MultiPolygonDto.class);
		assertThat(multiPolygon.getPolygons().size(), equalTo(2));
		PolygonDto polygon = multiPolygon.getPolygons().get(1);
		
		LineStringDto lineStringDto = polygon.getLinearRings().get(0);
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
