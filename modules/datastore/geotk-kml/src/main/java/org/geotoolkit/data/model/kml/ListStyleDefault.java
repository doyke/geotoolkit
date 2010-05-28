package org.geotoolkit.data.model.kml;

import java.util.List;
import org.geotoolkit.data.model.xsd.SimpleType;

/**
 *
 * @author Samuel Andrés
 */
public class ListStyleDefault extends AbstractSubStyleDefault implements ListStyle {

    private ListItem listItem;
    private Color bgColor;
    private List<ItemIcon> itemIcons;
    private int maxSnippetLines;
    private List<SimpleType> listStyleSimpleExtensions;
    private List<AbstractObject> listStyleObjectExtensions;

    /**
     *
     * @param objectSimpleExtensions
     * @param idAttributes
     * @param subStyleSimpleExtensions
     * @param subStyleObjectExtensions
     * @param listItem
     * @param bgColor
     * @param itemIcons
     * @param maxSnippetLines
     * @param listStyleSimpleExtensions
     * @param listStyleObjectExtensions
     */
    public ListStyleDefault(List<SimpleType> objectSimpleExtensions, IdAttributes idAttributes,
            List<SimpleType> subStyleSimpleExtensions, List<AbstractObject> subStyleObjectExtensions,
            ListItem listItem, Color bgColor, List<ItemIcon> itemIcons, int maxSnippetLines,
            List<SimpleType> listStyleSimpleExtensions, List<AbstractObject> listStyleObjectExtensions){
        super(objectSimpleExtensions, idAttributes,
                subStyleSimpleExtensions, subStyleObjectExtensions);
        this.listItem = listItem;
        this.bgColor = bgColor;
        this.itemIcons = itemIcons;
        this.maxSnippetLines = maxSnippetLines;
        this.listStyleSimpleExtensions = listStyleSimpleExtensions;
        this.listStyleObjectExtensions = listStyleObjectExtensions;
    }

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public ListItem getListItem() {return this.listItem;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public Color getBgColor() {return this.bgColor;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public List<ItemIcon> getItemIcons() {return this.itemIcons;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public int getMaxSnippetLines() {return this.maxSnippetLines;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public List<SimpleType> getListStyleSimpleExtensions() {return this.listStyleSimpleExtensions;}

    /**
     *
     * @{@inheritDoc }
     */
    @Override
    public List<AbstractObject> getListStyleObjectExtensions() {return this.listStyleObjectExtensions;}

    @Override
    public String toString(){
        String resultat = super.toString()+
                "\n\tListStyleDefault : "+
                "\n\tlistItem : "+this.listItem+
                "\n\tbgColor : "+this.bgColor+
                "\n\titemIcons : "+this.itemIcons+
                "\n\tmaxSnippetLines : "+this.maxSnippetLines;
        return resultat;
    }
}
