package org.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.ugeojson.builder.geometry.GeometryCollectionBuilder;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

public class GeometryCollectionBuilderShould {

	@Test public void
	build_point_and_linestring_geometry_collection_geojson(){
		PointDto point = new PointDto(101.2471,37.2368);
		LineStringDto line = new LineStringDto(Arrays.asList(new PositionDto(101.01, 58.0147),new PositionDto(24.014, 19.364)));
		
		GeometryCollectionDto geometryCollection = new GeometryCollectionDto();
		geometryCollection.setGeometries(Arrays.asList(point,line));
		String geometryGeoJSON = GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollection);
		System.out.println(geometryGeoJSON);	
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"GeometryCollection\",\n\"geometries\": [{\n\"type\": \"Point\",\n\"coordinates\": [101.2471, 37.2368]\n},{\n\"type\": \"LineString\",\n\"coordinates\": [\n [101.01, 58.0147],\n [24.014, 19.364]\n]\n}]\n}"));
	}
	
	@Test public void
	build_multipoint_and_polygon_geometry_collection_geojson(){
		MultiPointDto lineString = new MultiPointDto();
		List<PositionDto> positions = new ArrayList<>();
		positions.add(new PositionDto(32.123, 24.587));
		positions.add(new PositionDto(36.1478, 29.3645));
		lineString.setPositions(positions);
		
		PolygonDto polygon = new PolygonDto();
		
		LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
		LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		LineStringDto lineString3 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		
		lineString1.getPositions().add(new PositionDto(44.44,45,55));
		lineString2.getPositions().add(new PositionDto(34,45,15.78));
		lineString3.getPositions().add(new PositionDto(10.44,58,55));	
		
		polygon.setLinearRings(Arrays.asList(lineString1,lineString2,lineString3));
		
		GeometryCollectionDto geometryCollection = new GeometryCollectionDto();
		geometryCollection.setGeometries(Arrays.asList(lineString,polygon));
		String geometryGeoJSON = GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollection);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"GeometryCollection\",\n\"geometries\": [{\n\"type\": \"MultiPoint\",\n\"coordinates\": [\n[32.123, 24.587],\n[36.1478, 29.3645]\n]\n},{\n\"type\": \"Polygon\",\n\"coordinates\": [\n[\n [32.123, 24.587],\n [36.1478, 29.3645],\n [44.44, 45.0, 55.0],\n [32.123, 24.587]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778],\n [34.0, 45.0, 15.78],\n [12.2365, -14.8987]\n],\n[\n [12.2365, -14.8987],\n [63.254, 28.778],\n [10.44, 58.0, 55.0],\n [12.2365, -14.8987]\n]\n]\n}]\n}"));
	}
	
	@Test public void
	build_empty_geometry_list(){
		GeometryCollectionDto geometryCollection = new GeometryCollectionDto();
		geometryCollection.setGeometries(Collections.emptyList());
		String geometryGeoJSON = GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollection);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"GeometryCollection\",\n\"geometries\": []\n}"));
	}
	
	@Test public void
	build_null_geometry_list(){
		GeometryCollectionDto geometryCollection = new GeometryCollectionDto();
		assertThat(geometryCollection.getGeometries(), nullValue());
		String geometryGeoJSON = GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollection);
		assertThat(geometryGeoJSON, equalTo("{\n\"type\": \"GeometryCollection\",\n\"geometries\": []\n}"));
	}
	 
}
