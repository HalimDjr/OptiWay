import {
  ZodArray,
  ZodDiscriminatedUnion,
  ZodNullable,
  ZodObject,
  ZodTuple,
  _enum,
  array,
  discriminatedUnion,
  json,
  literal,
  looseObject,
  never,
  number,
  object,
  record,
  string,
  tuple,
  union
} from "./chunk-EPQWOPXQ.js";
import {
  __spreadProps,
  __spreadValues
} from "./chunk-R327OCYJ.js";

// node_modules/zod-geojson/lib/geometry/position.mjs
var GeoJSON2DPositionSchema = tuple([number(), number()]);
var GeoJSON3DPositionSchema = tuple([number(), number(), number()]);
var GeoJSONPositionSchema = number().array().min(2).max(3);
var GeoJSONAnyPositionSchema = number().array().min(2);

// node_modules/zod-geojson/lib/bbox.mjs
var _2DBBoxSchema = tuple([number(), number(), number(), number()]);
var _3DBBoxSchema = tuple([number(), number(), number(), number(), number(), number()]);
var _2DOr3DBBoxSchema = union([_2DBBoxSchema, _3DBBoxSchema]);
var GeoJSONBBoxGenericSchema = (positionSchema) => {
  if (positionSchema instanceof ZodArray) {
    return _2DOr3DBBoxSchema;
  }
  if (positionSchema instanceof ZodTuple) {
    const itemCount = positionSchema.def.items.length;
    return number().array().length(itemCount * 2);
  }
  return number().array();
};
var GeoJSON2DBBoxSchema = GeoJSONBBoxGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DBBoxSchema = GeoJSONBBoxGenericSchema(GeoJSON3DPositionSchema);
var GeoJSONBBoxSchema = GeoJSONBBoxGenericSchema(GeoJSONPositionSchema);

// node_modules/zod-geojson/lib/base.mjs
var GeoJSONBaseSchema = (positionSchema) => object({
  bbox: GeoJSONBBoxGenericSchema(positionSchema).optional()
});

// node_modules/zod-geojson/lib/geometry/type.mjs
var GeoJSONGeometryTypeSchema = _enum([
  "Point",
  "MultiPoint",
  "LineString",
  "MultiLineString",
  "Polygon",
  "MultiPolygon",
  "GeometryCollection"
]);
var GeoJSONGeometryType = GeoJSONGeometryTypeSchema.enum;

