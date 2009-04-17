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
import org.opengis.style.Displacement;
import org.opengis.style.StyleVisitor;

import static org.geotools.style.StyleConstants.*;
import static org.opengis.filter.expression.Expression.*;

/**
 * Immutable implementation of GeoAPI displacement.
 *
 * @author Johann Sorel (Geomatys)
 */
public class DefaultDisplacement implements Displacement{

    private final Expression dispX;
    
    private final Expression dispY;
    
    /**
     * Create a default immutable displacement.
     * 
     * @param dispX : if null or Expression.NIL will be replaced by default value.
     * @param dispY : if null or Expression.NIL will be replaced by default value.
     */
    public DefaultDisplacement(Expression dispX, Expression dispY){
        this.dispX = (dispX == null || dispX == NIL) ? DEFAULT_DISPLACEMENT_X : dispX;
        this.dispY = (dispY == null || dispY == NIL) ? DEFAULT_DISPLACEMENT_Y : dispY;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Expression getDisplacementX() {
        return dispX;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Expression getDisplacementY() {
        return dispY;
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

        DefaultDisplacement other = (DefaultDisplacement) obj;

        return this.dispX.equals(other.dispX)
                && this.dispY.equals(other.dispY);

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        return dispX.hashCode() + 17*dispY.hashCode() ;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Displacement : X=");
        builder.append(dispX.toString());
        builder.append(" Y=");
        builder.append(dispY.toString());
        builder.append(']');
        return builder.toString();
    }
    
}
