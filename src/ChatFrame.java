
import com.meterware.httpunit.WebConversation;
import java.awt.Image;
import java.awt.Image.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author  Longbiaochen
 */
public class ChatFrame extends javax.swing.JFrame {
    //MyInfo myself;
    //Image srcImage;
    //ImageIcon friendLogoImage;

    /** Creates new form ChatFrame */
    Socket socket;
    BufferedReader socketReader;
    PrintWriter socketWriter;
    String name, ip, port;
    DatagramSocket sendsocket;
    DatagramSocket recsocket;
    boolean isChat, friendState;
    File file;

    public ChatFrame(FriendPanel fp) {
        this.name = fp.name;
        this.ip = fp.ip;
        this.port = fp.port;
        this.isChat = true;
        this.friendState = fp.state;

        try {
            sendsocket = new DatagramSocket();

        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        this.typeArea.setToolTipText("按Enter键发送消息.");
        this.setTitle("与" + name + "聊天");
        this.setVisible(true);
        setLogoImage(fp.getLogoURLstr(), Global.myLogoURLstr);

    //receive(fp.name);

    }

    public void receive(String content) {

        messageArea.append(name + "说:\n");

        messageArea.append(content);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            }
        });
        //jScrollPane1.getViewport().setViewPosition(new Point(jScrollPane1.getWidth(),jScrollPane1.getHeight()));//
        messageArea.validate();


    }

    public void setLogoImage(String friendLogoURLstr, String myLogoURLstr) {
        try {
            URL friendLogoURL = new URL(friendLogoURLstr);
            URL myselfLogoURL = new URL(myLogoURLstr);
            Image friendLogoImg = new ImageIcon(friendLogoURL).getImage();
            friendLogoImg = friendLogoImg.getScaledInstance(120, 120, (Image.SCALE_SMOOTH));
//显示resized图片
            friendLogoLabel.setIcon(new ImageIcon(friendLogoImg));


            Image myselfLogoImg = new ImageIcon(myselfLogoURL).getImage();
            myselfLogoImg = myselfLogoImg.getScaledInstance(120, 120, (Image.SCALE_SMOOTH));
//显示resized图片
            myselfLogoLabel.setIcon(new ImageIcon(myselfLogoImg));
            myselfLogoLabel.updateUI();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("load internet image failure");
        }
    }

    public void sendMsg() {
        if (this.friendState == true) {
            try {
                String cnt = typeArea.getText();
                typeArea.setText("");
                messageArea.append("\n我说:\n" + cnt + "\n");
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {

                        jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
                    }
                });
                //jScrollPane1.getViewport().setViewPosition(new Point(jScrollPane1.getWidth(),jScrollPane1.getHeight()));//
                messageArea.validate();
                cnt = Global.myName + Global.split + Global.myPort + Global.split + cnt + '\n';
                byte data[] = cnt.getBytes();
                Integer portid = Integer.parseInt(port);
                System.out.println("Message:" + cnt + "\nSent to:\n" + ip + ":" + portid);
                DatagramPacket sendpacket = new DatagramPacket(data, data.length, InetAddress.getByName(ip), portid);
                sendsocket.send(sendpacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            messageArea.append("\n系统信息:\n对方已经下线,您的信息将无法送达.\n");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        toolPanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        friendLogoPanel = new javax.swing.JPanel();
        friendLogoLabel = new javax.swing.JLabel();
        myLogoPanel = new javax.swing.JPanel();
        myselfLogoLabel = new javax.swing.JLabel();
        typePanel = new javax.swing.JScrollPane();
        typeArea = new javax.swing.JTextArea();
        showLogoButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.white);
        setLocationByPlatform(true);

        menuPanel.setBackground(java.awt.Color.white);

        jButton1.setText("传文件");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        messageArea.setColumns(20);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setRows(5);
        messageArea.setWrapStyleWord(true);
        messageArea.setMinimumSize(new java.awt.Dimension(104, 24));
        jScrollPane1.setViewportView(messageArea);

        toolPanel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout toolPanelLayout = new javax.swing.GroupLayout(toolPanel);
        toolPanel.setLayout(toolPanelLayout);
        toolPanelLayout.setHorizontalGroup(
            toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        toolPanelLayout.setVerticalGroup(
            toolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        logoPanel.setBackground(java.awt.Color.white);

        friendLogoPanel.setBackground(java.awt.Color.white);
        friendLogoPanel.setPreferredSize(new java.awt.Dimension(120, 120));

        friendLogoLabel.setBackground(java.awt.Color.white);
        friendLogoLabel.setText("  该用户未上传头像");

        javax.swing.GroupLayout friendLogoPanelLayout = new javax.swing.GroupLayout(friendLogoPanel);
        friendLogoPanel.setLayout(friendLogoPanelLayout);
        friendLogoPanelLayout.setHorizontalGroup(
            friendLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(friendLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        friendLogoPanelLayout.setVerticalGroup(
            friendLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(friendLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        myLogoPanel.setBackground(java.awt.Color.white);
        myLogoPanel.setPreferredSize(new java.awt.Dimension(120, 120));

        myselfLogoLabel.setBackground(java.awt.Color.white);
        myselfLogoLabel.setText("  该用户未上传头像");

        javax.swing.GroupLayout myLogoPanelLayout = new javax.swing.GroupLayout(myLogoPanel);
        myLogoPanel.setLayout(myLogoPanelLayout);
        myLogoPanelLayout.setHorizontalGroup(
            myLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myselfLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        myLogoPanelLayout.setVerticalGroup(
            myLogoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myselfLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendLogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myLogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendLogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(myLogoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        typePanel.setHorizontalScrollBar(null);

        typeArea.setColumns(20);
        typeArea.setLineWrap(true);
        typeArea.setRows(2);
        typeArea.setFocusCycleRoot(true);
        typeArea.setMinimumSize(new java.awt.Dimension(4, 4));
        typeArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeAreaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                typeAreaKeyTyped(evt);
            }
        });
        typePanel.setViewportView(typeArea);

        showLogoButton.setText("隐藏头像");
        showLogoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLogoButtonActionPerformed(evt);
            }
        });

        sendButton.setText("发送");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(typePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showLogoButton)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                    .addComponent(toolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {sendButton, showLogoButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showLogoButton)
                        .addGap(14, 14, 14)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                    .addComponent(typePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void showLogoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLogoButtonActionPerformed
    if (logoPanel.isVisible()) {
        logoPanel.setVisible(false);
        showLogoButton.setText("显示头像");

    } else {
        logoPanel.setVisible(true);
        showLogoButton.setText("隐藏头像");

    }
// TODO add your handling code here:
}//GEN-LAST:event_showLogoButtonActionPerformed

private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
    sendMsg();
}//GEN-LAST:event_sendButtonActionPerformed

private void typeAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeAreaKeyTyped
}//GEN-LAST:event_typeAreaKeyTyped

private void typeAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeAreaKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        System.out.println("sending...");
        sendMsg();
    }// TODO add your handling code here:
}//GEN-LAST:event_typeAreaKeyPressed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
        file = fc.getSelectedFile();
        System.out.println("Open: " + file.getName());
        new Thread(new sendFileThread()).start();
    } else {
        System.out.println(returnVal);
    }

// TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed
    class sendFileThread implements Runnable {

        public void run() {
            //start file server socket
            int fportid = Global.filePort;
            while (true) {
                try {
                    ServerSocket fss = new ServerSocket(fportid);//fileserversocket                  
                    break;
                } catch (Exception e) {
                    fportid += 1;
                }
            }
            System.out.println("file portid=" + fportid);

            //send connection request
            DatagramSocket fileReqSocket;
            try {
                fileReqSocket = new DatagramSocket();

                String cnt = Global.myName + Global.split + Global.myPort + Global.split + Global.fileReqMsg;
                byte data[] = cnt.getBytes();
                Integer portid = Integer.parseInt(port);
                DatagramPacket sendpacket = new DatagramPacket(data, data.length, InetAddress.getByName(ip), portid);
                fileReqSocket.send(sendpacket);
                //wait for connection confirm
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new javax.swing.UIManager().getSystemLookAndFeelClassName());

        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                WebConversation wc = new WebConversation();


                FriendPanel fp = new FriendPanel("test", "B0tWVQk7VGJRZ1EtUTBQN1snVTVVNlR1BzJVNQFsUD4DMwFgBjsANQ==", wc);
                new ChatFrame(fp);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel friendLogoLabel;
    private javax.swing.JPanel friendLogoPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JPanel myLogoPanel;
    private javax.swing.JLabel myselfLogoLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton showLogoButton;
    private javax.swing.JPanel toolPanel;
    private javax.swing.JTextArea typeArea;
    private javax.swing.JScrollPane typePanel;
    // End of variables declaration//GEN-END:variables
}