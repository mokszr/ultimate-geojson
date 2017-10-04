package org.ugeojson.builder.feature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.ugeojson.builder.feature.FeatureBuilder;
import org.ugeojson.model.BoundingBoxDto;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.feature.FeatureDto;
import org.ugeojson.model.geometry.LineStringDto;

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
	
	@Test public void
	build_with_bbox(){
		FeatureDto feature = new FeatureDto();
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		feature.setGeometry(lineString1);
		feature.setId("\"uniqueId\"");
		
		feature.setProperties("{}");
		BoundingBoxDto bbox = new BoundingBoxDto();
		bbox.setSouthWestCorner(new PositionDto(10.10, 20.20));
		bbox.setNorthEastCorner(new PositionDto(30.30, 40.40));
		feature.setBbox(bbox);
		
		String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(feature);
 		assertThat(featureGeoJSON, equalTo("{\n\"type\": \"Feature\",\n\"geometry\": {\n\"type\": \"LineString\",\n\"coordinates\": [\n [32.123, 24.587],\n [36.1478, 29.3645]\n]\n},\n\"properties\": {},\n\"id\": \"uniqueId\",\n\"bbox\": [10.1, 20.2, 30.3, 40.4]\n}"));
	}
	
	@Test public void
	build_with_bbox_with_elevation(){
		FeatureDto feature = new FeatureDto();
		feature.setProperties("{}");
		 
		BoundingBoxDto bbox = new BoundingBoxDto();
		bbox.setSouthWestCorner(new PositionDto(10.10, 20.20,1000));
		bbox.setNorthEastCorner(new PositionDto(30.30, 40.40,800));
		feature.setBbox(bbox);
		
		String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(feature);
  		assertThat(featureGeoJSON, equalTo("{\n\"type\": \"Feature\",\n\"geometry\": null,\n\"properties\": {},\n\"bbox\": [10.1, 20.2, 1000.0, 30.3, 40.4, 800.0]\n}"));
	}
	
}
