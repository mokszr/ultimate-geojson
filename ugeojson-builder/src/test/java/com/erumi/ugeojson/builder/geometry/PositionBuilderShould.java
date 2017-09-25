package com.erumi.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.PositionDto;

public class PositionBuilderShould {

	@Test public void
	build_two_dimension_position(){
		PositionDto position = new PositionDto(14.147, 36.985);
		String positionGeojson = PositionBuilder.getInstance().position(position);
		assertThat(positionGeojson, equalTo("[14.147, 36.985]"));
	}
	
	@Test public void
	build_three_dimension_position(){
		PositionDto position = new PositionDto(14.147, 36.985, 879.4562);
		String positionGeojson = PositionBuilder.getInstance().position(position);
		assertThat(positionGeojson, equalTo("[14.147, 36.985, 879.4562]"));
	}
}
