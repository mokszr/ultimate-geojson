package org.ugeojson.math.draw;

import java.util.ArrayList;
import java.util.List;

import org.ugeojson.math.util.GeoCalculator;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.PointDto;

/**
 * Circular drawing methods are here
 * @author moksuzer
 *
 */
public class CircularDrawingAlgorithmImpl implements CircularDrawingAlgorithm {

	private static final double FULL_CIRCLE_ANGLE = 360;
	private static final double INITIAL_ANGLE = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ugeojson.math.draw.CircularDrawingAlgorithm#getCirclePoints(org.
	 * ugeojson.model.geometry.PointDto, double)
	 */
	@Override
	public List<PointDto> getCirclePoints(PointDto center, double radiusInMeters) {
		List<PointDto> circlePoints = new ArrayList<>();

		PointDto firstPoint = GeoCalculator.getDestinationPoint(center, radiusInMeters, INITIAL_ANGLE);
		circlePoints.add(firstPoint);
		for (double i = 1; i < FULL_CIRCLE_ANGLE; i++) {
			PointDto destination = GeoCalculator.getDestinationPoint(center, radiusInMeters, i);
			circlePoints.add(destination);
		}
		circlePoints.add(firstPoint);

		return circlePoints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ugeojson.math.draw.CircularDrawingAlgorithm#getArcPositions(org.
	 * ugeojson.model.PositionDto, double, double, double)
	 */
	@Override
	public List<PositionDto> getArcPositions(PositionDto center, double radiusInMeters, double startingAngle,
			double endingAngle) {
		List<PositionDto> arcPositions = new ArrayList<>();
		arcPositions.add(center);
		for (double i = startingAngle; i <= endingAngle; i++) {
			PositionDto destination = GeoCalculator.getDestinationPosition(center, radiusInMeters, i);
			arcPositions.add(destination);
		}
		arcPositions.add(center);
		return arcPositions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ugeojson.math.draw.CircularDrawingAlgorithm#getCirclePositions(org.
	 * ugeojson.model.PositionDto, double)
	 */
	@Override
	public List<PositionDto> getCirclePositions(PositionDto center, double radiusInMeters) {
		List<PositionDto> circlePoints = new ArrayList<>();
		PositionDto firstPoint = GeoCalculator.getDestinationPosition(center, radiusInMeters, INITIAL_ANGLE);
		circlePoints.add(firstPoint);

		for (double i = 1; i < FULL_CIRCLE_ANGLE; i++) {
			PositionDto destination = GeoCalculator.getDestinationPosition(center, radiusInMeters, i);
			circlePoints.add(destination);
		}

		circlePoints.add(firstPoint);

		return circlePoints;
	}

}
