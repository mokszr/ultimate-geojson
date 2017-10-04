package org.ugeojson.builder.geometry;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.ugeojson.builder.geometry.CommonGeometryBuilder;
import org.ugeojson.builder.geometry.GeometryCollectionBuilder;
import org.ugeojson.builder.geometry.LineStringBuilder;
import org.ugeojson.builder.geometry.MultiLineStringBuilder;
import org.ugeojson.builder.geometry.MultiPointBuilder;
import org.ugeojson.builder.geometry.MultiPolygonBuilder;
import org.ugeojson.builder.geometry.PointBuilder;
import org.ugeojson.builder.geometry.PolygonBuilder;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.MultiPolygonDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

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
