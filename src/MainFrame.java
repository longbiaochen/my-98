/*
 * TestFrame.java
 *
 * Created on 2008年10月11日, 下午4:28
 */

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author  Administrator
 */
public class MainFrame extends javax.swing.JFrame {

    WebConversation wc;

    /** Creates new form TestFrame */
    public MainFrame(WebConversation wc) {
        this.wc = wc;

        initComponents();
        this.setTitle(Global.myName + "-My98");
        try {

            super.setIconImage(Toolkit.getDefaultToolkit().getImage("/FrameLogo.gif"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        LayoutManager onlineLayout = new BoxLayout(onlinePanel, BoxLayout.Y_AXIS);
        onlinePanel.setLayout(onlineLayout);
        LayoutManager offlineLayout = new BoxLayout(offlinePanel, BoxLayout.Y_AXIS);
        offlinePanel.setLayout(offlineLayout);
        //this.getContentPane().setBackground(new java.awt.Color(141, 169, 210));
        this.setSize(this.getSize().width, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height * 2 / 3);
        this.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth() - 20, 20);
    }

    public int onlineCount() {
        return this.onlinePanel.getComponentCount();
    }

    public int offlineCount() {
        return this.offlinePanel.getComponentCount();
    }

    public FriendPanel removeonlineFriend(int index) {
        FriendPanel fp = (FriendPanel) onlinePanel.getComponent(index);
        onlinePanel.remove(index);
        onlinePanel.validate();
        return fp;
    }

    public FriendPanel removeofflineFriend(int index) {
        FriendPanel fp = (FriendPanel) offlinePanel.getComponent(index);
        offlinePanel.remove(index);
        offlinePanel.validate();
        return fp;
    }

    public int addonlineFriend(FriendPanel fp) {


        onlinePanel.add(fp);
        onlinePanel.validate();
        //this.pack();
        //this.setVisible(true);
        return onlinePanel.getComponentCount() - 1;

    }

    public int addofflineFriend(FriendPanel fp) {
        //LayoutManager l = new BoxLayout(offlinePanel, BoxLayout.Y_AXIS);
        //offlinePanel.setLayout(l);

        offlinePanel.add(fp);
        offlinePanel.validate();
        //this.pack();
        //this.setVisible(true);
        return offlinePanel.getComponentCount() - 1;

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendlistPanel = new javax.swing.JPanel();
        offlineBox = new javax.swing.JCheckBox();
        onlinePanel = new javax.swing.JPanel();
        offlinePanel = new javax.swing.JPanel();
        onlineBox = new javax.swing.JCheckBox();
        extraPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        myPanel.setBackground(java.awt.Color.white);
        myPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout myPanelLayout = new javax.swing.GroupLayout(myPanel);
        myPanel.setLayout(myPanelLayout);
        myPanelLayout.setHorizontalGroup(
            myPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );
        myPanelLayout.setVerticalGroup(
            myPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 74, Short.MAX_VALUE)
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 153, 153));
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setMinimumSize(null);

        friendlistPanel.setBackground(java.awt.Color.white);
        friendlistPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        offlineBox.setBackground(java.awt.Color.white);
        offlineBox.setText("离线好友");
        offlineBox.setBorder(null);
        offlineBox.setFocusPainted(false);
        offlineBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/on.gif"))); // NOI18N
        offlineBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/off.gif"))); // NOI18N
        offlineBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                offlineBoxMouseClicked(evt);
            }
        });

        onlinePanel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout onlinePanelLayout = new javax.swing.GroupLayout(onlinePanel);
        onlinePanel.setLayout(onlinePanelLayout);
        onlinePanelLayout.setHorizontalGroup(
            onlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        onlinePanelLayout.setVerticalGroup(
            onlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        offlinePanel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout offlinePanelLayout = new javax.swing.GroupLayout(offlinePanel);
        offlinePanel.setLayout(offlinePanelLayout);
        offlinePanelLayout.setHorizontalGroup(
            offlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        offlinePanelLayout.setVerticalGroup(
            offlinePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        onlineBox.setBackground(java.awt.Color.white);
        onlineBox.setText("在线好友");
        onlineBox.setBorder(null);
        onlineBox.setFocusPainted(false);
        onlineBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/on.gif"))); // NOI18N
        onlineBox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/off.gif"))); // NOI18N
        onlineBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onlineBoxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout friendlistPanelLayout = new javax.swing.GroupLayout(friendlistPanel);
        friendlistPanel.setLayout(friendlistPanelLayout);
        friendlistPanelLayout.setHorizontalGroup(
            friendlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(friendlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(onlineBox)
                    .addComponent(offlineBox)
                    .addComponent(offlinePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(onlinePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        friendlistPanelLayout.setVerticalGroup(
            friendlistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendlistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(onlineBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(onlinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(offlineBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(offlinePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(friendlistPanel);

        extraPanel.setBackground(java.awt.Color.white);
        extraPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout extraPanelLayout = new javax.swing.GroupLayout(extraPanel);
        extraPanel.setLayout(extraPanelLayout);
        extraPanelLayout.setHorizontalGroup(
            extraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );
        extraPanelLayout.setVerticalGroup(
            extraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(extraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extraPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void onlineBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineBoxMouseClicked
    if (onlineBox.isSelected()) {
        onlinePanel.setVisible(false);
    } else {
        onlinePanel.setVisible(true);
    }// TODO add your handling code here:
}//GEN-LAST:event_onlineBoxMouseClicked

private void offlineBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offlineBoxMouseClicked
    if (offlineBox.isSelected()) {
        offlinePanel.setVisible(false);
    } else {
        offlinePanel.setVisible(true);
    }// TODO add your handling code here:// TODO add your handling code here:
}//GEN-LAST:event_offlineBoxMouseClicked

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    // TODO add your handling code here:
}//GEN-LAST:event_formWindowClosed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    System.out.println("Exiting...");
    HttpUnitOptions.setScriptingEnabled(false);
    try {
        WebRequest req = new PostMethodWebRequest("http://www.cc98.org/modifyadd.asp?action=updat");
        req.setParameter("homepage", "");
        WebResponse resp = wc.getResponse(req);
    //System.out.println(resp.getText());

    } catch (Exception e) {
        e.printStackTrace();

    }

    sayImOff();
// TODO add your handling code here:
}//GEN-LAST:event_formWindowClosing
    public void sayImOff() {
        String cnt = Global.myName + Global.split + Global.myPort + Global.split + Global.offlineMsg;
        byte data[] = cnt.getBytes();
        FriendPanel fp;
        DatagramSocket sendsocket;
        try {
            sendsocket = new DatagramSocket();
            for (int i = 0; i < this.onlineCount(); i++) {
                try {
                    fp = (FriendPanel) this.onlinePanel.getComponent(i);
                    System.out.println("find friend:" + fp.name);
                    Integer portid = Integer.parseInt(fp.port);
                    System.out.println("OnlineMessage:" + cnt + "\nSent to:\n" + fp.ip + ":" + portid);
                    DatagramPacket sendpacket = new DatagramPacket(data, data.length, InetAddress.getByName(fp.ip), portid);
                    sendsocket.send(sendpacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new javax.swing.UIManager().getSystemLookAndFeelClassName());

        } catch (Exception e) {
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel extraPanel;
    protected javax.swing.JPanel friendlistPanel;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JPanel myPanel;
    protected javax.swing.JCheckBox offlineBox;
    protected javax.swing.JPanel offlinePanel;
    protected javax.swing.JCheckBox onlineBox;
    public javax.swing.JPanel onlinePanel;
    // End of variables declaration//GEN-END:variables
}
