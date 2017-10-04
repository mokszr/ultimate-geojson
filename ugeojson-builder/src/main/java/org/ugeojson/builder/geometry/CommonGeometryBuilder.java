package org.ugeojson.builder.geometry;

import java.util.HashMap;
import java.util.Map;

import org.ugeojson.builder.common.BuilderConstants;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.MultiPolygonDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

/**
 * Find suitable geometry builder
 * 
 * @author moksuzer
 *
 */
public final class CommonGeometryBuilder {

	private static Map<Class<? extends GeometryDto>, GeometryBuilder<?>> builders = new HashMap<>();

	static {
		builders.put(PointDto.class, PointBuilder.getInstance());
		builders.put(LineStringDto.class, LineStringBuilder.getInstance());
		builders.put(PolygonDto.class, PolygonBuilder.getInstance());
		builders.put(MultiPointDto.class, MultiPointBuilder.getInstance());
		builders.put(MultiLineStringDto.class, MultiLineStringBuilder.getInstance());
		builders.put(MultiPolygonDto.class, MultiPolygonBuilder.getInstance());
		builders.put(GeometryCollectionDto.class, GeometryCollectionBuilder.getInstance());
	}

	private CommonGeometryBuilder() {
	}

	/**
	 * Get suitable GeometryBuilder
	 * 
	 * @param geometryDto
	 *            An instance of GeometryDto
	 * @return
	 */
	public static GeometryBuilder getBuilder(GeometryDto geometryDto) {
		if (geometryDto == null) {
			return null;
		}
		return builders.get(geometryDto.getClass());
	}

	/**
	 * Find suitable builder and return build result;
	 * 
	 * @param geometryDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String toGeometryGeoJSON(GeometryDto geometryDto) {
		GeometryBuilder builder = getBuilder(geometryDto);
		if (builder != null) {
			return builder.toGeoJSON(geometryDto);
		}
		return BuilderConstants.NULL_VALUE;
	}

}
