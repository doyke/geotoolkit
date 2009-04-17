//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.07.21 at 12:27:36 PM CEST 
//


package org.geotools.internal.jaxb.v110.se;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategorizeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CategorizeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/se}FunctionType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/se}LookupValue"/>
 *         &lt;element ref="{http://www.opengis.net/se}Value"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/se}Threshold"/>
 *           &lt;element ref="{http://www.opengis.net/se}TValue"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="threshholdsBelongTo" type="{http://www.opengis.net/se}ThreshholdsBelongToType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategorizeType", propOrder = {
    "lookupValue",
    "value",
    "thresholdAndTValue"
})
public class CategorizeType
    extends FunctionType
{

    @XmlElement(name = "LookupValue", required = true)
    protected ParameterValueType lookupValue;
    @XmlElement(name = "Value", required = true)
    protected ParameterValueType value;
    @XmlElementRefs({
        @XmlElementRef(name = "Threshold", namespace = "http://www.opengis.net/se", type = JAXBElement.class),
        @XmlElementRef(name = "TValue", namespace = "http://www.opengis.net/se", type = JAXBElement.class)
    })
    protected List<JAXBElement<ParameterValueType>> thresholdAndTValue;
    @XmlAttribute
    protected ThreshholdsBelongToType threshholdsBelongTo;

    /**
     * Gets the value of the lookupValue property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getLookupValue() {
        return lookupValue;
    }

    /**
     * Sets the value of the lookupValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setLookupValue(ParameterValueType value) {
        this.lookupValue = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setValue(ParameterValueType value) {
        this.value = value;
    }

    /**
     * Gets the value of the thresholdAndTValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thresholdAndTValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThresholdAndTValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     * {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<ParameterValueType>> getThresholdAndTValue() {
        if (thresholdAndTValue == null) {
            thresholdAndTValue = new ArrayList<JAXBElement<ParameterValueType>>();
        }
        return this.thresholdAndTValue;
    }

    /**
     * Gets the value of the threshholdsBelongTo property.
     * 
     * @return
     *     possible object is
     *     {@link ThreshholdsBelongToType }
     *     
     */
    public ThreshholdsBelongToType getThreshholdsBelongTo() {
        return threshholdsBelongTo;
    }

    /**
     * Sets the value of the threshholdsBelongTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreshholdsBelongToType }
     *     
     */
    public void setThreshholdsBelongTo(ThreshholdsBelongToType value) {
        this.threshholdsBelongTo = value;
    }

}
