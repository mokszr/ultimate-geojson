# ultimate-geojson

Ultimate GeoJSON java library to build, parse GeoJSON and more

## How To Parse GeoJSON

```
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
Generate Polygon GeoJson from PolygonDto you populate

```
PolygonDto polygon = new PolygonDto();
LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645),new PositionDto(44.44,45,55)));
				
polygon.setLinearRings(Arrays.asList(lineString1));
String geometryGeoJSON = UltimateGeoJSONBuilder.getInstance().toGeoJSON(polygon);
System.out.println(geometryGeoJSON);
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

Generate Feature GeoJSON

```
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
 
