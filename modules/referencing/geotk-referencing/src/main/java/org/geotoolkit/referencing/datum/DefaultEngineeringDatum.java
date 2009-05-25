/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2001-2009, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2009, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    This package contains documentation from OpenGIS specifications.
 *    OpenGIS consortium's work is fully acknowledged here.
 */
package org.geotoolkit.referencing.datum;

import java.util.Collections;
import java.util.Map;

import org.opengis.referencing.datum.EngineeringDatum;
import org.geotoolkit.referencing.AbstractIdentifiedObject;
import org.geotoolkit.resources.Vocabulary;
import org.geotoolkit.io.wkt.Formatter;


/**
 * Defines the origin of an engineering coordinate reference system. An engineering datum is used
 * in a region around that origin. This origin can be fixed with respect to the earth (such as a
 * defined point at a construction site), or be a defined point on a moving vehicle (such as on a
 * ship or satellite).
 *
 * @author Martin Desruisseaux (IRD, Geomatys)
 * @version 3.00
 *
 * @since 1.2
 * @module
 */
public class DefaultEngineeringDatum extends AbstractDatum implements EngineeringDatum {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 1498304918725248637L;

    /**
     * An engineering datum for unknow coordinate reference system. Such CRS are usually
     * assumed cartesian, but will not have any transformation path to other CRS.
     *
     * @see org.geotoolkit.referencing.crs.DefaultEngineeringCRS#CARTESIAN_2D
     * @see org.geotoolkit.referencing.crs.DefaultEngineeringCRS#CARTESIAN_3D
     */
    public static final DefaultEngineeringDatum UNKNOW =
            new DefaultEngineeringDatum(name(Vocabulary.Keys.UNKNOW));

    /**
     * Constructs a new object in which every attributes are set to a default value.
     * <strong>This is not a valid object.</strong> This constructor is strictly
     * reserved to JAXB, which will assign values to the fields using reflexion.
     */
    private DefaultEngineeringDatum() {
        this(org.geotoolkit.internal.referencing.NullReferencingObject.INSTANCE);
    }

    /**
     * Constructs a new datum with the same values than the specified one.
     * This copy constructor provides a way to wrap an arbitrary implementation into a
     * Geotoolkit one or a user-defined one (as a subclass), usually in order to leverage
     * some implementation-specific API. This constructor performs a shallow copy,
     * i.e. the properties are not cloned.
     *
     * @param datum The datum to copy.
     *
     * @since 2.2
     */
    public DefaultEngineeringDatum(final EngineeringDatum datum) {
        super(datum);
    }

    /**
     * Constructs an engineering datum from a name.
     *
     * @param name The datum name.
     */
    public DefaultEngineeringDatum(final String name) {
        this(Collections.singletonMap(NAME_KEY, name));
    }

    /**
     * Constructs an engineering datum from a set of properties. The properties map is given
     * unchanged to the {@linkplain AbstractDatum#AbstractDatum(Map) super-class constructor}.
     *
     * @param properties Set of properties. Should contains at least {@code "name"}.
     */
    public DefaultEngineeringDatum(final Map<String,?> properties) {
        super(properties);
    }

    /**
     * Compare this datum with the specified object for equality.
     *
     * @param  object The object to compare to {@code this}.
     * @param  compareMetadata {@code true} for performing a strict comparison, or
     *         {@code false} for comparing only properties relevant to transformations.
     * @return {@code true} if both objects are equal.
     */
    @Override
    public boolean equals(final AbstractIdentifiedObject object, final boolean compareMetadata) {
        if (object == this) {
            return true; // Slight optimization.
        }
        return super.equals(object, compareMetadata);
    }

    /**
     * Formats the inner part of a
     * <A HREF="http://geoapi.sourceforge.net/snapshot/javadoc/org/opengis/referencing/doc-files/WKT.html#LOCAL_DATUM"><cite>Well
     * Known Text</cite> (WKT)</A> element.
     *
     * @param  formatter The formatter to use.
     * @return The WKT element name, which is {@code "LOCAL_DATUM"}.
     */
    @Override
    public String formatWKT(final Formatter formatter) {
        super.formatWKT(formatter);
        return "LOCAL_DATUM";
    }
}
