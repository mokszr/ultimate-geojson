package org.ugeojson.model.geometry;

import java.util.List;

import org.ugeojson.model.GeoJSONObjectTypeEnum;

/**
 * @author moksuzer
 *
 */
public class MultiPolygonDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PolygonDto> polygons;

	public List<PolygonDto> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonDto> polygons) {
		this.polygons = polygons;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPolygon;
	}
}
