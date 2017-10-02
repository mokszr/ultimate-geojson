package com.erumi.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.GeometryDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FeatureDeserializerShould {

	@Test public void
	deserialize_feature(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureDto.class, new FeatureDeserializer());
		Gson gson = gsonBuilder.create();
		String featureGeoJson = "{\"type\": \"Feature\", "
				+ " \"bbox\": [-10.0, -10.0, 10.0, 10.0],"
				+ " \"properties\": {}, "
				+ " \"geometry\":  {        \"type\": \"Point\",       \"coordinates\": [14.244158,   \n47.149861, 0.7890780]    } }";
		
		FeatureDto featureDto = gson.fromJson(featureGeoJson, FeatureDto.class);
		GeometryDto geometry = featureDto.getGeometry();
		PointDto pointDto = (PointDto) geometry;
		assertThat(pointDto.getLongitude(), equalTo(14.244158));
		assertThat(pointDto.getLatitude(), equalTo(47.149861));
		assertThat(pointDto.getElevation(), equalTo(0.7890780));
		
		assertThat(featureDto.getBbox(), notNullValue());
		assertThat(featureDto.getBbox().getSouthWestCorner().getLongitude(), equalTo(-10.0));
		assertThat(featureDto.getBbox().getSouthWestCorner().getLatitude(), equalTo(-10.0));
		assertThat(featureDto.getBbox().getNorthEastCorner().getLongitude(), equalTo(10.0));
		assertThat(featureDto.getBbox().getNorthEastCorner().getLatitude(), equalTo(10.0));
	}
	
	
}
