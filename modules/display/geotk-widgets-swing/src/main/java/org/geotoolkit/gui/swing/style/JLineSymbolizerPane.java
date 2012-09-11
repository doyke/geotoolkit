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
package org.geotoolkit.gui.swing.style;


import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import org.geotoolkit.gui.swing.resource.MessageBundle;
import org.geotoolkit.map.MapLayer;
import org.geotoolkit.style.StyleConstants;
import org.opengis.style.LineSymbolizer;


/**
 * Line symbolizer edition panel
 * 
 * @author Johann Sorel
 * @module pending
 */
public class JLineSymbolizerPane extends  StyleElementEditor<LineSymbolizer> {
    
    private MapLayer layer = null;
    
    /** 
     * Creates new form JLineSymbolizerPanel
     */
    public JLineSymbolizerPane() {
        super(LineSymbolizer.class);
        initComponents();
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void setLayer(final MapLayer layer){
        this.layer = layer;
        guiStroke.setLayer(layer);
        guiGeom.setLayer(layer);
        guiUOM.setLayer(layer);
        guiOffset.setLayer(layer);
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public MapLayer getLayer(){
        return layer;
    }
 
    /**
     * {@inheritDoc }
     */
    @Override
    public void parse(final LineSymbolizer symbol) {
        
        if (symbol != null) {            
            guiGeom.setGeom(symbol.getGeometryPropertyName());
            guiStroke.parse(symbol.getStroke());
            guiOffset.parse(symbol.getPerpendicularOffset());
            guiUOM.parse(symbol.getUnitOfMeasure());
    }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public LineSymbolizer create(){
        return getStyleFactory().lineSymbolizer(
                    "lineSymbolizer",
                    guiGeom.getGeom(),
                    StyleConstants.DEFAULT_DESCRIPTION,
                    guiUOM.create(),
                    guiStroke.create(), 
                    guiOffset.create());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guiStroke = new JStrokePane();
        guiOffset = new JOffSetPane();
        jPanel1 = new JPanel();
        guiGeom = new JGeomPane();
        guiUOM = new JUOMPane();

        setOpaque(false);



        guiStroke.setBorder(BorderFactory.createTitledBorder(MessageBundle.getString("stroke"))); // NOI18N
        guiOffset.setBorder(BorderFactory.createTitledBorder(MessageBundle.getString("displacement"))); // NOI18N
        jPanel1.setBorder(BorderFactory.createTitledBorder(MessageBundle.getString("general"))); // NOI18N
        jPanel1.setOpaque(false);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(guiGeom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiUOM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {guiGeom, guiUOM});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(guiGeom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(guiUOM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(guiStroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(guiOffset, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(guiStroke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(guiOffset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JGeomPane guiGeom;
    private JOffSetPane guiOffset;
    private JStrokePane guiStroke;
    private JUOMPane guiUOM;
    private JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
