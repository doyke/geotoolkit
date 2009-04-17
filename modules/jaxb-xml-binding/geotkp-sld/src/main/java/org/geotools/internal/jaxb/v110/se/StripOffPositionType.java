//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.07.21 at 12:27:36 PM CEST 
//


package org.geotools.internal.jaxb.v110.se;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stripOffPositionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="stripOffPositionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="leading"/>
 *     &lt;enumeration value="trailing"/>
 *     &lt;enumeration value="both"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stripOffPositionType")
@XmlEnum
public enum StripOffPositionType {

    @XmlEnumValue("leading")
    LEADING("leading"),
    @XmlEnumValue("trailing")
    TRAILING("trailing"),
    @XmlEnumValue("both")
    BOTH("both");
    private final String value;

    StripOffPositionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StripOffPositionType fromValue(String v) {
        for (StripOffPositionType c: StripOffPositionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
