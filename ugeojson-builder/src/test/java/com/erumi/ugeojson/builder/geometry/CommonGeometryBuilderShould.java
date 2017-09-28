package com.erumi.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.erumi.ugeojson.model.geometry.GeometryCollectionDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.MultiLineStringDto;
import com.erumi.ugeojson.model.geometry.MultiPointDto;
import com.erumi.ugeojson.model.geometry.MultiPolygonDto;
import com.erumi.ugeojson.model.geometry.PointDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;

public class CommonGeometryBuilderShould {

	@Test public void
	find_suitable_builder(){
		assertThat(CommonGeometryBuilder.getBuilder(new PointDto()), instanceOf(PointBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new LineStringDto()), instanceOf(LineStringBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new PolygonDto()), instanceOf(PolygonBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new MultiPointDto()), instanceOf(MultiPointBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new MultiLineStringDto()), instanceOf(MultiLineStringBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new MultiPolygonDto()), instanceOf(MultiPolygonBuilder.class));
		assertThat(CommonGeometryBuilder.getBuilder(new GeometryCollectionDto()), instanceOf(GeometryCollectionBuilder.class));
	}
	
	@Test public void
	return_null_if_null_passed(){
		assertThat(CommonGeometryBuilder.getBuilder(null), nullValue());
	}
	
	
}
