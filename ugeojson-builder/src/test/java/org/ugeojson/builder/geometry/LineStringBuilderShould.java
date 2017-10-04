package org.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.ugeojson.builder.geometry.LineStringBuilder;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;

public class LineStringBuilderShould {

	@Test
	public void build_linestring() {
		LineStringDto lineString = new LineStringDto();
		List<PositionDto> positions = new ArrayList<>();
		positions.add(new PositionDto(32.123,24.587));
		positions.add(new PositionDto(36.1478,29.3645));
		lineString.setPositions(positions);
		
		String geometryGeoJSON = LineStringBuilder.getInstance().toGeoJSON(lineString);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"LineString\",\n\"coordinates\": [\n [32.123, 24.587],\n [36.1478, 29.3645]\n]\n}"));
	}
}
