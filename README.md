# ultimate-geojson

Ultimate GeoJSON java library to build, parse GeoJSON and more

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

 
```
@Test public void
build_feature(){
	FeatureDto feature = new FeatureDto();
	LineStringDto lineString1 = new LineStringDto(Arrays.asList(new PositionDto(32.123, 24.587),new PositionDto(36.1478, 29.3645)));
	feature.setGeometry(lineString1);
	feature.setId("2423534545");
		
	feature.setProperties("{}");
	String featureGeoJSON = FeatureBuilder.getInstance().toFeatureGeoJSON(feature);
	System.out.println(featureGeoJSON);
	}
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
 

## Built With

 * [Maven](https://maven.apache.org/) - Dependency Management
 
 
## Authors

* **Murat Öksüzer** - *Initial work* - [mokszr](https://github.com/mokszr)

See also the list of [contributors](https://github.com/mokszr/ultimate-geojson/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
 
