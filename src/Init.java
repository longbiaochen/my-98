
import com.meterware.httpunit.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import javax.swing.UIManager;

public class Init {

    HashMap friendMap = new HashMap();
    //HashMap ipMap = new HashMap();
    WebConversation wc;
    MainFrame mf;
    DatagramSocket recSocket;
    //public MyInfo myself;

    public Init(WebConversation wc) {
        this.wc = wc;

        try {
            mf = new MainFrame(wc);
            mf.setVisible(true);
            getMyself();
            setMyself();
            getFriends();
            sayImOn();

            new Thread(new recThread(recSocket, friendMap, mf)).start();  


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Initialization Failure.");
        }

    }

    public void sayImOn() {
        String cnt = Global.myName + Global.split + Global.myPort + Global.split + Global.onlineMsg;
        byte data[] = cnt.getBytes();
        FriendPanel fp;
        DatagramSocket sendsocket;
        try {
            sendsocket = new DatagramSocket();
            for (int i = 0; i < mf.onlineCount(); i++) {
                try {
                    fp = (FriendPanel) this.mf.onlinePanel.getComponent(i);
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

    public void getMyself() {
        HttpUnitOptions.setScriptingEnabled(false);
        WebResponse wr;
        String html;

        try {
            wr = wc.getResponse("http://www.cc98.org/usermanager.asp");
            html = wr.getText();
            //System.out.println(html);
            int beg = html.indexOf("用户头像");
            html = html.substring(beg);
            beg = html.indexOf("img src=") + 8;
            html = html.substring(beg);
            int end = html.indexOf(" ");
            Global.myLogoURLstr = "http://www.cc98.org/" + html.substring(1, end - 1);
        //System.out.println(Global.myLogoURLstr);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Set Myself Failure.");
        }

    }

    public void setMyself() {
        int portid = Global.msgPort;
        while (true) {
            try {
                recSocket = new DatagramSocket(portid);
                break;
            } catch (Exception e) {
                portid += 1;
            //System.out.println("Create recSocket Error");
            }
        }
        System.out.println("msg portid=" + portid);

        HttpUnitOptions.setScriptingEnabled(false);
        try {
            WebRequest req = new PostMethodWebRequest("http://www.cc98.org/modifyadd.asp?action=updat");
            Global.myIP = java.net.InetAddress.getLocalHost().getHostAddress();
            Global.myPort = new Integer(portid).toString();
            Global.myAddr = "IP:" + Global.myIP + ":" + Global.myPort;
            System.out.println("MYIP:" + Global.myAddr);
            String myAddr = Transfer.encrypt(Global.myAddr);
            req.setParameter("homepage", myAddr);
            WebResponse resp = wc.getResponse(req);
        //System.out.println(resp.getText());

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void getFriends() {

        HttpUnitOptions.setScriptingEnabled(false);
        String name, homepage;
        FriendPanel fp;

        try {
            WebResponse wr = wc.getResponse("http://www.cc98.org/friendlist.asp");
            //String html = wr.getText();
            //System.out.println(html);
            WebTable[] wbs = wr.getTables();

            if (wbs[4].getCellAsText(1, 0).equals("您的好友列表中没有任何内容。")) {
                friendMap.clear();
                return;
            } else {
                for (int i = 1; i < wbs[4].getRowCount() - 1; i++) {
                    name = wbs[4].getCellAsText(i, 0);
                    homepage = wbs[4].getCellAsText(i, 2);
                    System.out.println("NAME:" + name + "HOMEPAGE:" + homepage);

                    if (friendMap.containsKey(name)) {
                        fp = (FriendPanel) friendMap.get(name);

                        if ((fp.homepage.equals("")) && (homepage.equals("")) == false) {
                            fp.useHomepage(homepage);
                            if (fp.state == true) {
                                fp = mf.removeofflineFriend(fp.panelID);
                                fp.panelID = mf.addonlineFriend(fp);
                            }
                        } else if ((fp.state == true) && (homepage.equals(""))) {
                            fp.useHomepage(homepage);
                            fp = mf.removeonlineFriend(fp.panelID);
                            fp.panelID = mf.addofflineFriend(fp);
                        }

                    } else {
                        fp = new FriendPanel(name, homepage, wc);
                        System.out.println("ADD:NAME=" + name + "\nADDR=" + fp.ip + ":" + fp.port);
                        if (fp.state == true) {
                            fp.panelID = mf.addonlineFriend(fp);
                        } else {
                            fp.panelID = mf.addofflineFriend(fp);
                        }
                        friendMap.put(name, fp);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get Friendlist Failure.");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new javax.swing.UIManager().getSystemLookAndFeelClassName());

        } catch (Exception e) {
        }

        new LoginFrame().setVisible(true);


    }
}
