/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2014 Basis Technology Corp.
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.sleuthkit.autopsy.coreutils.Logger;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.coreutils.MessageNotifyUtil;
import org.sleuthkit.autopsy.ingest.IngestMessage.MessageType;
import org.sleuthkit.datamodel.Content;

/**
 * Top component which displays something.
 */
final class IngestMessageTopComponent extends TopComponent {

    private static IngestMessageTopComponent instance;
    private static final Logger logger = Logger.getLogger(IngestMessageTopComponent.class.getName());
    private IngestMessageMainPanel messagePanel;
    private IngestManager manager;
    private static String PREFERRED_ID = "IngestMessageTopComponent"; //NON-NLS
    private ActionListener showIngestInboxAction;
    private static final Pattern tagRemove = Pattern.compile("<.+?>");

    public IngestMessageTopComponent() {
        initComponents();
        customizeComponents();
        setName(NbBundle.getMessage(IngestMessageTopComponent.class, "CTL_IngestMessageTopComponent"));
        setToolTipText(NbBundle.getMessage(IngestMessageTopComponent.class, "HINT_IngestMessageTopComponent"));
        //putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);

        showIngestInboxAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IngestMessagesToolbar.getDefault().showIngestMessages();
            }
        };

    }

    private static synchronized IngestMessageTopComponent getDefault() {
        if (instance == null) {
            instance = new IngestMessageTopComponent();
        }
        return instance;
    }

    public static synchronized IngestMessageTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            return getDefault();
        }
        if (win instanceof IngestMessageTopComponent) {
            return (IngestMessageTopComponent) win;
        }

        return getDefault();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDisplayName(org.openide.util.NbBundle.getMessage(IngestMessageTopComponent.class, "IngestMessageTopComponent.displayName")); // NOI18N
        setName(NbBundle.getMessage(this.getClass(), "IngestMessageTopComponent.initComponents.name")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        //logger.log(Level.INFO, "OPENED");
        super.componentOpened();
        //create manager instance
        if (manager == null) {
            manager = IngestManager.getInstance();
        }

    }

    @Override
    public void componentClosed() {
        //logger.log(Level.INFO, "CLOSED");
        super.componentClosed();

        // mark all the messages as seen (this will make the 'New?' columen NOT
        // show a ckeckmark)
        messagePanel.markAllSeen();
    }

    @Override
    protected void componentShowing() {
        //logger.log(Level.INFO, "SHOWING");
        super.componentShowing();

        Mode mode = WindowManager.getDefault().findMode("floatingLeftBottom"); //NON-NLS
        if (mode != null) {
            TopComponent[] tcs = mode.getTopComponents();
            for (int i = 0; i < tcs.length; ++i) {
                if (tcs[i] == this) //already floating
                {
                    this.open();
                    return;
                }
            }
            mode.dockInto(this);
            this.open();
        }
    }

    @Override
    protected void componentHidden() {
        //logger.log(Level.INFO, "HIDDEN");
        super.componentHidden();

    }

    @Override
    protected void componentActivated() {
        //logger.log(Level.INFO, "ACTIVATED");
        super.componentActivated();
    }

    @Override
    protected void componentDeactivated() {
        //logger.log(Level.INFO, "DEACTIVATED");
        super.componentDeactivated();
    }

    @Override
    public boolean canClose() {
        return true;
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void customizeComponents() {
        //custom GUI setup not done by builder
        messagePanel = new IngestMessageMainPanel();
        messagePanel.setOpaque(true);
        //setLayout(new BorderLayout());
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(messagePanel);
    }

    /**
     * Display ingest summary report in some dialog
     */
    public void displayReport(String ingestReport) {

        Object[] options = {NbBundle.getMessage(this.getClass(), "IngestMessageTopComponent.displayReport.option.OK"),
            NbBundle.getMessage(this.getClass(),
            "IngestMessageTopComponent.displayReport.option.GenRpt")};
        final int choice = JOptionPane.showOptionDialog(null,
                ingestReport,
                NbBundle.getMessage(this.getClass(), "IngestMessageTopComponent.msgDlg.ingestRpt.text"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        final String reportActionName = "org.sleuthkit.autopsy.report.ReportAction"; //NON-NLS
        Action reportAction = null;

        //find action by name from action lookup, without introducing cyclic dependency
        if (choice == JOptionPane.NO_OPTION) {
            List<? extends Action> actions = Utilities.actionsForPath("Toolbars/File"); //NON-NLS
            for (Action a : actions) {
                //separators are null actions
                if (a != null) {
                    if (a.getClass().getCanonicalName().equals(reportActionName)) {
                        reportAction = a;
                        break;
                    }
                }
            }

            if (reportAction == null) {
                logger.log(Level.SEVERE, "Could not locate Action: " + reportActionName); //NON-NLS
            } else {
                reportAction.actionPerformed(null);
            }

        }

    }

    /**
     * Display IngestMessage from module (forwarded by IngestManager)
     */
    public void displayMessage(IngestMessage ingestMessage) {
        messagePanel.addMessage(ingestMessage);

        //post special messages to notification area
        MessageType ingestMessageType = ingestMessage.getMessageType();
        if (ingestMessageType.equals(MessageType.ERROR)
                || ingestMessageType.equals(MessageType.WARNING)) {
            MessageNotifyUtil.MessageType notifyMessageType
                    = ingestMessageType.equals(MessageType.ERROR)
                            ? MessageNotifyUtil.MessageType.ERROR
                            : MessageNotifyUtil.MessageType.WARNING;

            String subject = ingestMessage.getSubject();
            String details = ingestMessage.getDetails();
            if (details == null) {
                details = "";
            }
            //strip html tags in case they are present in ingest message
            details = stripHtmlTags(details);

            MessageNotifyUtil.Notify.show(subject, details,
                    notifyMessageType, showIngestInboxAction);
        }
    }

    public int getMessagesCount() {
        return messagePanel.getMessagesCount();
    }

    public void clearMessages() {
        messagePanel.clearMessages();
    }

    public void displayIngestDialog(final Content ingestDataSource) {
        /*
         * final IngestDialog ingestDialog = new IngestDialog();
         * ingestDialog.setImage(image); ingestDialog.display();
         */
    }

    public Action[] getActions() {
        //disable TC toolbar actions
        return new Action[0];
    }

    private static String stripHtmlTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        Matcher m = tagRemove.matcher(string);
        return m.replaceAll("");
    }
}
