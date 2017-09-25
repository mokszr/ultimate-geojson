package com.erumi.ugeojson.model.geometry;

import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class MultiLineStringDto extends GeoJSONObjectDto implements GeometryDto {

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
