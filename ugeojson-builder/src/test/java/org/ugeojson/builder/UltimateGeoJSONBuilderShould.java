package org.ugeojson.builder;

import java.util.Arrays;

import org.junit.Test;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.feature.FeatureDto;
import org.ugeojson.model.geometry.LineStringDto;

public class UltimateGeoJSONBuilderShould {

	@Test public void
	generate_geojson_from_dtos(){
		FeatureDto feature = new FeatureDto();
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		feature.setGeometry(lineString1);
		feature.setId("2423534545");
		
		feature.setProperties("{}");
		String featureGeoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(feature);
		System.out.println(featureGeoJSON);

	}
}
