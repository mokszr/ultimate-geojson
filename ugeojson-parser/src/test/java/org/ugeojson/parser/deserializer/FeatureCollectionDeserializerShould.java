package org.ugeojson.parser.deserializer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.feature.FeatureCollectionDto;
import org.ugeojson.model.feature.FeatureDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.parser.deserializer.FeatureDeserializer;
import org.ugeojson.parser.deserializer.PointDeserializer;
import org.ugeojson.parser.deserializer.PositionDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FeatureCollectionDeserializerShould {

	@Test public void
	deserialize_featurecollection(){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureDto.class, new FeatureDeserializer());
		Gson gson = gsonBuilder.create();
		String featureGeoJson = "{\"type\": \"Feature\", "
				+ " \"properties\": {}, "
				+ " \"geometry\":  {        \"type\": \"Point\",       \"coordinates\": [14.244158,   \n47.149861, 0.7890780]    } }";
		
		String featureCollectionGeojson = "{\"type\": \"FeatureCollection\", " 
				 + " \"features\": [ " + featureGeoJson + " ] "
				 +" } ";
		
		FeatureCollectionDto featureCollectionDto = gson.fromJson(featureCollectionGeojson, FeatureCollectionDto.class);
		FeatureDto featureDto = featureCollectionDto.getFeatures().get(0);
		GeometryDto geometry = featureDto.getGeometry();
		PointDto pointDto = (PointDto) geometry;
		assertThat(pointDto.getLongitude(), equalTo(14.244158));
		assertThat(pointDto.getLatitude(), equalTo(47.149861));
		assertThat(pointDto.getElevation(), equalTo(0.7890780));
	}
	
	
}
