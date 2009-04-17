/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
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
 */
package org.geotools.style;

import org.opengis.filter.expression.Expression;
import org.opengis.style.ShadedRelief;
import org.opengis.style.StyleVisitor;

import static org.geotools.style.StyleConstants.*;
import static org.opengis.filter.expression.Expression.*;

/**
 * Immutable implementation of GeoAPI shaded relief.
 * 
 * @author Johann Sorel (Geomatys)
 */
public class DefaultShadedRelief implements ShadedRelief{

    private final boolean bright;
    
    private final Expression relief;
    
    /**
     * Create a default immutable shaded relief.
     * 
     * @param bright : can be null
     * @param relief : if null or Expression.NIL will be replaced by default description.
     */
    public DefaultShadedRelief(boolean bright, Expression relief){
        this.bright = bright;
        this.relief = (relief == null || relief == NIL) ? DEFAULT_SHADED_RELIEF_FACTOR : relief;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isBrightnessOnly() {
        return bright;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Expression getReliefFactor() {
        return relief;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object accept(StyleVisitor visitor, Object extraData) {
        return visitor.visit(this,extraData);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }

        if(obj == null || !this.getClass().equals(obj.getClass()) ){
            return false;
        }

        DefaultShadedRelief other = (DefaultShadedRelief) obj;

        return this.bright == other.bright
                && this.relief.equals(other.relief);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return Boolean.valueOf(bright).hashCode() + relief.hashCode() ;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ShadedRelief : Bright=");
        builder.append(bright);
        builder.append(" Factor=");
        builder.append(relief.toString());
        builder.append(']');
        return builder.toString();
    }
}
