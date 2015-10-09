/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011-2015 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.ingest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import org.openide.util.NbBundle;
import org.openide.windows.Mode;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.casemodule.Case;
import org.sleuthkit.autopsy.core.RuntimeProperties;

/**
 * Tool bar for an ingest messages button that allows a user to open the ingest
 * messages inbox top component.
 */
class IngestMessagesToolbar extends javax.swing.JPanel {

    private IngestMessagesButton ingestMessagesButton = new IngestMessagesButton();
    private static volatile IngestMessagesToolbar instance;

    private IngestMessagesToolbar() {
        initComponents();
        customizeComponents();
    }

    public static IngestMessagesToolbar getDefault() {
        if (instance == null) {
            synchronized (IngestMessagesToolbar.class) {
                if (instance == null) {
                    instance = new IngestMessagesToolbar();
                }
            }
        }
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setToolTipText(org.openide.util.NbBundle.getMessage(IngestMessagesToolbar.class, "IngestMessagesToolbar.toolTipText")); // NOI18N
        setMaximumSize(new java.awt.Dimension(32767, 25));
        setPreferredSize(new java.awt.Dimension(80, 25));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void customizeButton() {
        ingestMessagesButton = new IngestMessagesButton();

        setMaximumSize(new java.awt.Dimension(32767, 25));
        setPreferredSize(new java.awt.Dimension(80, 25));

        ingestMessagesButton.setFocusPainted(false);
        ingestMessagesButton.setContentAreaFilled(false);
        ingestMessagesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/ingest/eye-bw-25.png"))); //NON-NLS
        ingestMessagesButton.setRolloverEnabled(true);
        ingestMessagesButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/ingest/eye-bw-25-rollover.png"))); //NON-NLS
        ingestMessagesButton.setToolTipText(
                NbBundle.getMessage(this.getClass(), "IngestMessagesToolbar.customizeButton.toolTipText"));
        ingestMessagesButton.setBorder(null);
        ingestMessagesButton.setBorderPainted(false);
        ingestMessagesButton.setEnabled(false);
        ingestMessagesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ingestMessagesButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        ingestMessagesButton.setMaximumSize(new java.awt.Dimension(38, 24));
        ingestMessagesButton.setMinimumSize(new java.awt.Dimension(38, 24));
        ingestMessagesButton.setPreferredSize(new java.awt.Dimension(38, 24));
        ingestMessagesButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            EventQueue.invokeLater(this::showIngestMessages);
        });
        this.add(ingestMessagesButton, BorderLayout.CENTER);
    }

    private void customizeComponents() {

        customizeButton();

        this.setBorder(null);

        IngestMessagePanel.addPropertyChangeSupportListener(new PropertyChangeListener() {
            private int numNewMessages = 0;

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                String propName = evt.getPropertyName();
                int newNumNewMessages = numNewMessages;
                if (propName.equals(IngestMessagePanel.MESSAGES_BOX_CLEARED)
                        || propName.equals(IngestMessagePanel.TOTAL_NUM_NEW_MESSAGES_CHANGED)) {
                    newNumNewMessages = (Integer) evt.getNewValue();
                }

                if (newNumNewMessages != numNewMessages) {
                    ingestMessagesButton.setMessages(newNumNewMessages);
                    numNewMessages = newNumNewMessages;
                    ingestMessagesButton.repaint();
                }
            }
        });

        Case.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().equals(Case.Events.CURRENT_CASE.toString())) {
                setEnabled(evt.getNewValue() != null && RuntimeProperties.coreComponentsAreActive());
            }
        });
    }

    /**
     * Pop up and show ingest messages window
     */
    void showIngestMessages() {
        IngestMessageTopComponent tc = IngestMessageTopComponent.findInstance();

        Mode mode = WindowManager.getDefault().findMode("floatingLeftBottom"); //NON-NLS
        if (mode != null) {
            //TopComponent[] tcs = mode.getTopComponents();
            mode.dockInto(tc);
            tc.open();
            //tc.requestActive();   
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        ingestMessagesButton.setEnabled(enabled);
    }

    private static class IngestMessagesButton extends JButton {

        private static final int fontSize = 9;
        private int messages = 0;

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            if (messages == 0) {
                return;
            }
            //paint text
            Font messagesFont = g.getFont().deriveFont(Font.PLAIN, fontSize);
            String messageStr = Integer.toString(messages);
            final int len = messageStr.length();
            g.setFont(messagesFont);

            int dx = len * 5 + 5;
            int x = getSize().width - dx;
            if (x < 0) {
                x = 0;
            }
            g.setColor(Color.GRAY);
            //g.fillRect(x, 1, dx, fontSize);
            g.fillRoundRect(x, 1, dx, fontSize, 2, 2);
            g.setColor(Color.WHITE);
            g.drawString(messageStr, x + 2, fontSize);
        }

        void setMessages(int messages) {
            this.messages = messages;
        }
    }
}
