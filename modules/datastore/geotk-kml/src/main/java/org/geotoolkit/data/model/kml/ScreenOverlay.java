package org.geotoolkit.data.model.kml;

import java.util.List;
import org.geotoolkit.data.model.xsd.SimpleType;

/**
 * <p>This interface maps ScreenOverlay element.</p>
 *
 * <br />&lt;element name="ScreenOverlay" type="kml:ScreenOverlayType" substitutionGroup="kml:AbstractOverlayGroup"/>
 * <br />&lt;complexType name="ScreenOverlayType" final="#all">
 * <br />&lt;complexContent>
 * <br />&lt;extension base="kml:AbstractOverlayType">
 * <br />&lt;sequence>
 * <br />&lt;element ref="kml:overlayXY" minOccurs="0"/>
 * <br />&lt;element ref="kml:screenXY" minOccurs="0"/>
 * <br />&lt;element ref="kml:rotationXY" minOccurs="0"/>
 * <br />&lt;element ref="kml:size" minOccurs="0"/>
 * <br />&lt;element ref="kml:rotation" minOccurs="0"/>
 * <br />&lt;element ref="kml:ScreenOverlaySimpleExtensionGroup" minOccurs="0" maxOccurs="unbounded"/>
 * <br />&lt;element ref="kml:ScreenOverlayObjectExtensionGroup" minOccurs="0" maxOccurs="unbounded"/>
 * <br />&lt;/sequence>
 * <br />&lt;/extension>
 * <br />&lt;/complexContent>
 * <br />&lt;/complexType>
 * <br />&lt;element name="ScreenOverlaySimpleExtensionGroup" abstract="true" type="anySimpleType"/>
 * <br />&lt;element name="ScreenOverlayObjectExtensionGroup" abstract="true" substitutionGroup="kml:AbstractObjectGroup"/>
 *
 * @author Samuel Andrés
 */
public interface ScreenOverlay extends AbstractOverlay {

    /**
     *
     * @return
     */
    public Vec2 getOverlayXY();

    /**
     *
     * @return
     */
    public Vec2 getScreenXY();

    /**
     *
     * @return
     */
    public Vec2 getRotationXY();

    /**
     *
     * @return
     */
    public Vec2 getSize();

    /**
     *
     * @return
     */
    public Angle180 getRotation();

    /**
     *
     * @return the list of ScreenOverlay simple extensions.
     */
    public List<SimpleType> getScreenOverlaySimpleExtensions();

    /**
     *
     * @return the list of ScreenOverlay object extensions.
     */
    public List<AbstractObject> getScreenOverlayObjectExtensions();

}
