package org.ugeojson.parser.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.ugeojson.model.PositionDto;
import org.ugeojson.model.geometry.GeometryCollectionDto;
import org.ugeojson.model.geometry.GeometryDto;
import org.ugeojson.model.geometry.LineStringDto;
import org.ugeojson.model.geometry.MultiLineStringDto;
import org.ugeojson.model.geometry.MultiPointDto;
import org.ugeojson.model.geometry.MultiPolygonDto;
import org.ugeojson.model.geometry.PointDto;
import org.ugeojson.model.geometry.PolygonDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JtsUGeojsonConversionUtil {

    private GeometryFactory geometryFactory;

    public JtsUGeojsonConversionUtil() {
        this.geometryFactory = new GeometryFactory();
    }

    public JtsUGeojsonConversionUtil(GeometryFactory geometryFactory) {
        this.geometryFactory = geometryFactory;
    }

    public GeometryDto toGeometryDto(Geometry geometry) {
        if (geometry instanceof Point) {
            return toPointDto((Point) geometry);
        }
        if (geometry instanceof LineString) {
            return toLineStringDto((LineString) geometry);
        }
        if (geometry instanceof Polygon) {
            return toPolygonDto((Polygon) geometry);
        }
        if (geometry instanceof MultiPoint) {
            return toMultiPointDto((MultiPoint) geometry);
        }
        if (geometry instanceof MultiLineString) {
            return toMultiLineStringDto((MultiLineString) geometry);
        }
        if (geometry instanceof MultiPolygon) {
            return toMultiPolygonDto((MultiPolygon) geometry);
        }
        if (geometry instanceof GeometryCollection) {
            return toGeometryCollectionDto((GeometryCollection) geometry);
        }
        return null;
    }

    public Geometry toJtsGeometry(GeometryDto dto) {
        if (dto instanceof PointDto) {
            return toJtsPoint((PointDto) dto);
        }
        if (dto instanceof LineStringDto) {
            return toJtsLineString((LineStringDto) dto);
        }
        if (dto instanceof PolygonDto) {
            return toJtsPolygon((PolygonDto) dto);
        }
        if (dto instanceof MultiPointDto) {
            return toJtsMultiPoint((MultiPointDto) dto);
        }
        if (dto instanceof MultiLineStringDto) {
            return toJtsMultiLineString((MultiLineStringDto) dto);
        }
        if (dto instanceof MultiPolygonDto) {
            return toJTsMultiPolygon((MultiPolygonDto) dto);
        }
        if (dto instanceof GeometryCollectionDto) {
            return toJtsGeometryCollection((GeometryCollectionDto) dto);
        }
        return null;
    }

    public Point toJtsPoint(PointDto pointDto) {
        return geometryFactory.createPoint(toJtsCoordinate(pointDto.getPosition()));
    }

    public LineString toJtsLineString(LineStringDto lineStringDto) {
        List<PositionDto> positions = lineStringDto.getPositions();
        List<Coordinate> coordinates = positions.stream().map(this::toJtsCoordinate).collect(Collectors.toList());

        return geometryFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
    }

    public LinearRing toJtsLinearRing(LineStringDto lineStringDto) {
        List<PositionDto> positions = lineStringDto.getPositions();
        List<Coordinate> coordinates = positions.stream().map(this::toJtsCoordinate).collect(Collectors.toList());

        return geometryFactory.createLinearRing(coordinates.toArray(new Coordinate[coordinates.size()]));
    }

    public Polygon toJtsPolygon(PolygonDto polygonDto) {
        List<LineStringDto> linearRings = polygonDto.getLinearRings();
        if (linearRings.size() == 1) {
            return geometryFactory.createPolygon(toJtsLinearRing(linearRings.get(0)));
        }

        List<LinearRing> jtsLinearRings = linearRings.stream().map(this::toJtsLinearRing).collect(Collectors.toList());

        LinearRing shell = jtsLinearRings.get(0);

        jtsLinearRings.remove(0);

        return geometryFactory.createPolygon(shell, jtsLinearRings.toArray(new LinearRing[jtsLinearRings.size()]));
    }

    public MultiPoint toJtsMultiPoint(MultiPointDto multiPointDto) {
        List<PositionDto> positions = multiPointDto.getPositions();
        List<Point> jtsPoints = positions.stream().map(pos -> geometryFactory.createPoint(toJtsCoordinate(pos))).collect(Collectors.toList());

        return geometryFactory.createMultiPoint(jtsPoints.toArray(new Point[jtsPoints.size()]));
    }

    public MultiLineString toJtsMultiLineString(MultiLineStringDto multiLineStringDto) {
        List<LineStringDto> lines = multiLineStringDto.getLines();
        List<LineString> jtsLines = lines.stream().map(this::toJtsLineString).collect(Collectors.toList());
        return geometryFactory.createMultiLineString(jtsLines.toArray(new LineString[jtsLines.size()]));
    }

    public MultiPolygon toJTsMultiPolygon(MultiPolygonDto multiPolygonDto) {
        List<PolygonDto> polygons = multiPolygonDto.getPolygons();
        List<Polygon> jtsPolygons = polygons.stream().map(this::toJtsPolygon).collect(Collectors.toList());
        return geometryFactory.createMultiPolygon(jtsPolygons.toArray(new Polygon[jtsPolygons.size()]));
    }

    public Coordinate toJtsCoordinate(PositionDto positionDto) {
        double[] numbers = positionDto.getNumbers();
        if (numbers.length == 3) {
            return new Coordinate(numbers[0], numbers[1], numbers[2]);
        }

        return new Coordinate(numbers[0], numbers[1]);
    }

    public PointDto toPointDto(Point p) {
        if (Double.isNaN(p.getCoordinate().getZ())) {
            return new PointDto(p.getX(), p.getY());
        } else {
            return new PointDto(p.getX(), p.getY(), p.getCoordinate().getZ());
        }
    }

    public LineStringDto toLineStringDto(LineString lineString) {
        return new LineStringDto(toPositionDtoList(lineString.getCoordinates()));
    }

    public PolygonDto toPolygonDto(Polygon polygon) {
        LineStringDto exteriorRing = toLineStringFromLinearRing(polygon.getExteriorRing());
        int numInteriorRing = polygon.getNumInteriorRing();

        List<LineStringDto> linearRings = new ArrayList<>();
        linearRings.add(exteriorRing);

        for (int i = 0; i < numInteriorRing; i++) {
            LinearRing interiorRingN = polygon.getInteriorRingN(i);
            LineStringDto interiorRing = toLineStringFromLinearRing(interiorRingN);
            linearRings.add(interiorRing);
        }

        PolygonDto polygonDto = new PolygonDto();
        polygonDto.setLinearRings(linearRings);

        return polygonDto;
    }


    public LineStringDto toLineStringFromLinearRing(LinearRing linearRing) {
        return new LineStringDto(toPositionDtoList(linearRing.getCoordinates()));
    }

    public MultiPointDto toMultiPointDto(MultiPoint multiPoint) {
        Coordinate[] coordinates = multiPoint.getCoordinates();
        List<PositionDto> positionDtos = toPositionDtoList(coordinates);
        MultiPointDto dto = new MultiPointDto();
        dto.setPositions(positionDtos);
        return dto;
    }

    public MultiLineStringDto toMultiLineStringDto(MultiLineString multiLineString) {
        List<LineStringDto> lineStringDtos = new ArrayList<>();
        int numGeometries = multiLineString.getNumGeometries();
        for (int i = 0; i < numGeometries; i++) {
            LineString lineString = (LineString) multiLineString.getGeometryN(i);
            lineStringDtos.add(toLineStringDto(lineString));
        }
        MultiLineStringDto dto = new MultiLineStringDto();
        dto.setLines(lineStringDtos);
        return dto;
    }

    public MultiPolygonDto toMultiPolygonDto(MultiPolygon multiPolygon) {
        List<PolygonDto> polygonDtos = new ArrayList<>();
        int numGeometries = multiPolygon.getNumGeometries();
        for (int i = 0; i < numGeometries; i++) {
            Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
            polygonDtos.add(toPolygonDto(polygon));
        }
        MultiPolygonDto dto = new MultiPolygonDto();
        dto.setPolygons(polygonDtos);
        return dto;
    }

    public GeometryCollectionDto toGeometryCollectionDto(GeometryCollection geometryCollection) {
        int numGeometries = geometryCollection.getNumGeometries();
        List<GeometryDto> geometries = new ArrayList<>();
        for (int i = 0; i < numGeometries; i++) {
            Geometry geometryN = geometryCollection.getGeometryN(i);
            GeometryDto geometryDto = this.toGeometryDto(geometryN);
            geometries.add(geometryDto);
        }

        GeometryCollectionDto dto = new GeometryCollectionDto();
        dto.setGeometries(geometries);
        return dto;
    }

    public GeometryCollection toJtsGeometryCollection(GeometryCollectionDto geometryCollectionDto) {
        List<GeometryDto> geometries = geometryCollectionDto.getGeometries();
        List<Geometry> jtsGeometries = geometries.stream().map(this::toJtsGeometry).collect(Collectors.toList());
        return geometryFactory.createGeometryCollection(jtsGeometries.toArray(new Geometry[jtsGeometries.size()]));
    }

    public PositionDto toPositionDto(Coordinate coordinate) {
        if (Double.isNaN(coordinate.getZ())) {
            return new PositionDto(coordinate.getX(), coordinate.getY());
        } else {
            return new PositionDto(coordinate.getX(), coordinate.getY(), coordinate.getZ());
        }
    }

    private List<PositionDto> toPositionDtoList(Coordinate[] coordinates) {
        List<PositionDto> listDto = new ArrayList<>();

        for (Coordinate c : coordinates) {
            listDto.add(toPositionDto(c));
        }

        return listDto;
    }

}
