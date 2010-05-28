package org.geotoolkit.data.model.kml;

import java.util.List;
import org.geotoolkit.data.model.atom.AtomLink;
import org.geotoolkit.data.model.atom.AtomPersonConstruct;
import org.geotoolkit.data.model.xal.AddressDetails;
import org.geotoolkit.data.model.xsd.SimpleType;

/**
 *
 * @author Samuel Andrés
 */
public class PhotoOverlayDefault extends AbstractOverlayDefault implements PhotoOverlay {

    private Angle180 rotation;
    private ViewVolume viewVolume;
    private ImagePyramid imagePyramid;
    private Point point;
    private Shape shape;
    private List<SimpleType> photoOverlaySimpleExtensions;
    private List<AbstractObject> photoOverlayObjectExtensions;

    /**
     *
     * @param objectSimpleExtensions
     * @param idAttributes
     * @param name
     * @param visibility
     * @param open
     * @param author
     * @param link
     * @param address
     * @param addressDetails
     * @param phoneNumber
     * @param snippet
     * @param description
     * @param view
     * @param timePrimitive
     * @param styleUrl
     * @param styleSelector
     * @param region
     * @param extendedData
     * @param abstractFeatureSimpleExtensions
     * @param abstractFeatureObjectExtensions
     * @param color
     * @param drawOrder
     * @param icon
     * @param abstractOveraySimpleExtensions
     * @param abstractOverlayObjectExtensions
     * @param rotation
     * @param viewVolume
     * @param imagePyramid
     * @param point
     * @param shape
     * @param photoOverlaySimpleExtensions
     * @param photoOverlayObjectExtensions
     */
    public PhotoOverlayDefault(List<SimpleType> objectSimpleExtensions, IdAttributes idAttributes,
            String name, boolean visibility, boolean open, AtomPersonConstruct author, AtomLink link,
            String address, AddressDetails addressDetails, String phoneNumber, String snippet,
            String description, AbstractView view, AbstractTimePrimitive timePrimitive,
            String styleUrl, List<AbstractStyleSelector> styleSelector,
            Region region, ExtendedData extendedData,
            List<SimpleType> abstractFeatureSimpleExtensions,
            List<AbstractObject> abstractFeatureObjectExtensions,
            Color color, int drawOrder, Link icon,
            List<SimpleType> abstractOveraySimpleExtensions, List<AbstractObject> abstractOverlayObjectExtensions,
            Angle180 rotation, ViewVolume viewVolume, ImagePyramid imagePyramid, Point point, Shape shape,
            List<SimpleType> photoOverlaySimpleExtensions, List<AbstractObject> photoOverlayObjectExtensions){
        super(objectSimpleExtensions, idAttributes,
                name, visibility, open, author, link, address, addressDetails, phoneNumber, snippet,
                description, view, timePrimitive, styleUrl, styleSelector, region, extendedData,
                abstractFeatureSimpleExtensions, abstractFeatureObjectExtensions,
                color, drawOrder, icon, abstractOveraySimpleExtensions, abstractOverlayObjectExtensions);
        this.rotation = rotation;
        this.viewVolume = viewVolume;
        this.imagePyramid = imagePyramid;
        this.point = point;
        this.shape = shape;
        this.photoOverlaySimpleExtensions = photoOverlaySimpleExtensions;
        this.photoOverlayObjectExtensions = photoOverlayObjectExtensions;
    }

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public Angle180 getRotation() {return this.rotation;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public ViewVolume getViewVolume() {return this.viewVolume;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public ImagePyramid getImagePyramid() {return this.imagePyramid;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public Point getPoint() {return this.point;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public Shape getShape() {return this.shape;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public List<SimpleType> getPhotoOverlaySimpleExtensions() {return this.photoOverlaySimpleExtensions;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public List<AbstractObject> getPhotoOverlayObjectExtensions() {return this.photoOverlayObjectExtensions;}

}