// node_modules/zod-geojson/lib/geometry/validation/bbox.mjs
var getInvalidBBoxIssue = (ctx) => ({
  code: "custom",
  message: "Invalid bbox. BBox length must be 2 * n, where n is the dimension of the geometry. BBox must be a valid extent for the geometry.",
  input: ctx.value,
  continue: true
});
function validBBoxForPosition({ bbox, coordinates }) {
  if (bbox == null)
    return true;
  if (coordinates == null)
    return false;
  const dimension = coordinates.length;
  if (bbox.length !== dimension * 2) {
    return false;
  }
  return bbox.every((value, index) => value === coordinates[index % dimension]);
}
function validBBoxForPositionList({ bbox, coordinates }) {
  if (bbox == null)
    return true;
  if (coordinates == null)
    return false;
  const dimension = coordinates[0].length;
  if (bbox.length !== dimension * 2) {
    return false;
  }
  return bboxEquals(bbox, updateBBoxForPositionList([], coordinates));
}
function validBBoxForPositionGrid({ bbox, coordinates }) {
  if (bbox == null)
    return true;
  if (coordinates == null)
    return false;
  const dimension = coordinates[0][0].length;
  if (bbox.length !== 2 * dimension) {
    return false;
  }
  return bboxEquals(bbox, updateBBoxForPositionGrid([], coordinates));
}
function validBBoxForPositionGridList({ bbox, coordinates }) {
  if (bbox == null)
    return true;
  if (coordinates == null)
    return false;
  const dimension = coordinates[0][0][0].length;
  if (bbox.length !== 2 * dimension) {
    return false;
  }
  return bboxEquals(bbox, updateBBoxForPositionGridList([], coordinates));
}
function validBBoxForCollection({ bbox, geometries }) {
  if (!bbox) {
    return true;
  }
  const expectedBBox = getBBoxForGeometries(geometries);
  return bboxEquals(bbox, expectedBBox);
}
function getBBoxForGeometry(geometry) {
  switch (geometry.type) {
    case "Point":
      return updateBBoxForPosition([], geometry.coordinates);
    case "MultiPoint":
    case "LineString":
      return updateBBoxForPositionList([], geometry.coordinates);
    case "MultiLineString":
    case "Polygon":
      return updateBBoxForPositionGrid([], geometry.coordinates);
    case "MultiPolygon":
      return updateBBoxForPositionGridList([], geometry.coordinates);
    case "GeometryCollection":
      return getBBoxForGeometries(geometry.geometries);
  }
}
function getBBoxForGeometries(geometries) {
  return mergeBBoxs(geometries.map(getBBoxForGeometry));
}
function bboxEquals(bbox1, bbox2) {
  if (bbox1.length !== bbox2.length) {
    return false;
  }
  return bbox1.every((value, index) => value === bbox2[index]);
}
function updateBBoxForPositionGridList(currentBBox, positions) {
  positions === null || positions === void 0 ? void 0 : positions.forEach((positionGrid) => updateBBoxForPositionGrid(currentBBox, positionGrid));
  return currentBBox;
}
function updateBBoxForPositionGrid(currentBBox, positions) {
  positions === null || positions === void 0 ? void 0 : positions.forEach((positionList) => updateBBoxForPositionList(currentBBox, positionList));
  return currentBBox;
}
function updateBBoxForPositionList(currentBBox, positions) {
  positions === null || positions === void 0 ? void 0 : positions.forEach((position) => updateBBoxForPosition(currentBBox, position));
  return currentBBox;
}
function updateBBoxForPosition(currentBBox, position) {
  var _a;
  const dimension = (_a = position === null || position === void 0 ? void 0 : position.length) !== null && _a !== void 0 ? _a : 0;
  position === null || position === void 0 ? void 0 : position.forEach((value, index) => {
    const iMin = currentBBox[index];
    const iMax = currentBBox[index + dimension];
    if (iMin === void 0 || value < iMin) {
      currentBBox[index] = value;
    }
    if (iMax === void 0 || value > iMax) {
      currentBBox[index + dimension] = value;
    }
  });
  return currentBBox;
}
function mergeBBoxs(bboxs) {
  const dimension = bboxs[0].length / 2;
  const mergedBBox = [];
  for (let i = 0; i < dimension; i++) {
    mergedBBox[i] = Math.min(...bboxs.map((bbox) => bbox[i]));
    mergedBBox[i + dimension] = Math.max(...bboxs.map((bbox) => bbox[i + dimension]));
  }
  return mergedBBox;
}

