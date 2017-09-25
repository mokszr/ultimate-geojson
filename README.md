# ultimate-geojson
Ultimate GeoJSON java library to build, parse GeoJSON and more

Example Polygon GeoJSON building code:

PolygonDto polygon = new PolygonDto();
LineStringDto lineString1 = generateLineString(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645));
lineString1.getPositions().add(new PositionDto(44.44,45,55));
		
polygon.setLinearRings(Arrays.asList(lineString1));
String geometryGeoJSON = PolygonBuilder.getInstance().toGeometryGeoJSON(polygon);

Output:
{
"type": "Polygon",
"coordinates": [
[
 [32.123, 24.587],
 [36.1478, 29.3645],
 [44.44, 45.0, 55.0],
 [32.123, 24.587]
]
]
}
