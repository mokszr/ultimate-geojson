package com.erumi.ugeojson.builder.feature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.feature.FeatureDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;

public class FeatureBuilderShould {

	@Test public void
	build_feature(){
		FeatureDto feature = new FeatureDto();
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		feature.setGeometry(lineString1);
		feature.setId("2423534545");
		
		feature.setProperties("{}");
		String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(feature);
		assertThat(featureGeoJSON, equalTo("{\n\"type\": \"Feature\",\n\"geometry\": {\n\"type\": \"LineString\",\n\"coordinates\": [\n [32.123, 24.587],\n [36.1478, 29.3645]\n]\n},\n\"properties\": {},\n\"id\": 2423534545\n}"));
	}
}
