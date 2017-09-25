package com.erumi.ugeojson.model.geometry;

import java.util.List;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class PolygonDto extends GeoJSONObjectDto implements GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<LineStringDto> linearRings;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Polygon;
	}

	public List<LineStringDto> getLinearRings() {
		return linearRings;
	}

	public void setLinearRings(List<LineStringDto> linearRings) {
		this.linearRings = linearRings;
	}

}
