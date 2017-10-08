package org.ugeojson.math.util;

import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.PointDto;

/**
 * @author moksuzer
 *
 */
public final class GeoCalculator {

	// earth radius in meters
	private static final Double EARTH_RADIUS = 6371000.0;

	private GeoCalculator() {
	}

	/**
	 * Calculates destination point, given starting point, distance in meters
	 * and bearing angle in degrees, that is the angle starting from true north.
	 * 
	 * @param startingPoint
	 *            starting point
	 * @param distanceInMeters
	 *            distance in meters
	 * @param bearingInDegrees
	 *            the angle starting from true north in degrees
	 * @return destination point
	 */
	public static PointDto getDestinationPoint(PointDto startingPoint, double distanceInMeters,
			double bearingInDegrees) {
		PositionDto destinationPosition = getDestinationPosition(startingPoint.getPosition(), distanceInMeters,
				bearingInDegrees);
		PointDto destination = new PointDto();
		destination.setPosition(destinationPosition);
		return destination;
	}

	/**
	 * Calculates destination position, given starting position, distance in
	 * meters and bearing angle in degrees, that is the angle starting from true
	 * north.
	 * 
	 * @param startingPoint
	 *            starting point
	 * @param distanceInMeters
	 *            distance in meters
	 * @param bearingInDegrees
	 *            the angle starting from true north in degrees
	 * @return destination point
	 */
	public static PositionDto getDestinationPosition(PositionDto startingPoint, double distanceInMeters,
			double bearingInDegrees) {
		PositionDto destination = new PositionDto(startingPoint);
		double bearing = Math.toRadians(bearingInDegrees);
		double latInRadians = Math.toRadians(startingPoint.getLatitude());
		double lonInRadians = Math.toRadians(startingPoint.getLongitude());

		double angularRadius = distanceInMeters / EARTH_RADIUS;

		double lat2Radian = Math.asin(Math.sin(latInRadians) * Math.cos(angularRadius)
				+ Math.cos(latInRadians) * Math.sin(angularRadius) * Math.cos(bearing));

		double dlon = Math.atan2(Math.sin(bearing) * Math.sin(angularRadius) * Math.cos(latInRadians),
				Math.cos(angularRadius) - Math.sin(latInRadians) * Math.sin(lat2Radian));

		double lon2Radian = (lonInRadians + dlon + Math.PI) % (2 * Math.PI) - Math.PI;

		destination.setLatitude(Math.toDegrees(lat2Radian));
		destination.setLongitude(Math.toDegrees(lon2Radian));

		return destination;
	}

	/**
	 * Calculates distance in meters between two positions
	 * 
	 * @param positionFrom
	 *            starting position
	 * @param positionTo
	 *            ending position
	 * @return
	 */
	public static double distance(PositionDto positionFrom, PositionDto positionTo) {
		double latRadianFrom = Math.toRadians(positionFrom.getLatitude());
		double lonRadianFrom = Math.toRadians(positionFrom.getLongitude());

		double latRadionTo = Math.toRadians(positionTo.getLatitude());
		double lonRadionTo = Math.toRadians(positionTo.getLongitude());

		return Math
				.acos(Math.sin(latRadianFrom) * Math.sin(latRadionTo)
						+ Math.cos(latRadianFrom) * Math.cos(latRadionTo) * Math.cos(lonRadianFrom - lonRadionTo))
				* EARTH_RADIUS;
	}

	/**
	 * Calculates distance in meters between two points
	 * 
	 * @param pointFrom
	 *            starting point
	 * @param pointTo
	 *            ending point
	 * @return
	 */
	public static double distance(PointDto pointFrom, PointDto pointTo) {
		return distance(pointFrom.getPosition(), pointTo.getPosition());
	}

}
