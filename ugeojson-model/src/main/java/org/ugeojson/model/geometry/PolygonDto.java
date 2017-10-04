package org.ugeojson.model.geometry;

import java.util.List;

import org.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class PolygonDto extends GeometryDto {

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
