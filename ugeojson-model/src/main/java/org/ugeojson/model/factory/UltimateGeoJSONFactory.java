package org.ugeojson.model.factory;

import java.util.ArrayList;
import java.util.List;

import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

/**
 * @author moksuzer
 *
 */
public class UltimateGeoJSONFactory {

	public PositionDto createPosition(double longitude, double latitude){
		return new PositionDto(longitude, latitude);
	}
	
	public PositionDto createPosition(double longitude, double latitude, double elevation){
		return new PositionDto(longitude, latitude, elevation);
	}
	
	public LineStringDto createLineString(PositionDto... positions) {
		LineStringDto lineString = new LineStringDto();
		List<PositionDto> lineStringPositions = new ArrayList<>();

		for (PositionDto positionDto : positions) {
			lineStringPositions.add(positionDto);
		}
		lineString.setPositions(lineStringPositions);

		return lineString;
	}
	
	public LineStringDto createLineString(List<PositionDto> positions) {
		LineStringDto lineString = new LineStringDto();
		List<PositionDto> lineStringPositions = new ArrayList<>();

		for (PositionDto positionDto : positions) {
			lineStringPositions.add(positionDto);
		}
		lineString.setPositions(lineStringPositions);

		return lineString;
	}
	

	public LineStringDto createLineString(PointDto... points) {
		List<PositionDto> lineStringPositions = new ArrayList<>();
		LineStringDto lineString = new LineStringDto();
		for (PointDto point : points) {
			lineStringPositions.add(point.getPosition());
		}
		lineString.setPositions(lineStringPositions);

		return lineString;
	}

	public PolygonDto createPolygon(PositionDto... positions) {
		PolygonDto polygon = new PolygonDto();
		List<LineStringDto> rings = new ArrayList<>();
		polygon.setLinearRings(rings);
		LineStringDto lineString = createLineString(positions);
		rings.add(lineString);
		return polygon;
	}

	public PolygonDto createPolygon(List<PositionDto> positionDtos) {
		LineStringDto lineString = new LineStringDto();
		lineString.setPositions(positionDtos);
		List<LineStringDto> rings = new ArrayList<>();
		rings.add(lineString);
		PolygonDto polygon = new PolygonDto();
		polygon.setLinearRings(rings);
		return polygon;
	}

	public PolygonDto createPolygon(PointDto... points) {
		List<PositionDto> lineStringPositions = new ArrayList<>();
		for (PointDto point : points) {
			lineStringPositions.add(point.getPosition());
		}
		LineStringDto lineString = new LineStringDto();
		lineString.setPositions(lineStringPositions);
		List<LineStringDto> rings = new ArrayList<>();
		rings.add(lineString);
		rings.add(lineString);
		PolygonDto polygon = new PolygonDto();
		polygon.setLinearRings(rings);
		return polygon;
	}
 

}
