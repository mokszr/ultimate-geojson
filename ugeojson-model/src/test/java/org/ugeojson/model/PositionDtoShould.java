package org.ugeojson.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.model.PositionDto;

public class PositionDtoShould {

	@Test public void
	copy_position(){
		PositionDto position = new PositionDto(14.7,23.8);
		PositionDto copy = new PositionDto(position);
		assertThat(copy.getLongitude(), equalTo(14.7));
		assertThat(copy.getLatitude(), equalTo(23.8));
	}
	
	@Test public void
	deep_copy_position(){
		PositionDto position = new PositionDto(14.7,23.8);
		PositionDto copy = new PositionDto(position);
		
		position.setLongitude(99.0);
		position.setLatitude(55.5);
		
		assertThat(copy.getLongitude(), equalTo(14.7));
		assertThat(copy.getLatitude(), equalTo(23.8));
	}
	
}
