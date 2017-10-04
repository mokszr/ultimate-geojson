package org.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.ugeojson.builder.geometry.MultiLineStringBuilder;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;

public class MultiLineStringBuilderShould {

	@Test public void
	build_multilinestring(){
		MultiLineStringDto multiLine = new MultiLineStringDto();
		
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		
		multiLine.setLines(Arrays.asList(lineString1,lineString2));
		
		String geometryGeoJSON = MultiLineStringBuilder.getInstance().toGeoJSON(multiLine);
		System.out.println(geometryGeoJSON);
		assertThat(geometryGeoJSON,equalTo("{\n\"type\": \"MultiLineString\",\n\"coordinates\": [\n[\n [32.123, 24.587],\n [36.1478, 29.3645]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778]\n]\n]\n}"));
	}

	
}
