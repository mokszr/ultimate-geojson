package com.erumi.ugeojson.builder.feature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.feature.FeatureCollectionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;

public class FeatureCollectionBuilderShould {

	@Test public void
	build_feature_collection(){
		FeatureDto feature = new FeatureDto();
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		feature.setGeometry(lineString1);
		feature.setId("2423534545");
		feature.setProperties("{}");
		
		FeatureDto feature2 = new FeatureDto();
		LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(47.47, 59.457),new PositionDto(86.3698, 45.10471)));
		feature2.setGeometry(lineString2);
		feature2.setId("\"myFeatureId\"");
		
		feature2.setProperties("{\"color\": \"red\"}");
		FeatureCollectionDto featureCollection = new FeatureCollectionDto();
		featureCollection.setFeatures(Arrays.asList(feature,feature2));
		
		String featureCollectionGeoJSON = FeatureCollectionBuilder.getInstance().toGeoJSON(featureCollection);
		assertThat(featureCollectionGeoJSON, equalTo("{\n\"type\": \"FeatureCollection\",\n\"features\": [{\n\"type\": \"Feature\",\n\"geometry\": {\n\"type\": \"LineString\",\n\"coordinates\": [\n [32.123, 24.587],\n [36.1478, 29.3645]\n]\n},\n\"properties\": {},\n\"id\": 2423534545\n},\n{\n\"type\": \"Feature\",\n\"geometry\": {\n\"type\": \"LineString\",\n\"coordinates\": [\n [47.47, 59.457],\n [86.3698, 45.10471]\n]\n},\n\"properties\": {\"color\": \"red\"},\n\"id\": \"myFeatureId\"\n}]\n}"));
	}
	
	@Test public void
	build_null_to_json_null(){
		String featureCollectionGeoJSON = FeatureCollectionBuilder.getInstance().toGeoJSON(null);
		assertThat(featureCollectionGeoJSON, equalTo("null"));
	}
	
	@Test public void
	build_empty_features(){
		FeatureCollectionDto featureCollectionDto = new FeatureCollectionDto();
		featureCollectionDto.setFeatures(Collections.emptyList());
		String featureCollectionGeoJSON = FeatureCollectionBuilder.getInstance().toGeoJSON(featureCollectionDto);
		assertThat(featureCollectionGeoJSON, equalTo("{\n\"type\": \"FeatureCollection\",\n\"features\": []\n}"));
	}
	
	@Test public void
	build_null_features(){
		FeatureCollectionDto featureCollectionDto = new FeatureCollectionDto();
		assertThat(featureCollectionDto.getFeatures(), nullValue());
		String featureCollectionGeoJSON = FeatureCollectionBuilder.getInstance().toGeoJSON(featureCollectionDto);
		assertThat(featureCollectionGeoJSON, equalTo("{\n\"type\": \"FeatureCollection\",\n\"features\": []\n}"));
	}
}
