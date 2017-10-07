package org.geojson.math.util;

import org.junit.Assert;
import org.junit.Test;
import org.ugeojson.math.util.GeoCalculator;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.PointDto;

public class GeoCalculatorShould {

	@Test
	public void calculate_distance_of_points() {
		double distance = GeoCalculator.distance(new PointDto(35.0, 38.08993216059188), new PointDto(35 , 38));
		Assert.assertEquals(10000, distance, 0.001);
	}
	
	@Test
	public void calculate_distance_of_positions() {
		double distance = GeoCalculator.distance(new PositionDto(40.6892, -74.0444), new PositionDto(48.8583, 2.2945));
		Assert.assertEquals(8506765.587, distance, 0.001);
	}

	@Test
	public void calculate_destination_point() {
		PointDto point = new PointDto(34, 42);
		PointDto destinationPoint = GeoCalculator.getDestinationPoint(point, 10000, 45);
		Assert.assertEquals(42.0635598, destinationPoint.getLatitude(), 0.000001);
		Assert.assertEquals(34.0856565, destinationPoint.getLongitude(), 0.000001);
	}

	@Test
	public void calculate_destination_position() {
		PositionDto position = new PositionDto(34, 42);
		PositionDto destinationPosition = GeoCalculator.getDestinationPosition(position, 10000, 45);
		Assert.assertEquals(42.0635598, destinationPosition.getLatitude(), 0.000001);
		Assert.assertEquals(34.0856565, destinationPosition.getLongitude(), 0.000001);
	}

}
