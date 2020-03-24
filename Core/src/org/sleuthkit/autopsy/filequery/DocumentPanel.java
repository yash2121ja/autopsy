/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
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
package org.sleuthkit.autopsy.filequery;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.openide.util.NbBundle.Messages;

/**
 * Class which displays a preview and details about a document.
 */
public class DocumentPanel extends javax.swing.JPanel implements ListCellRenderer<DocumentWrapper> {

    private static final long serialVersionUID = 1L;
    private static final Color SELECTION_COLOR = new Color(0, 120, 215);

    /**
     * Creates new form DocumentPanel
     */
    public DocumentPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        isDeletedLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        fileSizeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        sampleImageLabel = new javax.swing.JLabel();
        numberOfImagesLabel = new javax.swing.JLabel();
        previewTextPane = new javax.swing.JTextPane();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        isDeletedLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/file-icon-deleted.png"))); // NOI18N
        isDeletedLabel.setToolTipText(org.openide.util.NbBundle.getMessage(DocumentPanel.class, "DocumentPanel.isDeletedLabel.toolTipText")); // NOI18N
        isDeletedLabel.setMaximumSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));
        isDeletedLabel.setMinimumSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));
        isDeletedLabel.setPreferredSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));

        scoreLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/red-circle-exclamation.png"))); // NOI18N
        scoreLabel.setToolTipText("");
        scoreLabel.setMaximumSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));
        scoreLabel.setMinimumSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));
        scoreLabel.setPreferredSize(new Dimension(org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize(),org.sleuthkit.autopsy.filequery.DiscoveryUiUtils.getIconSize()));

        fileSizeLabel.setToolTipText(org.openide.util.NbBundle.getMessage(DocumentPanel.class, "DocumentPanel.fileSizeLabel.toolTipText")); // NOI18N

        sampleImageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sampleImageLabel.setIconTextGap(0);
        sampleImageLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        sampleImageLabel.setMinimumSize(new java.awt.Dimension(100, 100));
        sampleImageLabel.setPreferredSize(new java.awt.Dimension(100, 100));

        previewTextPane.setEditable(false);
        previewTextPane.setBackground(new java.awt.Color(240, 240, 240));
        previewTextPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fileSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(isDeletedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(previewTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numberOfImagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sampleImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberOfImagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sampleImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previewTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isDeletedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileSizeLabel;
    private javax.swing.JLabel isDeletedLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel numberOfImagesLabel;
    private javax.swing.JTextPane previewTextPane;
    private javax.swing.JLabel sampleImageLabel;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables

    @Messages({
        "# {0} - otherInstanceCount",
        "DocumentPanel.nameLabel.more.text= and {0} more",
        "# {0} - numberOfImages",
        "DocumentPanel.numberOfImages.text=1 of {0} images",
        "DocumentPanel.numberOfImages.noImages=No images",
        "DocumentPanel.noImageExtraction.text=0 of ? images"})

    @Override
    public Component getListCellRendererComponent(JList<? extends DocumentWrapper> list, DocumentWrapper value, int index, boolean isSelected, boolean cellHasFocus) {
        fileSizeLabel.setText(DiscoveryUiUtils.getFileSizeString(value.getResultFile().getFirstInstance().getSize()));
        String nameText = value.getResultFile().getFirstInstance().getParentPath() + value.getResultFile().getFirstInstance().getName();
        if (value.getResultFile().getAllInstances().size() > 1) {
            nameText += Bundle.DocumentPanel_nameLabel_more_text(value.getResultFile().getAllInstances().size() - 1);
        }
        if (value.getSummary().getNumberOfImages() > 0) {
            numberOfImagesLabel.setText(Bundle.DocumentPanel_numberOfImages_text(value.getSummary().getNumberOfImages()));
            sampleImageLabel.setIcon(new ImageIcon(value.getSummary().getSampleImage()));
        } else if (FileSearchData.getDocTypesWithoutImageExtraction().contains(value.getResultFile().getFirstInstance().getMIMEType())) {
            numberOfImagesLabel.setText(Bundle.DocumentPanel_noImageExtraction_text());
            sampleImageLabel.setIcon(DiscoveryUiUtils.getUnsupportedImageThumbnail());
        } else {
            numberOfImagesLabel.setText(Bundle.DocumentPanel_numberOfImages_noImages());
            sampleImageLabel.setIcon(null);
        }
        nameLabel.setText(nameText);
        previewTextPane.setText(value.getSummary().getSummaryText());
        previewTextPane.setCaretPosition(0);
        DiscoveryUiUtils.setDeletedIcon(value.getResultFile().isDeleted(), isDeletedLabel);
        DiscoveryUiUtils.setScoreIcon(value.getResultFile(), scoreLabel);
        setBackground(isSelected ? SELECTION_COLOR : list.getBackground());
        return this;
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        if (event != null) {
            //gets tooltip of internal panel item mouse is over
            Point point = event.getPoint();
            for (Component comp : getComponents()) {
                if (DiscoveryUiUtils.isPointOnIcon(comp, point)) {
                    String toolTip = ((JComponent) comp).getToolTipText();
                    if (toolTip == null || toolTip.isEmpty()) {
                        return null;
                    } else {
                        return toolTip;
                    }
                }
            }
        }
        return null;
    }

}
