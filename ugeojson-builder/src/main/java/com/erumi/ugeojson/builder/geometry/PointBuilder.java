package com.erumi.ugeojson.builder.geometry;

import com.erumi.ugeojson.builder.common.BuilderConstants;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.geometry.PointDto;

/**
 * @author moksuzer
 *
 */
public class PointBuilder extends GeometryBuilder<PointDto> {

	private static final PointBuilder INSTANCE = new PointBuilder();

	public static PointBuilder getInstance() {
		return INSTANCE;
	}

	private PointBuilder() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.erumi.ugeojson.builder.geometry.GeometryBuilder#toGeometryGeoJSON(com
	 * .erumi.ugeojson.model.geometry.GeometryDto)
	 */
	@Override
	public String toGeoJSON(PointDto point) {
		if (point == null || point.getPosition() == null || point.getPosition().getNumbers().length == 0) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.Point);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(PositionBuilder.getInstance().position(point.getPosition()));

		buildBbox(builder, point.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
