package org.ugeojson.model.geometry;

import java.util.List;

import org.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class MultiLineStringDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<LineStringDto> lines;

	public List<LineStringDto> getLines() {
		return lines;
	}

	public void setLines(List<LineStringDto> lines) {
		this.lines = lines;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiLineString;
	}
}
