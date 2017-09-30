package com.erumi.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.geometry.PointDto;

public class PointBuilderShould {

	@Test public void
	build_point_to_geometry_geojson(){
		PointDto point = new PointDto(101.2471,37.2368);
		String geometryGeoJSON = PointBuilder.getInstance().toGeoJSON(point);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"Point\",\n\"coordinates\": [101.2471, 37.2368]\n}"));
	}
	
	@Test public void
	build_point_with_elevation(){
		PointDto point = new PointDto(101.2471,37.2368,1200);
		String geometryGeoJSON = PointBuilder.getInstance().toGeoJSON(point);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"Point\",\n\"coordinates\": [101.2471, 37.2368, 1200.0]\n}"));
	}
	
}
