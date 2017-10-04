package org.ugeojson.builder.feature;

import java.util.List;

import org.ugeojson.builder.common.BuilderConstants;
import org.ugeojson.builder.common.GeoJSONBuilder;
import org.ugeojson.model.GeoJSONObjectTypeEnum;
import org.ugeojson.model.feature.FeatureCollectionDto;
import org.ugeojson.model.feature.FeatureDto;

/**
 * Build FeatureCollectionDto object to GeoJSON String
 * 
 * @author moksuzer
 *
 */
public class FeatureCollectionBuilder extends GeoJSONBuilder<FeatureCollectionDto> {

	private static final FeatureCollectionBuilder INSTANCE = new FeatureCollectionBuilder();

	private FeatureCollectionBuilder() {
	}

	public static FeatureCollectionBuilder getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ugeojson.builder.common.GeoJSONBuilder#toGeoJSON(org.ugeojson.model.
	 * GeoJSONObjectDto)
	 */
	@Override
	public String toGeoJSON(FeatureCollectionDto featureCollection) {
		if (featureCollection == null) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.FeatureCollection);

		builder.append(BuilderConstants.FEATURES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);

		List<FeatureDto> features = featureCollection.getFeatures();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				String featureGeoJSON = FeatureBuilder.getInstance().toGeoJSON(features.get(i));
				builder.append(featureGeoJSON);
				if (i < features.size() - 1) {
					builder.append(BuilderConstants.COMMA_NEWLINE);
				}
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, featureCollection.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
