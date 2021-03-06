/*
 *    Geotoolkit - An Open Source Java GIS Toolkit
 *    http://www.geotoolkit.org
 *
 *    (C) 2009-2011, Johann Sorel
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
package org.geotoolkit.gui.swing.render2d.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import org.geotoolkit.gui.swing.render2d.JMap2D;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.opengis.referencing.operation.TransformException;
import org.apache.sis.util.logging.Logging;

/**
 *
 * @author Johann Sorel (Puzzle-GIS)
 * @module
 */
public class JScaleCombo extends JComboBox {

    private JMap2D map = null;
    private final PropertyChangeListener listener = new PropertyChangeListener() {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            removeItemListener(action);
            try {
                final double  scale = map.getCanvas().getGeographicScale();
                scales.set(0, scale);
                setSelectedIndex(0);
                repaint();
                addItemListener(action);
            } catch (TransformException ex) {
                Logging.getLogger("org.geotoolkit.gui.swing.render2d.control").log(Level.WARNING, null, ex);
            }
        }
    };
    private final ItemListener action = new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            if (ie.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            if (map != null) {
                try {
                    map.getCanvas().setGeographicScale(((Number) getSelectedItem()).doubleValue());
                } catch (TransformException ex) {
                    Logging.getLogger("org.geotoolkit.gui.swing.render2d.control").log(Level.WARNING, null, ex);
                }
            }
        }
    };
    private final List<Number> scales = new ArrayList<Number>();
    private final ScaleEditor edit = new ScaleEditor();

    public JScaleCombo() {
        scales.add(1000l);
        scales.add(5000l);
        scales.add(20000l);
        scales.add(50000l);
        setPrototypeDisplayValue(100000000000l);
        setModel(new ListComboBoxModel(scales));
        setRenderer(new ScaleRenderer());
        setEditor(edit);
        setEditable(false);
        setSelectedIndex(0);
        setEditable(true);
        addItemListener(action);
        setEditor(editor);

    }

    public void setScales(final List<Number> scales) {
        removeItemListener(action);

        final Number selected = (Number) getSelectedItem();

        this.scales.clear();

        if (scales != null) {
            this.scales.addAll(scales);
        }

        this.scales.set(0, selected);
        setSelectedIndex(0);
        repaint();
        addItemListener(action);
    }

    public List<Number> getScales(){
        return new ArrayList<Number>(scales);
    }

    public void setStepSize(final Number step){
        edit.setStepSize(step);
    }

    public Number getStepSize(){
        return edit.getStepSize();
    }

    public void setMap(final JMap2D map) {
        if (this.map != null) {
            this.map.getCanvas().removePropertyChangeListener(listener);
        }

        this.map = map;

        if (this.map != null) {
            this.map.getCanvas().addPropertyChangeListener(listener);
        }

    }

    public JMap2D getMap() {
        return map;
    }

    private static final class ScaleRenderer extends DefaultListCellRenderer {

        public ScaleRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(final JList jlist, final Object o, final int i, final boolean bln, final boolean bln1) {
            super.getListCellRendererComponent(jlist, o, i, bln, bln1);
            ScaleRenderer.this.setOpaque(false);
            final Long l = ((Number) o).longValue();

            final StringBuilder sb = new StringBuilder();
            final String str = l.toString();


            for (int n = str.length() - 1, k = n; k >= 0; k--) {
                sb.append(str.charAt(k));
                if ((n - k) != 0 && (n - k) % 3 == 2) {
                    sb.append(' ');
                }
            }
            sb.append(" : 1");
            sb.reverse();

            this.setText(sb.toString());

            return this;
        }
    }

    private static final class ScaleEditor extends JPanel implements ComboBoxEditor {

        private final JSpinner field = new JSpinner(new SpinnerNumberModel(1,0.000001,Double.POSITIVE_INFINITY,500));
        private final EventListenerList listeners = new EventListenerList();


        public ScaleEditor() {
            super(new BorderLayout());
            setOpaque(false);
            ScaleEditor.this.add(BorderLayout.CENTER,field);

            field.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent ce) {
                    ActionEvent event = new ActionEvent(ScaleEditor.this, 0, "");

                    for(ActionListener lst : listeners.getListeners(ActionListener.class)){
                        lst.actionPerformed(event);
                    }
                }
            });

        }

        public void setStepSize(final Number step){
            SpinnerNumberModel model = (SpinnerNumberModel) field.getModel();
            model.setStepSize(step);
        }

        public Number getStepSize(){
            SpinnerNumberModel model = (SpinnerNumberModel) field.getModel();
            return model.getStepSize();
        }

        @Override
        public void setItem(final Object anObject) {
            if (anObject != null) {
                field.setValue( ((Number)anObject).doubleValue());
            }
        }

        @Override
        public Component getEditorComponent() {
            return ScaleEditor.this;
        }

        @Override
        public Object getItem() {
            return field.getValue();
        }

        @Override
        public void selectAll() {
//            field.selectAll();
        }

        @Override
        public void addActionListener(final ActionListener l) {
            listeners.add(ActionListener.class, l);
        }

        @Override
        public void removeActionListener(final ActionListener l) {
            listeners.remove(ActionListener.class, l);
        }
    }
}
