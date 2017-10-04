# ultimate-geojson

**ultimate-geojeson** is the ultimate GeoJSON java library to generate & parse GeoJSON and to do geospatial operations on geometry objects.

**Table of Contents**  

- [ultimate-geojson](#ultimate-geojson)
	- [About GeoJSON](#about-geojson)
	- [Data Models](#data-models)
	- [How To Parse GeoJSON](#how-to-parse-geojson)
	- [How To Build (generate) GeoJSON](#how-to-build-generate-geojson)
		- [PointBuilder](#pointbuilder)
		- [LineStringBuilder](#linestringbuilder)
	 	- [PolygonBuilder](#polygonbuilder)
	 	- [MultiPointBuilder](#multipointbuilder)
	 	- [MultiLineStringBuilder](#multilinestringbuilder)
	 	- [MultiPolygonBuilder](#multipolygonbuilder)
	 	- [GeometryCollectionBuilder](#geometrycollectionbuilder)
	 	- [FeatureBuilder](#featurebuilder)
	 	- [FeatureCollectionBuilder](#featurecollectionbuilder)
	- [Built With](#built-with)
	- [Authors](#authors)
	- [License](#license)
	- [Acknowledgments](#acknowledgments)

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
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(12.2365, -14.8987),new PositionDto(63.254, 28.778)));
		
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

#### GeometryCollectionBuilder
```
PointDto point = new PointDto(101.2471,37.2368);
LineStringDto line = new LineStringDto(Arrays.asList(new PositionDto(101.01, 58.0147),new PositionDto(24.014, 19.364)));
		
GeometryCollectionDto geometryCollection = new GeometryCollectionDto();
geometryCollection.setGeometries(Arrays.asList(point,line));
String geometryGeoJSON = GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollection);
System.out.println(geometryGeoJSON);
```
Output:
```
{
"type": "GeometryCollection",
"geometries": [{
"type": "Point",
"coordinates": [101.2471, 37.2368]
},{
"type": "LineString",
"coordinates": [
 [101.01, 58.0147],
 [24.014, 19.364]
]
}]
}
```

#### FeatureBuilder
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

#### FeatureCollectionBuilder
```
FeatureDto feature = new FeatureDto();
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
feature.setGeometry(lineString1);
feature.setId("2423534545");
feature.setProperties("{}");
		
FeatureDto feature2 = new FeatureDto();
LineStringDto lineString2 = new LineStringDto(Arrays.asList(new PositionDto(47.47, 59.457),new PositionDto(86.3698, 45.10471)));
feature2.setGeometry(lineString2);
feature2.setId("\"myFeatureId\"");
		
feature2.setProperties("{\"color\": \"red\"}");
FeatureCollectionDto featureCollection = new FeatureCollectionDto();
featureCollection.setFeatures(Arrays.asList(feature,feature2));
		
String featureCollectionGeoJSON = FeatureCollectionBuilder.getInstance().toGeoJSON(featureCollection);
System.out.println(featureCollectionGeoJSON);
```
Output:
```
{
"type": "FeatureCollection",
"features": [{
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
},
{
"type": "Feature",
"geometry": {
"type": "LineString",
"coordinates": [
 [47.47, 59.457],
 [86.3698, 45.10471]
]
},
"properties": {"color": "red"},
"id": "myFeatureId"
}]
}
```


## Built With

 * [Maven](https://maven.apache.org/) - Dependency Management
 
 
## Authors

* **Murat Öksüzer** - *Initial work* - [mokszr](https://github.com/mokszr)
([Personel Site: http://www.muratoksuzer.com/](http://www.muratoksuzer.com/))

See also the list of [contributors](https://github.com/mokszr/ultimate-geojson/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
 
