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
package org.geotoolkit.gui.swing.filestore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.geotoolkit.client.Server;
import org.geotoolkit.client.ServerFactory;
import org.geotoolkit.client.ServerFinder;
import org.geotoolkit.gui.swing.propertyedit.JFeatureOutLine;
import org.geotoolkit.gui.swing.resource.MessageBundle;
import org.geotoolkit.map.MapLayer;
import org.geotoolkit.storage.DataStoreException;
import org.geotoolkit.util.logging.Logging;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.opengis.parameter.ParameterValueGroup;

/**
 * Panel allowing to choose a server and configure it among the
 * declared ServerFactories.
 * 
 * @author Johann Sorel (Geomatys)
 * @module pending
 */
public class JServerChooser extends javax.swing.JPanel {

    private static final Logger LOGGER = Logging.getLogger(JCoverageStoreChooser.class);
    
    private static final Comparator<ServerFactory> SORTER = new Comparator<ServerFactory>() {
        @Override
        public int compare(ServerFactory o1, ServerFactory o2) {
            return o1.getDisplayName().compareTo(o2.getDisplayName());
        }
    };
    
    private final JFeatureOutLine guiEditor = new JFeatureOutLine();
    private final JLayerChooser chooser = new JLayerChooser();
    
    public JServerChooser() {
        initComponents();
        guiEditPane.add(BorderLayout.CENTER,new JScrollPane(guiEditor));
        
        final Iterator<ServerFactory> ite = ServerFinder.getAvailableFactories();
        final List<ServerFactory> factories = new ArrayList<ServerFactory>();
        while(ite.hasNext()){
            factories.add(ite.next());
        }
        Collections.sort(factories, SORTER);
        
        guiList.setModel(new ListComboBoxModel(factories));
        guiList.setCellRenderer(new FactoryCellRenderer());
        guiList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                final ServerFactory factory = (ServerFactory) guiList.getSelectedValue();
                final ParameterValueGroup param = factory.getParametersDescriptor().createValue();
                guiEditor.setEdited(param);
            }
        });
        setLayerSelectionVisible(false);
    }

    public void setLayerSelectionVisible(boolean visible){
        if(visible){
            guiSplit.setRightComponent(guiLayerSplit);
            guiLayerSplit.setLeftComponent(guiConfig);
            guiLayerSplit.setRightComponent(chooser);
            guiLayerSplit.setDividerLocation(260);
        }else{
            guiSplit.setRightComponent(guiConfig);
        }
    }

    public Server getServer() throws DataStoreException{
        final ServerFactory factory = (ServerFactory) guiList.getSelectedValue();
        
        if(factory == null){
            return null;
        }
        
        final ParameterValueGroup param = guiEditor.getEditedAsParameter(factory.getParametersDescriptor());
        return factory.create(param);
    }
    
    public List<MapLayer> getSelectedLayers() throws DataStoreException{
        return chooser.getLayers();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guiLayerSplit = new javax.swing.JSplitPane();
        guiSplit = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        guiList = new javax.swing.JList();
        guiConfig = new javax.swing.JPanel();
        guiEditPane = new javax.swing.JPanel();
        guiInfoLabel = new javax.swing.JTextField();
        guiConnect = new javax.swing.JButton();

        guiLayerSplit.setDividerSize(5);

        guiSplit.setDividerLocation(240);
        guiSplit.setDividerSize(5);

        guiList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(guiList);

        guiSplit.setLeftComponent(jScrollPane1);

        guiEditPane.setLayout(new java.awt.BorderLayout());

        guiInfoLabel.setEditable(false);

        guiConnect.setText("Connect");
        guiConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout guiConfigLayout = new javax.swing.GroupLayout(guiConfig);
        guiConfig.setLayout(guiConfigLayout);
        guiConfigLayout.setHorizontalGroup(
            guiConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, guiConfigLayout.createSequentialGroup()
                .addComponent(guiInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guiConnect))
            .addComponent(guiEditPane, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );
        guiConfigLayout.setVerticalGroup(
            guiConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, guiConfigLayout.createSequentialGroup()
                .addComponent(guiEditPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(guiConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guiInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiConnect)))
        );

        guiSplit.setRightComponent(guiConfig);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(guiSplit, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(guiSplit, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void guiConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiConnectActionPerformed
        
        Server store = null;
        try {
            chooser.setSource(null);
            store = getServer();
            chooser.setSource(store);
            guiInfoLabel.setForeground(Color.GREEN);
            guiInfoLabel.setText("ok");
        } catch (DataStoreException ex) {
            guiInfoLabel.setForeground(Color.RED);
            guiInfoLabel.setText(""+ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getMessage(),ex);
        }
    
}//GEN-LAST:event_guiConnectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel guiConfig;
    private javax.swing.JButton guiConnect;
    private javax.swing.JPanel guiEditPane;
    private javax.swing.JTextField guiInfoLabel;
    private javax.swing.JSplitPane guiLayerSplit;
    private javax.swing.JList guiList;
    private javax.swing.JSplitPane guiSplit;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


    private static class FactoryCellRenderer extends DefaultListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            final JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if(value instanceof ServerFactory){
                final ServerFactory factory = (ServerFactory) value;
                final String txt = "<html><b>"+factory.getDisplayName()+"</b><br/>"
                        + "<font size=\"0.5em\"><i>&nbsp&nbsp&nbsp "+factory.getDescription()+"</i></font></html>";
                lbl.setText(txt);
            }
            
            return lbl;
        }
        
    }
    
    /**
     * Display a modal dialog.
     * 
     * @return
     * @throws DataStoreException 
     */
    public static List<Server> showDialog() throws DataStoreException{
        return showDialog(Collections.EMPTY_LIST);
    }
    
    /**
     * Display a modal dialog choosing layers.
     * 
     * @param editors : additional FeatureOutline editors
     * @return
     * @throws DataStoreException 
     */
    public static List<MapLayer> showLayerDialog(List<JFeatureOutLine.PropertyEditor> editors) throws DataStoreException{
        return showDialog(editors, true);
    }
    
    /**
     * Display a modal dialog.
     * 
     * @param editors : additional FeatureOutline editors
     * @return
     * @throws DataStoreException 
     */
    public static List<Server> showDialog(List<JFeatureOutLine.PropertyEditor> editors) throws DataStoreException{
        return showDialog(editors, false);       
    }
    
    private static List showDialog(List<JFeatureOutLine.PropertyEditor> editors, boolean layerVisible) throws DataStoreException{
        final JServerChooser chooser = new JServerChooser();
        if(editors != null){
            chooser.guiEditor.getEditors().addAll(editors);
        }
        chooser.setLayerSelectionVisible(layerVisible);
        final JDialog dialog = new JDialog();
        
        final AtomicBoolean openAction = new AtomicBoolean(false);
        final JToolBar bar = new JToolBar();
        bar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bar.setFloatable(false);
        bar.add(new AbstractAction(MessageBundle.getString("open")) {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAction.set(true);
                dialog.dispose();
            }
        });
        
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(BorderLayout.CENTER,chooser);        
        panel.add(BorderLayout.SOUTH, bar);
        dialog.setModal(true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
        if(openAction.get()){
            if(layerVisible){
                return chooser.getSelectedLayers();
            }else{
                final Server store = chooser.getServer();
                if(store == null){
                    return Collections.EMPTY_LIST;
                }else{
                    return Collections.singletonList(store);
                }
            }
        }else{
            return Collections.EMPTY_LIST;
        }
    }
    
}
