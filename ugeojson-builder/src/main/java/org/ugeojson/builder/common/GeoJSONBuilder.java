package org.ugeojson.builder.common;

import org.ugeojson.model.BoundingBoxDto;
import org.ugeojson.model.GeoJSONObjectDto;
import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.PositionDto;

/**
 * @author moksuzer
 *
 */
public abstract class GeoJSONBuilder<T extends GeoJSONObjectDto> {
	
	/**
	 * Convert geojsonDto object to GeoJSON geometry string.
	 * @param geoJSONDto GeoJSONObjectDto object to be converted to GeoJSON
	 * @return
	 */
	public abstract String toGeoJSON(T geoJSONDto);

	protected void buildTypePart(StringBuilder builder, GeoJSONObjectTypeEnum type) {
		builder.append(BuilderConstants.TYPE_SPACE);
		builder.append(BuilderConstants.DOUBLE_QUOTE).append(type.name()).append(BuilderConstants.DOUBLE_QUOTE);
		builder.append(BuilderConstants.COMMA_NEWLINE);
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
	
	protected void buildBbox(StringBuilder builder, BoundingBoxDto bbox) {
		if(bbox != null && bbox.getSouthWestCorner() != null && bbox.getNorthEastCorner() != null){
			builder.append(BuilderConstants.COMMA_NEWLINE);
			builder.append(BuilderConstants.BBOX_SPACE);
			builder.append(BuilderConstants.OPEN_BRACKET);
			
			PositionDto southWestCorner = bbox.getSouthWestCorner();
			double[] southWestNumbers = southWestCorner.getNumbers();
			for (int i = 0; i < southWestNumbers.length; i++) {
				builder.append(southWestNumbers[i]);
				builder.append(BuilderConstants.COMMA_SPACE);
			}
			
			PositionDto northEastCorner = bbox.getNorthEastCorner();
			double[] northEastNumbers = northEastCorner.getNumbers();
			for (int i = 0; i < northEastNumbers.length; i++) {
				builder.append(northEastNumbers[i]);
				if(i < northEastNumbers.length - 1){
					builder.append(BuilderConstants.COMMA_SPACE);
				}
			}
			builder.append(BuilderConstants.CLOSE_BRACKET);
		}
	}

}
