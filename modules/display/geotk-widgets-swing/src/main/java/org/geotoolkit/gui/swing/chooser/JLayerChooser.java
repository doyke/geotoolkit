/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2012, Geomatys
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 3 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotoolkit.gui.swing.chooser;

import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import org.geotoolkit.feature.FeatureExt;
import org.geotoolkit.storage.coverage.CoverageStore;
import org.geotoolkit.data.FeatureStore;
import org.geotoolkit.data.FeatureCollection;
import org.geotoolkit.data.query.QueryBuilder;
import org.geotoolkit.data.session.Session;
import org.geotoolkit.factory.FactoryFinder;
import org.geotoolkit.factory.Hints;
import org.geotoolkit.gui.swing.util.JOptionDialog;
import org.geotoolkit.gui.swing.resource.IconBundle;
import org.geotoolkit.map.CoverageMapLayer;
import org.geotoolkit.map.FeatureMapLayer;
import org.geotoolkit.map.MapBuilder;
import org.geotoolkit.map.MapLayer;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.Resource;
import org.geotoolkit.util.NamesExt;
import org.geotoolkit.style.MutableStyle;
import org.geotoolkit.style.MutableStyleFactory;
import org.geotoolkit.style.RandomStyleBuilder;
import org.geotoolkit.style.StyleConstants;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.opengis.feature.AttributeType;
import org.opengis.feature.FeatureType;
import org.opengis.util.GenericName;
import org.opengis.feature.PropertyNotFoundException;
import org.opengis.feature.PropertyType;
import org.geotoolkit.storage.coverage.GridCoverageResource;

/**
 * Panel allowing to choose layer among the available datas of the source.
 * Random styles are generated for feature layers.
 *
 * @author Johann Sorel (Geoamtys)
 * @module
 */
public class JLayerChooser extends javax.swing.JPanel {

    private static final Comparator SORTER = new Comparator() {

        @Override
        public int compare(Object o1, Object o2) {
            final String str1;
            final String str2;

            if(o1 instanceof FeatureType){
                str1 = ((FeatureType)o1).getName().tip().toString();
            }else if(o1 instanceof GenericName){
                str1 = ((GenericName)o1).tip().toString();
            }else{
                str1 = o1.toString();
            }

            if(o2 instanceof FeatureType){
                str2 = ((FeatureType)o2).getName().tip().toString();
            }else if(o2 instanceof GenericName){
                str2 = ((GenericName)o2).tip().toString();
            }else{
                str2 = o2.toString();
            }

            return str1.compareToIgnoreCase(str2);
        }
    };

    private Object source = null;

    public JLayerChooser() {
        initComponents();
        guiList.setCellRenderer(new NameCellRenderer());
    }

    public List<MapLayer> getLayers() throws DataStoreException{

        final MutableStyleFactory styleFactory = (MutableStyleFactory) FactoryFinder.getStyleFactory(
                            new Hints(Hints.STYLE_FACTORY, MutableStyleFactory.class));

        final Object[] values = guiList.getSelectedValuesList().toArray();
        final List<MapLayer> layers = new ArrayList<>();

        if(values != null){
            for(Object value : values){
                final GenericName name;
                if(value instanceof FeatureType){
                    name = ((FeatureType) value).getName();
                }else{
                    name = (GenericName) value;
                }

                if(source instanceof FeatureStore){
                    final FeatureStore store = (FeatureStore) source;
                    final Session session = store.createSession(true);
                    final FeatureCollection collection = session.getFeatureCollection(QueryBuilder.all(name.toString()));
                    final MutableStyle style = RandomStyleBuilder.createRandomVectorStyle(collection.getType());
                    final FeatureMapLayer layer = MapBuilder.createFeatureLayer(collection, style);
                    layer.setDescription(styleFactory.description(name.tip().toString(), name.toString()));
                    layers.add(layer);

                }else if(source instanceof CoverageStore){
                    final CoverageStore store = (CoverageStore) source;
                    final Resource ref = store.findResource(name.toString());
                    final MutableStyle style = styleFactory.style(StyleConstants.DEFAULT_RASTER_SYMBOLIZER);
                    final CoverageMapLayer layer = MapBuilder.createCoverageLayer(ref, style);
                    layer.setDescription(styleFactory.description(name.tip().toString(), name.toString()));
                    layers.add(layer);
                }
            }
        }

        return layers;
    }

