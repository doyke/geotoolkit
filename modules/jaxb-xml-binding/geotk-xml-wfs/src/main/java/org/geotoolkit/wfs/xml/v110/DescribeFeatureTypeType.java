/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2008 - 2009, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.wfs.xml.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.geotoolkit.wfs.xml.DescribeFeatureType;


/**
 * The DescribeFeatureType operation allows a client application to request that a Web Feature Service describe one or more
 * feature types.   
 * A Web Feature Service must be able to generate feature descriptions as valid GML3 application schemas.
 * 
 * The schemas generated by the DescribeFeatureType operation can be used by a client application to validate the output.
 * 
 * Feature instances within the WFS interface must be specified using GML3.
 * The schema of feature instances specified within the WFS interface must validate against the feature schemas
 * generated by the DescribeFeatureType request.
 *          
 * 
 * <p>Java class for DescribeFeatureTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescribeFeatureTypeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/wfs}BaseRequestType">
 *       &lt;sequence>
 *         &lt;element name="TypeName" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="outputFormat" type="{http://www.w3.org/2001/XMLSchema}string" default="text/xml; subtype=gml/3.1.1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 * @module pending
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeFeatureTypeType", propOrder = {
    "typeName"
})
@XmlRootElement(name = "DescribeFeatureType")
public class DescribeFeatureTypeType extends BaseRequestType implements DescribeFeatureType {

    @XmlElement(name = "TypeName")
    private List<QName> typeName;
    @XmlAttribute
    private String outputFormat;

    public DescribeFeatureTypeType() {

    }

    public DescribeFeatureTypeType(final String service, final String version, final String handle, final List<QName> typeName, final String outputFormat) {
        super(service, version, handle);
        this.outputFormat = outputFormat;
        this.typeName     = typeName;
    }
    
    /**
     * Gets the value of the typeName property.
     */
    public List<QName> getTypeName() {
        if (typeName == null) {
            typeName = new ArrayList<QName>();
        }
        return this.typeName;
    }

    /**
     * Gets the value of the outputFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFormat() {
        if (outputFormat == null) {
            return "text/xml; subtype=gml/3.1.1";
        } else {
            return outputFormat;
        }
    }

    /**
     * Sets the value of the outputFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFormat(final String value) {
        this.outputFormat = value;
    }

}