// node_modules/zod-geojson/lib/geometry/validation/dimension.mjs
var getInvalidDimensionIssue = (ctx) => ({
  code: "custom",
  message: "Invalid dimensions. All positions in the geometry must have the same dimension.",
  input: ctx.value,
  continue: true
});
var getInvalidGeometryCollectionDimensionIssue = (ctx) => ({
  code: "custom",
  message: "Invalid geometry collection dimensions. All geometries must have the same dimension.",
  input: ctx.value,
  continue: true
});
function validDimensionsForPositionList({ coordinates }) {
  const dimension = coordinates[0].length;
  return sameDimensionsForPositions(dimension)(coordinates);
}
function validDimensionsForPositionGrid({ coordinates }) {
  let dimension = coordinates[0][0].length;
  return sameDimensionsForPositionGrid(dimension)(coordinates);
}
function validDimensionsForPositionGridList({ coordinates }) {
  let dimension = coordinates[0][0][0].length;
  return sameDimensionsForPositionGrids(dimension)(coordinates);
}
function validDimensionsForCollection({ geometries }) {
  if (geometries == null)
    return false;
  let dimension = getDimensionForGeometry(geometries[0]);
  return geometries.slice(1).every((geometry) => getDimensionForGeometry(geometry) === dimension);
}
function getDimensionForGeometry(geometry) {
  var _a, _b, _c, _d, _e, _f, _g, _h;
  switch (geometry.type) {
    case "Point":
      return (_b = (_a = geometry.coordinates) === null || _a === void 0 ? void 0 : _a.length) !== null && _b !== void 0 ? _b : 0;
    case "MultiPoint":
    case "LineString":
      return (_d = (_c = geometry.coordinates) === null || _c === void 0 ? void 0 : _c[0].length) !== null && _d !== void 0 ? _d : 0;
    case "MultiLineString":
    case "Polygon":
      return (_f = (_e = geometry.coordinates) === null || _e === void 0 ? void 0 : _e[0][0].length) !== null && _f !== void 0 ? _f : 0;
    case "MultiPolygon":
      return (_h = (_g = geometry.coordinates) === null || _g === void 0 ? void 0 : _g[0][0][0].length) !== null && _h !== void 0 ? _h : 0;
    case "GeometryCollection":
      return getDimensionForGeometry(geometry.geometries[0]);
  }
}
function sameDimensionsForPosition(dimension) {
  return (position) => position.length === dimension;
}
function sameDimensionsForPositions(dimension) {
  return (positions) => positions.every(sameDimensionsForPosition(dimension));
}
function sameDimensionsForPositionGrid(dimension) {
  return (positionGrid) => positionGrid.every(sameDimensionsForPositions(dimension));
}
function sameDimensionsForPositionGrids(dimension) {
  return (positionGrids) => positionGrids.every(sameDimensionsForPositionGrid(dimension));
}

