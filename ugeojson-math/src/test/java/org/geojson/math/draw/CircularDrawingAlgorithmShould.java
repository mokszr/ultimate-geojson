package org.geojson.math.draw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.ugeojson.builder.UltimateGeoJSONBuilder;
import org.ugeojson.math.draw.CircularDrawingAlgorithmImpl;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.factory.UltimateGeoJSONFactory;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

public class CircularDrawingAlgorithmShould {


	@Test public void
	draw_circle_from_positions(){
		CircularDrawingAlgorithmImpl circleDrawer = new CircularDrawingAlgorithmImpl();
		List<PositionDto> circlePoints = circleDrawer.getCirclePositions(new PositionDto(35,38), 10000.0);
		
		UltimateGeoJSONFactory factory = new UltimateGeoJSONFactory();
		PolygonDto circleAsPolygon = factory.createPolygon(circlePoints);
		
		String geoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(circleAsPolygon);
		assertThat(circlePoints.size(), equalTo(361));
		assertThat(geoJSON, notNullValue());
		assertThat(geoJSON.isEmpty(), equalTo(false));
		//System.out.println(geoJSON);
	}
	
	@Test public void
	draw_circle_from_points(){
		CircularDrawingAlgorithmImpl circleDrawer = new CircularDrawingAlgorithmImpl();
		PointDto center = new PointDto(35,38);
		List<PointDto> circlePoints = circleDrawer.getCirclePoints(center, 10000);
		
		PolygonDto polygon = new PolygonDto();
		LineStringDto ring = new LineStringDto();
		List<PositionDto> positions = new ArrayList<>();

		for (PointDto p : circlePoints) {
			positions.add(p.getPosition());			
		}
		
		ring.setPositions(positions);
		polygon.setLinearRings(Arrays.asList(ring));

		String geoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(polygon);
		assertThat(circlePoints.size(), equalTo(361));
		assertThat(geoJSON, notNullValue());
		assertThat(geoJSON.isEmpty(), equalTo(false));
		//System.out.println(geoJSON);
	}
	
	@Test public void
	draw_arc(){
		CircularDrawingAlgorithmImpl circleDrawer = new CircularDrawingAlgorithmImpl();
		PositionDto center = new PositionDto(35,38);
		List<PositionDto> arcPositions = circleDrawer.getArcPositions(center, 10000, 0, 45);
		
		UltimateGeoJSONFactory factory = new UltimateGeoJSONFactory();
		PolygonDto polygon = factory.createPolygon(arcPositions);
		
		String geoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(polygon);
		//System.out.println(geoJSON);
		assertThat(arcPositions.size(), equalTo(48));
		assertThat(geoJSON, notNullValue());
		assertThat(geoJSON.isEmpty(), equalTo(false));
	}
	
}
