/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2007 - 2008, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2008 - 2009, Johann Sorel
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
package org.geotoolkit.gui.swing.render2d.decoration;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import org.geotoolkit.gui.swing.render2d.JMap2D;


/**
 * Edition Decoration
 *
 * @author Johann Sorel
 * @module
 */
public class EditionDecoration extends JComponent implements MapDecoration{

    private final Color color = Color.RED;

    private int startx =0;
    private int starty =0;
    private int endx = 0;
    private int endy = 0;
    private boolean draw = false;

    public EditionDecoration(){}


    public void setCoord(final int sx, final int sy, final int ex, final int ey, final boolean draw){
        startx = sx;
        starty = sy;
        endx = ex;
        endy = ey;
        this.draw = draw;
        repaint();
    }

    public void setEnd(final int x, final int y){
        endx = x;
        endy = y;
        repaint();
    }

    public void setDraw(final boolean b){
        this.draw = b;
        repaint();
    }


    @Override
    public void paintComponent(final Graphics g) {
        if(draw && (startx != endx || starty != endy)){

            g.setColor(color);
            g.drawLine(startx, starty, endx, endy);
            g.drawLine(endx, endy-4, endx, endy+4);
            g.drawLine(endx-4, endy, endx+4, endy);

            }
    }

    public void refresh() {
        repaint();
    }

    public JComponent getComponent() {
        return this;
    }

    public void setMap2D(final JMap2D map) {

    }

    public JMap2D getMap2D() {
        return null;
    }

    public void dispose() {
    }

}
