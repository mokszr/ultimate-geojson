# ultimate-geojson

**ultimate-geojeson** is the ultimate GeoJSON java library to generate & parse GeoJSON and to do geospatial operations on geometry objects.

## About GeoJSON

[GeoJSON](http://geojson.org/) is a format for encoding a variety of geographic data structures. 

**ultimate-geojeson** uses [The GeoJSON Specification (RFC 7946)](https://tools.ietf.org/html/rfc7946)) for its implementation.

## Data Models 
**ultimate-geojeson** represents GeoJSON objects as pojo data transfer objects(Dto). 
* `PositionDto`
* `PointDto`
* `LineStringDto`
* `PolygonDto`
* `MultiPointDto`
* `MultiLineStringDto`
* `MultiPolygonDto`
* `GeometryCollectionDto`
* `FeatureDto`
* `FeatureCollectionDto`

## How To Parse GeoJSON
You can use `UltimateGeoJSONParser` class

```java
String featureGeoJson = "{\"type\": \"Feature\", "
				+ " \"bbox\": [-10.0, -10.0, 10.0, 10.0],"
				+ " \"properties\": {}, "
				+ " \"geometry\":  {        \"type\": \"Point\",       \"coordinates\": [14.244158,   \n47.149861, 0.7890780]    } }";

GeoJSONObjectDto geoJSONObjectDto = UltimateGeoJSONParser.getInstance().parse(featureGeoJson);
FeatureDto feature = (FeatureDto) geoJSONObjectDto;
// prints Point
System.out.println(feature.getGeometry().getGeoJSONObjectType());

```

## How To Build (generate) GeoJSON
For each GeoJSON object type, there is a builder class to convert model dto classes to GeoJSON String. For example; `PointBuilder`, `PolygonBuilder` etc...

Alternatively, you can use `UltimateGeoJSONBuilder` class for all types.

#### PointBuilder
```
PointDto point = new PointDto(101.2471,37.2368);
String geometryGeoJSON = PointBuilder.getInstance().toGeoJSON(point);
```
Output:
```
{
"type": "Point",
"coordinates": [101.2471, 37.2368]
}
```

#### LineStringBuilder
```
LineStringDto lineString = new LineStringDto();
List<PositionDto> positions = new ArrayList<>();
positions.add(new PositionDto(32.123,24.587));
positions.add(new PositionDto(36.1478,29.3645));
lineString.setPositions(positions);
		
String geometryGeoJSON = LineStringBuilder.getInstance().toGeoJSON(lineString);
```
Output:
```
{
"type": "LineString",
"coordinates": [
 [32.123, 24.587],
 [36.1478, 29.3645]
]
}
```

#### PolygonBuilder
```java
PolygonDto polygon = new PolygonDto();
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645),new PositionDto(44.44,45,55)));
				
polygon.setLinearRings(Arrays.asList(lineString1));
String geometryGeoJSON = PolygonBuilder.getInstance().toGeoJSON(polygon);
```

Output:
```
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
```

#### MultiPointBuilder
```
MultiPointDto multiPoint = new MultiPointDto();
List<PositionDto> positions = new ArrayList<>();
positions.add(new PositionDto(32.123, 24.587));
positions.add(new PositionDto(36.1478, 29.3645));
multiPoint.setPositions(positions);
String geometryGeoJSON = MultiPointBuilder.getInstance().toGeoJSON(multiPoint);
```
Output:
```
{
"type": "MultiPoint",
"coordinates": [
[32.123, 24.587],
[36.1478, 29.3645]
]
}
```

#### MultiLineStringBuilder
```
MultiLineStringDto multiLine = new MultiLineStringDto();
LineStringDto lineString1 = generateLineString(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645));
LineStringDto lineString2 = generateLineString(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778));
multiLine.setLines(Arrays.asList(lineString1,lineString2));
String geometryGeoJSON = MultiLineStringBuilder.getInstance().toGeoJSON(multiLine);
```
Output:
```
{
"type": "MultiLineString",
"coordinates": [
[
 [32.123, 24.587],
 [36.1478, 29.3645]
],
[
 [12.2365, -14.8987],
 [63.254, 28.778]
]
]
}
```

#### MultiPolygonBuilder
```
PolygonDto polygon = new PolygonDto();
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
lineString1.getPositions().add(new PositionDto(44.44,45,55));
		
polygon.setLinearRings(Arrays.asList(lineString1));
 
MultiPolygonDto multiPolygon = new MultiPolygonDto();
multiPolygon.setPolygons(Arrays.asList(polygon));
	
String geometryGeoJSON = MultiPolygonBuilder.getInstance().toGeoJSON(multiPolygon);

```
Output:
```
{
"type": "MultiPolygon",
"coordinates": [
[
[
 [32.123, 24.587],
 [36.1478, 29.3645],
 [44.44, 45.0, 55.0],
 [32.123, 24.587]
]
]
]
}
```

Generate Feature GeoJSON

```java
FeatureDto feature = new FeatureDto();
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
feature.setGeometry(lineString1);
feature.setId("2423534545");
		
feature.setProperties("{}");
String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(feature);
System.out.println(featureGeoJSON);
```
Output:

```
{
"type": "Feature",
"geometry": {
"type": "LineString",
"coordinates": [
 [32.123, 24.587],
 [36.1478, 29.3645]
]
},
"properties": {},
"id": 2423534545
}

```
For detailed documentation visit https://ultimategeojson.wordpress.com/

## Built With

 * [Maven](https://maven.apache.org/) - Dependency Management
 
 
## Authors

* **Murat Öksüzer** - *Initial work* - [mokszr](https://github.com/mokszr)

See also the list of [contributors](https://github.com/mokszr/ultimate-geojson/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
 
