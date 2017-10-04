package org.ugeojson.builder.geometry;

import java.util.List;

import org.ugeojson.builder.common.BuilderConstants;
import org.ugeojson.builder.common.GeoJSONBuilder;
import org.ugeojson.builder.exception.InvalidPolygonDtoException;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.PolygonDto;

/**
 * @author moksuzer
 *
 * @param <T>
 */
public abstract class GeometryBuilder<T extends GeometryDto> extends GeoJSONBuilder<T> {

	private static final int CORRECTABLE_LINEAR_RING_SIZE = 3;

	/**
	 * Convert geometryDto object to GeoJSON geometry string.
	 * 
	 * @param geometry GeometryDto object to be converted to GeoJSON
	 * @return
	 */
	@Override
	public abstract String toGeoJSON(T geometry);

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

}