    public void setSource(Object source) throws DataStoreException {
        this.source = source;

        final List firstCandidates = new ArrayList<>();
        final List secondCandidates = new ArrayList<>();

        if(source instanceof FeatureStore){
            final FeatureStore store = (FeatureStore) source;
            for(GenericName name : store.getNames()){
                final FeatureType ft = store.getFeatureType(name.toString());
                try {
                    final PropertyType geomAtt = FeatureExt.getDefaultGeometry(ft);
                    firstCandidates.add(ft);
                } catch (PropertyNotFoundException | IllegalStateException e) {
                    secondCandidates.add(ft);
                }
            }
        }

        if(source instanceof CoverageStore){
            final CoverageStore store = (CoverageStore) source;
            firstCandidates.addAll(store.getNames());
        }


        Collections.sort(firstCandidates, SORTER);

        if(!secondCandidates.isEmpty()){
            Collections.sort(secondCandidates, SORTER);

            firstCandidates.add(new JSeparator());
            firstCandidates.addAll(secondCandidates);
        }

        guiList.setModel(new ListComboBoxModel(firstCandidates));
    }

    public Object getSource() {
        return source;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        guiList = new javax.swing.JList();

        jScrollPane1.setViewportView(guiList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList guiList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


    /**
     * Display a modal dialog.
     *
     * @param source
     * @return
     * @throws DataStoreException
     */
    public static List<MapLayer> showDialog(Component parent, Object source) throws DataStoreException{
        final JLayerChooser chooser = new JLayerChooser();
        chooser.setSource(source);

        final int res = JOptionDialog.show(parent, chooser, JOptionPane.OK_OPTION);

        if (JOptionPane.OK_OPTION == res) {
            return chooser.getLayers();
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private class NameCellRenderer extends DefaultListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            if(value instanceof Component){
                return (Component)value;
            }

            final JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            lbl.setIcon(null);

            if(value instanceof FeatureType){
                final FeatureType ft = (FeatureType) value;
                final FeatureStore store = (FeatureStore) getSource();

                final AttributeType<?> desc = FeatureExt.castOrUnwrap(FeatureExt.getDefaultGeometry(ft))
                        .orElse(null);
                if(desc != null){
                    ImageIcon icon;
                    final Class binding = desc.getValueClass();
                    if(Point.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_single_point");
                    }else if(MultiPoint.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_multi_point");
                    }else if(LineString.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_single_line");
                    }else if(MultiLineString.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_multi_line");
                    }else if(Polygon.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_single_polygon");
                    }else if(MultiPolygon.class.isAssignableFrom(binding)){
                        icon = IconBundle.getIcon("16_multi_polygon");
                    }else{
                        icon = IconBundle.EMPTY_ICON_16;
                    }

                    boolean editable = false;
                    try {
                        if(store.isWritable(ft.getName().toString())){
                            editable = true;
                        }
                    } catch (DataStoreException ex) {}

                    if(!editable){
                        final BufferedImage img = new BufferedImage(
                                                        icon.getIconWidth(),
                                                        icon.getIconHeight(),
                                                        BufferedImage.TYPE_INT_ARGB);
                        final Graphics2D g = img.createGraphics();
                        g.drawImage(icon.getImage(), 0, 0, null);
                        final ImageIcon lock = IconBundle.getIcon("16_small_lock");
                        g.drawImage(lock.getImage(), 0, 0, null);
                        icon = new ImageIcon(img);
                    }

                    lbl.setIcon(icon);
                }

                value = ft.getName();
            }

            if(value instanceof GenericName){
                final GenericName name = (GenericName) value;
                lbl.setText(name.tip().toString());
                lbl.setToolTipText(NamesExt.toExpandedString(name));
            }

            return lbl;
        }

    }

}