// node_modules/zod-geojson/lib/geometry/geometry_collection.mjs
var GeoJSONGeometryCollectionGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.GeometryCollection),
  coordinates: never({ error: "GeoJSON geometry collection cannot have a 'coordinates' key" }).optional(),
  geometry: never({ error: "GeoJSON geometry collection cannot have a 'geometry' key" }).optional(),
  properties: never({ error: "GeoJSON geometry collection cannot have a 'properties' key" }).optional(),
  features: never({ error: "GeoJSON geometry collection cannot have a 'features' key" }).optional(),
  // > The value of "geometries" is an array. Each element of this array is a
  //   GeoJSON Geometry object. It is possible for this array to be empty. (RFC 7946, section 3.1.8)
  // > To maximize interoperability, implementations SHOULD avoid nested
  //    GeometryCollections. (RFC 7946, section 3.1.8)
  get geometries() {
    return array(GeoJSONGeometryGenericSchema(positionSchema));
  }
})).check((ctx) => {
  if (!ctx.value.geometries.length) {
    return;
  }
  if (!validDimensionsForCollection(ctx.value)) {
    ctx.issues.push(getInvalidGeometryCollectionDimensionIssue(ctx));
  }
  if (!validBBoxForCollection(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
  }
});
var GeoJSONGeometryCollectionSchema = GeoJSONGeometryCollectionGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DGeometryCollectionSchema = GeoJSONGeometryCollectionGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DGeometryCollectionSchema = GeoJSONGeometryCollectionGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/helper/base.mjs
var GeoJSONGeometryBaseSchema = (positionSchema) => object(__spreadProps(__spreadValues({}, GeoJSONBaseSchema(positionSchema).shape), {
  geometry: never({ error: "GeoJSON geometry cannot have a 'geometry' key" }).optional(),
  properties: never({ error: "GeoJSON geometry cannot have a 'properties' key" }).optional(),
  features: never({ error: "GeoJSON geometry cannot have a 'features' key" }).optional(),
  geometries: never({ error: "GeoJSON geometry cannot have a 'geometries' key" }).optional()
}));

// node_modules/zod-geojson/lib/geometry/line_string.mjs
var GeoJSONLineStringGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.LineString),
  // > For type "LineString", the "coordinates" member is an array of two or
  //   more positions. (RFC 7946, section 3.1.4)
  coordinates: positionSchema.array().min(2)
})).check((ctx) => {
  if (!ctx.value.coordinates.length) {
    return;
  }
  if (!validDimensionsForPositionList(ctx.value)) {
    ctx.issues.push(getInvalidDimensionIssue(ctx));
    return;
  }
  if (!validBBoxForPositionList(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONLineStringSchema = GeoJSONLineStringGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DLineStringSchema = GeoJSONLineStringGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DLineStringSchema = GeoJSONLineStringGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/multi_line_string.mjs
var GeoJSONMultiLineStringGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.MultiLineString),
  // We allow an empty coordinates array
  // > GeoJSON processors MAY interpret Geometry objects with empty "coordinates"
  //   arrays as null objects. (RFC 7946, section 3.1)
  coordinates: GeoJSONLineStringGenericSchema(positionSchema).shape.coordinates.array()
})).check((ctx) => {
  if (!ctx.value.coordinates.length) {
    return;
  }
  if (!validDimensionsForPositionGrid(ctx.value)) {
    ctx.issues.push(getInvalidDimensionIssue(ctx));
    return;
  }
  if (!validBBoxForPositionGrid(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONMultiLineStringSchema = GeoJSONMultiLineStringGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DMultiLineStringSchema = GeoJSONMultiLineStringGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DMultiLineStringSchema = GeoJSONMultiLineStringGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/multi_point.mjs
var GeoJSONMultiPointGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.MultiPoint),
  // We allow an empty coordinates array
  // > GeoJSON processors MAY interpret Geometry objects with empty "coordinates"
  //   arrays as null objects. (RFC 7946, section 3.1)
  coordinates: array(positionSchema)
})).check((ctx) => {
  if (!ctx.value.coordinates.length) {
    return;
  }
  if (!validDimensionsForPositionList(ctx.value)) {
    ctx.issues.push(getInvalidDimensionIssue(ctx));
    return;
  }
  if (!validBBoxForPositionList(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONMultiPointSchema = GeoJSONMultiPointGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DMultiPointSchema = GeoJSONMultiPointGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DMultiPointSchema = GeoJSONMultiPointGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/validation/linear_ring.mjs
var getInvalidPolygonLinearRingIssue = (ctx) => ({
  code: "custom",
  message: "Invalid polygon. Each ring inside a polygon must form a linear ring.",
  input: ctx.value,
  continue: true
});
var getInvalidMultiPolygonLinearRingIssue = (ctx) => ({
  code: "custom",
  message: "Invalid multi polygon. Each polygon inside the multi polygon must be made out of linear rings.",
  input: ctx.value,
  continue: true
});
function validLinearRing(linearRing) {
  const firstPosition = linearRing[0];
  const lastPosition = linearRing[linearRing.length - 1];
  return firstPosition.every((value, index) => value === lastPosition[index]);
}
function validPolygonRings({ coordinates: rings }) {
  return rings.every(validLinearRing);
}
function validMultiPolygonLinearRings({ coordinates }) {
  return coordinates.every((polygon) => validPolygonRings({ coordinates: polygon }));
}

// node_modules/zod-geojson/lib/geometry/polygon.mjs
var GeoJSONPolygonGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.Polygon),
  // We allow an empty coordinates array
  // > GeoJSON processors MAY interpret Geometry objects with empty "coordinates"
  //   arrays as null objects. (RFC 7946, section 3.1)
  // > A linear ring is a closed LineString with four or more positions (RFC 7946, section 3.1.6)
  coordinates: array(positionSchema.array().min(4))
})).check((ctx) => {
  if (!ctx.value.coordinates.length) {
    return;
  }
  if (!validDimensionsForPositionGrid(ctx.value)) {
    ctx.issues.push(getInvalidDimensionIssue(ctx));
    return;
  }
  if (!validPolygonRings(ctx.value)) {
    ctx.issues.push(getInvalidPolygonLinearRingIssue(ctx));
    return;
  }
  if (!validBBoxForPositionGrid(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONPolygonSchema = GeoJSONPolygonGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DPolygonSchema = GeoJSONPolygonGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DPolygonSchema = GeoJSONPolygonGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/multi_polygon.mjs
var GeoJSONMultiPolygonGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.MultiPolygon),
  // We allow an empty coordinates array:
  // > GeoJSON processors MAY interpret Geometry objects with empty "coordinates"
  //   arrays as null objects. (RFC 7946, section 3.1)
  coordinates: GeoJSONPolygonGenericSchema(positionSchema).shape.coordinates.array()
})).check((ctx) => {
  if (!ctx.value.coordinates.length) {
    return;
  }
  if (!validDimensionsForPositionGridList(ctx.value)) {
    ctx.issues.push(getInvalidDimensionIssue(ctx));
    return;
  }
  if (!validMultiPolygonLinearRings(ctx.value)) {
    ctx.issues.push(getInvalidMultiPolygonLinearRingIssue(ctx));
    return;
  }
  if (!validBBoxForPositionGridList(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONMultiPolygonSchema = GeoJSONMultiPolygonGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DMultiPolygonSchema = GeoJSONMultiPolygonGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DMultiPolygonSchema = GeoJSONMultiPolygonGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/point.mjs
var GeoJSONPointGenericSchema = (positionSchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONGeometryBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONGeometryTypeSchema.enum.Point),
  coordinates: positionSchema
})).check((ctx) => {
  if (!validBBoxForPosition(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
  }
});
var GeoJSONPointSchema = GeoJSONPointGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DPointSchema = GeoJSONPointGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DPointSchema = GeoJSONPointGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/geometry/geometry.mjs
var GeoJSONGeometryGenericSchema = (positionSchema) => discriminatedUnion("type", [
  GeoJSONPointGenericSchema(positionSchema),
  GeoJSONLineStringGenericSchema(positionSchema),
  GeoJSONMultiPointGenericSchema(positionSchema),
  GeoJSONPolygonGenericSchema(positionSchema),
  GeoJSONMultiLineStringGenericSchema(positionSchema),
  GeoJSONMultiPolygonGenericSchema(positionSchema),
  GeoJSONGeometryCollectionGenericSchema(positionSchema)
]);
var GeoJSONGeometrySchema = GeoJSONGeometryGenericSchema(GeoJSONPositionSchema);
var GeoJSON2DGeometrySchema = GeoJSONGeometryGenericSchema(GeoJSON2DPositionSchema);
var GeoJSON3DGeometrySchema = GeoJSONGeometryGenericSchema(GeoJSON3DPositionSchema);

// node_modules/zod-geojson/lib/properties.mjs
var GeoJSONPropertiesSchema = record(string(), json());

// node_modules/zod-geojson/lib/type.mjs
var GeoJSONTypeSchema = _enum(["Feature", "FeatureCollection", ...GeoJSONGeometryTypeSchema.options]);
var GeoJSONType = GeoJSONTypeSchema.enum;

// node_modules/zod-geojson/lib/validation/types.mjs
function getGeometries({ features }) {
  var _a;
  return (_a = features === null || features === void 0 ? void 0 : features.map((feature) => feature.geometry).filter((x) => x != null)) !== null && _a !== void 0 ? _a : [];
}

// node_modules/zod-geojson/lib/validation/bbox.mjs
function validBBoxForFeature({ bbox, geometry }) {
  if (bbox == null || geometry == null) {
    return true;
  }
  const expectedBBox = getBBoxForGeometry(geometry);
  return bboxEquals(expectedBBox, bbox);
}
function validBBoxForFeatureCollection({ features, bbox }) {
  if (!bbox) {
    return true;
  }
  const expectedBBox = getBBoxForGeometries(getGeometries({ features }));
  return bboxEquals(expectedBBox, bbox);
}

// node_modules/zod-geojson/lib/feature.mjs
var GeoJSONFeatureGenericSchema = (positionSchema, propertiesSchema, geometrySchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONBaseSchema(positionSchema).shape), {
  id: string().or(number()).optional(),
  type: literal(GeoJSONTypeSchema.enum.Feature),
  geometry: geometrySchema,
  properties: propertiesSchema,
  coordinates: never({ error: "GeoJSON Feature cannot have a 'coordinates' key" }).optional(),
  features: never({ error: "GeoJSON Feature cannot have a 'features' key" }).optional(),
  geometries: never({ error: "GeoJSON Feature cannot have a 'geometries' key" }).optional()
})).check((ctx) => {
  if (!validBBoxForFeature(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
  }
});
var GeoJSONFeatureSchema = GeoJSONFeatureGenericSchema(GeoJSONPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSONGeometrySchema);
var GeoJSON2DFeatureSchema = GeoJSONFeatureGenericSchema(GeoJSON2DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON2DGeometrySchema);
var GeoJSON3DFeatureSchema = GeoJSONFeatureGenericSchema(GeoJSON3DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON3DGeometrySchema);

// node_modules/zod-geojson/lib/validation/dimension.mjs
var getInvalidFeatureCollectionDimensionsIssue = (ctx) => ({
  code: "custom",
  message: "Invalid dimensions. All features in feature collection must have the same dimension.",
  input: ctx.value,
  continue: true
});
function validDimensionsForFeatureCollection(collection) {
  const geometries = getGeometries(collection);
  if (geometries.length === 0)
    return true;
  const dimension = getDimensionForGeometry(geometries[0]);
  return geometries.slice(1).every((geometry) => getDimensionForGeometry(geometry) === dimension);
}

// node_modules/zod-geojson/lib/feature_collection.mjs
var GeoJSONFeatureCollectionGenericSchema = (positionSchema, propertiesSchema, geometrySchema) => looseObject(__spreadProps(__spreadValues({}, GeoJSONBaseSchema(positionSchema).shape), {
  type: literal(GeoJSONTypeSchema.enum.FeatureCollection),
  features: array(GeoJSONFeatureGenericSchema(positionSchema, propertiesSchema, geometrySchema)),
  coordinates: never({ error: "GeoJSON feature collection cannot have a 'coordinates' key" }).optional(),
  geometry: never({ error: "GeoJSON feature collection cannot have a 'geometry' key" }).optional(),
  properties: never({ error: "GeoJSON feature collection cannot have a 'properties' key" }).optional(),
  geometries: never({ error: "GeoJSON feature collection cannot have a 'geometries' key" }).optional()
})).check((ctx) => {
  if (!ctx.value.features.length) {
    return;
  }
  if (!validDimensionsForFeatureCollection(ctx.value)) {
    ctx.issues.push(getInvalidFeatureCollectionDimensionsIssue(ctx));
    return;
  }
  if (!validBBoxForFeatureCollection(ctx.value)) {
    ctx.issues.push(getInvalidBBoxIssue(ctx));
    return;
  }
});
var GeoJSONFeatureCollectionSchema = GeoJSONFeatureCollectionGenericSchema(GeoJSONPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSONGeometrySchema);
var GeoJSON2DFeatureCollectionSchema = GeoJSONFeatureCollectionGenericSchema(GeoJSON2DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON2DGeometrySchema);
var GeoJSON3DFeatureCollectionSchema = GeoJSONFeatureCollectionGenericSchema(GeoJSON3DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON3DGeometrySchema);

// node_modules/zod-geojson/lib/geojson.mjs
var GeoJSONGenericSchema = (positionSchema, propertiesSchema, geometrySchema) => discriminatedUnion("type", [
  getDiscriminableGeometrySchema(geometrySchema),
  GeoJSONFeatureGenericSchema(positionSchema, propertiesSchema, geometrySchema),
  GeoJSONFeatureCollectionGenericSchema(positionSchema, propertiesSchema, geometrySchema)
]);
var GeoJSONSchema = GeoJSONGenericSchema(GeoJSONPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSONGeometrySchema);
var GeoJSON2DSchema = GeoJSONGenericSchema(GeoJSON2DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON2DGeometrySchema);
var GeoJSON3DSchema = GeoJSONGenericSchema(GeoJSON3DPositionSchema, GeoJSONPropertiesSchema.nullable(), GeoJSON3DGeometrySchema);
function getDiscriminableGeometrySchema(geometrySchema) {
  const schema = geometrySchema instanceof ZodNullable ? geometrySchema.unwrap() : geometrySchema;
  if (schema instanceof ZodObject || schema instanceof ZodDiscriminatedUnion) {
    return schema;
  }
  throw new Error(`GeoJSONGenericSchema received invalid geometry schema. Schema must be either a ZodObject or ZodDiscriminatedUnion, or a ZodNullable wrapping one of those. Received: ${schema._zod.def.type}.`);
}
export {
  GeoJSON2DBBoxSchema,
  GeoJSON2DFeatureCollectionSchema,
  GeoJSON2DFeatureSchema,
  GeoJSON2DGeometryCollectionSchema,
  GeoJSON2DGeometrySchema,
  GeoJSON2DLineStringSchema,
  GeoJSON2DMultiLineStringSchema,
  GeoJSON2DMultiPointSchema,
  GeoJSON2DMultiPolygonSchema,
  GeoJSON2DPointSchema,
  GeoJSON2DPolygonSchema,
  GeoJSON2DPositionSchema,
  GeoJSON2DSchema,
  GeoJSON3DBBoxSchema,
  GeoJSON3DFeatureCollectionSchema,
  GeoJSON3DFeatureSchema,
  GeoJSON3DGeometryCollectionSchema,
  GeoJSON3DGeometrySchema,
  GeoJSON3DLineStringSchema,
  GeoJSON3DMultiLineStringSchema,
  GeoJSON3DMultiPointSchema,
  GeoJSON3DMultiPolygonSchema,
  GeoJSON3DPointSchema,
  GeoJSON3DPolygonSchema,
  GeoJSON3DPositionSchema,
  GeoJSON3DSchema,
  GeoJSONBBoxSchema,
  GeoJSONFeatureCollectionGenericSchema,
  GeoJSONFeatureCollectionSchema,
  GeoJSONFeatureGenericSchema,
  GeoJSONFeatureSchema,
  GeoJSONGenericSchema,
  GeoJSONGeometryCollectionGenericSchema,
  GeoJSONGeometryCollectionSchema,
  GeoJSONGeometryGenericSchema,
  GeoJSONGeometrySchema,
  GeoJSONGeometryType,
  GeoJSONGeometryTypeSchema,
  GeoJSONLineStringGenericSchema,
  GeoJSONLineStringSchema,
  GeoJSONMultiLineStringGenericSchema,
  GeoJSONMultiLineStringSchema,
  GeoJSONMultiPointGenericSchema,
  GeoJSONMultiPointSchema,
  GeoJSONMultiPolygonGenericSchema,
  GeoJSONMultiPolygonSchema,
  GeoJSONPointGenericSchema,
  GeoJSONPointSchema,
  GeoJSONPolygonGenericSchema,
  GeoJSONPolygonSchema,
  GeoJSONPositionSchema,
  GeoJSONPropertiesSchema,
  GeoJSONSchema,
  GeoJSONType,
  GeoJSONTypeSchema
};
//# sourceMappingURL=zod-geojson.js.map
