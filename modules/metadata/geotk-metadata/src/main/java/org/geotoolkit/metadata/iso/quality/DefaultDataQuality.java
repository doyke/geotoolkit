/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2004-2009, Open Source Geospatial Foundation (OSGeo)
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
package org.geotoolkit.metadata.iso.quality;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.quality.Element;
import org.opengis.metadata.quality.Scope;
import org.geotoolkit.metadata.iso.MetadataEntity;


/**
 * Quality information for the data specified by a data quality scope.
 *
 * @author Martin Desruisseaux (IRD)
 * @author Touraïvane (IRD)
 * @version 3.00
 *
 * @since 2.1
 * @module
 */
@XmlRootElement(name = "DQ_DataQuality")
public class DefaultDataQuality extends MetadataEntity implements DataQuality {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 7964896551368382214L;

    /**
     * The specific data to which the data quality information applies.
     */
    private Scope scope;

    /**
     * Quantitative quality information for the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    private Collection<Element> reports;

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    private Lineage lineage;

    /**
     * Constructs an initially empty data quality.
     */
    public DefaultDataQuality() {
    }

    /**
     * Constructs a metadata entity initialized with the values from the specified metadata.
     *
     * @param source The metadata to copy.
     *
     * @since 2.4
     */
    public DefaultDataQuality(final DataQuality source) {
        super(source);
    }

    /**
     * Creates a data quality initialized to the given scope.
     *
     * @param scope The specific data to which the data quality information applies.
     */
    public DefaultDataQuality(Scope scope) {
        setScope(scope);
    }

    /**
     * Returns the specific data to which the data quality information applies.
     */
    @Override
/// @XmlElement(name = "scope", required = true)
    public Scope getScope() {
        return scope;
    }

    /**
     * Sets the specific data to which the data quality information applies.
     *
     * @param newValue The new scope.
     */
    public synchronized void setScope(final Scope newValue) {
        checkWritePermission();
        scope = newValue;
    }

    /**
     * Returns the quantitative quality information for the data specified by the
     * scope. Should be provided only if {@linkplain Scope#getLevel scope level}
     * is {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    @Override
    public synchronized Collection<Element> getReports() {
        return reports = nonNullCollection(reports, Element.class);
    }

    /**
     * Sets the quantitative quality information for the data specified by the scope.
     * Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @param newValues The new reports.
     */
    public synchronized void setReports(final Collection<? extends Element> newValues) {
        reports = copyCollection(newValues, reports, Element.class);
    }

    /**
     * Returns non-quantitative quality information about the lineage of the data specified
     * by the scope. Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     */
    @Override
    @XmlElement(name = "lineage")
    public Lineage getLineage() {
        return lineage;
    }

    /**
     * Sets the non-quantitative quality information about the lineage of the data specified
     * by the scope. Should be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @param newValue The new lineage.
     */
    public synchronized void setLineage(final Lineage newValue) {
        checkWritePermission();
        lineage = newValue;
    }
}
