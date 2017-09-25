package com.erumi.ugeojson.builder.geometry;

import java.util.List;

import com.erumi.ugeojson.builder.common.BuilderConstants;
import com.erumi.ugeojson.builder.exception.InvalidPolygonDtoException;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.PositionDto;
import com.erumi.ugeojson.model.geometry.GeometryDto;
import com.erumi.ugeojson.model.geometry.LineStringDto;
import com.erumi.ugeojson.model.geometry.PolygonDto;

/**
 * @author moksuzer
 *
 * @param <T>
 */
public abstract class GeometryBuilder<T extends GeometryDto> {
	
	private static final int CORRECTABLE_LINEAR_RING_SIZE = 3;
	
	/**
	 * Convert geometryDto object to GeoJSON geometry string.
	 * @param geometry
	 * @return
	 */
	public abstract String toGeometryGeoJSON(T geometry);

	protected void buildTypePart(StringBuilder builder, GeoJSONObjectTypeEnum type) {
		builder.append(BuilderConstants.TYPE_SPACE);
		builder.append(BuilderConstants.DOUBLE_QUOTE).append(type.name()).append(BuilderConstants.DOUBLE_QUOTE);
		builder.append(BuilderConstants.COMMA_NEWLINE);
	}

	protected void buildLineStringPositions(StringBuilder builder, LineStringDto lineStringDto) {
		List<PositionDto> positions = lineStringDto.getPositions();
		for (int j = 0; j < positions.size(); j++) {
			PositionDto position = positions.get(j);
			builder.append(BuilderConstants.SPACE);
			builder.append(PositionBuilder.getInstance().position(position));
			if (j < positions.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}
		}
	}
	
	protected void checkAndCorrectLinearRing(PolygonDto polygon) {
		List<LineStringDto> linearRings = polygon.getLinearRings();
		for (LineStringDto lineStringDto : linearRings) {
			List<PositionDto> positions = lineStringDto.getPositions();
			if (positions.size() < CORRECTABLE_LINEAR_RING_SIZE) {
				throw new InvalidPolygonDtoException(polygon);
			} else if (positions.size() == CORRECTABLE_LINEAR_RING_SIZE) {
				// correct linear ring by adding copy of first position to the
				// end of positions array
				PositionDto firstPosition = positions.get(0);
				positions.add(new PositionDto(firstPosition));
			}
		}
	}
	
	protected void endBuilder(StringBuilder builder) {
		builder.append(BuilderConstants.NEWLINE);
		builder.append(BuilderConstants.CLOSE_CURLY_BRACE);
	}

	protected StringBuilder initializeBuilder() {
		StringBuilder builder = new StringBuilder(BuilderConstants.OPEN_CURLY_BRACE);
		builder.append(BuilderConstants.NEWLINE);
		return builder;
	}

}
