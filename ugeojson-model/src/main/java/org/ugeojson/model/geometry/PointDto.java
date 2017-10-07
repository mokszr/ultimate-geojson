package org.ugeojson.model.geometry;

import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.PositionDto;

/**
 * @author moksuzer
 *
 */
public class PointDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private PositionDto position;

	/**
	 * Creates two-dimensional position
	 */
	public PointDto() {
		this.position = new PositionDto();
	}

	/**
	 * Creates two-dimensional position with given longitude and latitude
	 *
	 * @param longitude
	 * @param latitude
	 */
	public PointDto(double longitude, double latitude) {
		this.position = new PositionDto(longitude, latitude);
	}

	/**
	 * Creates three-dimensional position with given longitude, latitude and
	 * elevation
	 * 
	 * @param longitude
	 * @param latitude
	 * @param elevation
	 */
	public PointDto(double longitude, double latitude, double elevation) {
		this.position = new PositionDto(longitude, latitude, elevation);
	}

	/**
	 * Creates point with position parameters of given point 
	 * 
	 * @param point
	 */
	public PointDto(PointDto point) {
		this.position = new PositionDto(point.getPosition());
	}

	/**
	 * @param position
	 */
	public PointDto(PositionDto position) {
		 this.position = new PositionDto(position);
	}

	public double getLongitude() {
		return this.position.getLongitude();
	}

	public void setLongitude(double longitude) {
		this.position.setLongitude(longitude);
	}

	public double getLatitude() {
		return this.position.getLatitude();
	}

	public void setLatitude(double latitude) {
		this.position.setLatitude(latitude);
	}

	public void setElevation(double elevation) {
		this.position.setElevation(elevation);
	}

	public double getElevation() {
		return this.position.getElevation();
	}

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(PositionDto position) {
		this.position = position;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Point;
	}

}
