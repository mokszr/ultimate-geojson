package com.erumi.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiLineStringDto;

public class MultiLineStringBuilderShould {

	@Test public void
	build_multilinestring(){
		MultiLineStringDto multiLine = new MultiLineStringDto();
		
		LineStringDto lineString1 = generateLineString(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645));
		LineStringDto lineString2 = generateLineString(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778));
		
		multiLine.setLines(Arrays.asList(lineString1,lineString2));
		
		String geometryGeoJSON = MultiLineStringBuilder.getInstance().toGeometryGeoJSON(multiLine);
		System.out.println(geometryGeoJSON);
		assertThat(geometryGeoJSON,equalTo("{\n\"type\": \"MultiLineString\",\n\"coordinates\": [\n[\n [32.123, 24.587],\n [36.1478, 29.3645]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778]\n]\n]\n}"));
	}

	private LineStringDto generateLineString(PositionDto positionDto, PositionDto positionDto2) {
		LineStringDto lineString = new LineStringDto();
		List<PositionDto> positions = new ArrayList<>();
		positions.add(positionDto);
		positions.add(positionDto2);
		lineString.setPositions(positions);
		return lineString;
	}
	
}
