package com.erumi.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.GeometryCollectionDto;
import com.erumi.ugeojson.model.geometry.GeometryDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeometryCollectionDeserializerShould {

	@Test public void
	deserialize_geometrycollection(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(GeometryCollectionDto.class, new GeometryCollectionDeserializer());
		Gson gson = gsonBuilder.create();
		String geoCollectionGeoJson = "{ "
          + " \"type\": \"GeometryCollection\", "
          + "\"geometries\": [{ "
          + " \"type\": \"Point\",  "
          + " \"coordinates\": [100.0, 0.0] " 
          + " }, { " 
          + " \"type\": \"LineString\", "
          + "\"coordinates\": [     [101.0, 0.0],   [102.0, 1.0]      ]      }]   }";
		
		GeometryCollectionDto geoCollection = gson.fromJson(geoCollectionGeoJson, GeometryCollectionDto.class);
		assertThat(geoCollection.getGeometries().size(),equalTo(2));
		GeometryDto firstGeometry = geoCollection.getGeometries().get(0);
		assertThat(firstGeometry,instanceOf(PointDto.class));
		assertThat(((PointDto)firstGeometry).getLongitude(), equalTo(100.0));
		assertThat(((PointDto)firstGeometry).getLatitude(), equalTo(0.0));
		GeometryDto secondGeometry = geoCollection.getGeometries().get(1);
		assertThat(secondGeometry,instanceOf(LineStringDto.class));
		LineStringDto line = (LineStringDto) secondGeometry;
		assertThat(line.getPositions().size(), equalTo(2));
		assertThat(line.getPositions().get(0).getLongitude(), equalTo(101.0));
		assertThat(line.getPositions().get(0).getLatitude(), equalTo(0.0));
		assertThat(line.getPositions().get(1).getLongitude(), equalTo(102.0));
		assertThat(line.getPositions().get(1).getLatitude(), equalTo(1.0));
	}
	
	
}
