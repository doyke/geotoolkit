/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2011, Geomatys
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
package org.geotoolkit.gui.swing.render2d.control.navigation;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.geom.NoninvertibleTransformException;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.apache.sis.geometry.GeneralDirectPosition;
import org.geotoolkit.gui.swing.crschooser.JCRSChooser;
import org.geotoolkit.gui.swing.crschooser.JCRSChooser.ACTION;
import org.geotoolkit.gui.swing.render2d.JMap2D;
import org.geotoolkit.gui.swing.render2d.decoration.AbstractMapDecoration;
import org.geotoolkit.gui.swing.resource.IconBundle;
import org.geotoolkit.gui.swing.resource.MessageBundle;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.gui.swing.util.SwingUtilities;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.opengis.util.FactoryException;
import org.openide.util.Exceptions;

/**
 *
 * @author Johann Sorel (Geomatys)
 * @module
 */
public class JNavigateToPanel extends javax.swing.JPanel {

    private CoordinateReferenceSystem navCRS = CommonCRS.WGS84.normalizedGeographic();
    private JMap2D map = null;

    public JNavigateToPanel() {
        initComponents();
        guiCRS.setText(navCRS.getName().toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        guiNav = new javax.swing.JButton();
        guiCRS = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        guiYValue = new javax.swing.JSpinner();
        guiYLabel = new javax.swing.JLabel();
        guiXLabel = new javax.swing.JLabel();
        guiXValue = new javax.swing.JSpinner();

        guiNav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/geotoolkit/gui/swing/resource/icon/jsorel/16x16/navto.png"))); // NOI18N
        guiNav.setText(MessageBundle.format("map_nav_to")); // NOI18N
        guiNav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiNavActionPerformed(evt);
            }
        });

        guiCRS.setText(MessageBundle.format("crs")); // NOI18N
        guiCRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiCRSActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 0, 3));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        guiYValue.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(guiYValue, gridBagConstraints);

        guiYLabel.setText("Y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(guiYLabel, gridBagConstraints);

        guiXLabel.setText("X");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(guiXLabel, gridBagConstraints);

        guiXValue.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(guiXValue, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(guiCRS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guiNav))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guiCRS)
                    .addComponent(guiNav)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guiNavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiNavActionPerformed

        final GeneralDirectPosition pos = new GeneralDirectPosition(navCRS);
        pos.setOrdinate(0, (Double)guiXValue.getValue());
        pos.setOrdinate(1, (Double)guiYValue.getValue());

        if(map != null){
            try {
                map.getCanvas().setObjectiveCenter(pos);
            } catch (NoninvertibleTransformException | TransformException | FactoryException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

    }//GEN-LAST:event_guiNavActionPerformed

    private void guiCRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiCRSActionPerformed

        if (map != null ) {
            final Window frame = SwingUtilities.windowForComponent(this);
            final JCRSChooser chooser = JCRSChooser.create(frame, true);
            chooser.setCRS(navCRS);
            ACTION act = chooser.showDialog();

            if(ACTION.APPROVE.equals(act)){
                navCRS = chooser.getCRS();
                guiCRS.setText(navCRS.getName().toString());
            }
        }

    }//GEN-LAST:event_guiCRSActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guiCRS;
    private javax.swing.JButton guiNav;
    private javax.swing.JLabel guiXLabel;
    private javax.swing.JSpinner guiXValue;
    private javax.swing.JLabel guiYLabel;
    private javax.swing.JSpinner guiYValue;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public static class NavigateToMapDecoration extends AbstractMapDecoration{

        private final JPanel container = new JPanel(new BorderLayout());
        private final JLayeredPane desktop;
        private final JInternalFrame frame;
        private final JNavigateToPanel navtoPanel;

        public NavigateToMapDecoration() {
            container.setOpaque(false);
            container.setFocusable(false);

            navtoPanel = new JNavigateToPanel();

            frame = new JInternalFrame();
            frame.setContentPane(navtoPanel);
            frame.setResizable(true);
            frame.setClosable(true);
            frame.setIconifiable(false);
            frame.setFrameIcon(IconBundle.getIcon("16_navto"));
            frame.pack();
            frame.setVisible(true);

            desktop = new JLayeredPane();
            desktop.setOpaque(false);
            desktop.add(frame);

            container.add(BorderLayout.CENTER,desktop);
        }

        @Override
        public void setMap2D(JMap2D map) {
            super.setMap2D(map);
            navtoPanel.map = map;
        }

        @Override
        public void refresh() {
        }

        @Override
        public JComponent getComponent() {
            return container;
        }

    }

}
