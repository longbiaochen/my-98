
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

/**
 *
 * @author Longbiaochen
 */
public class recThread implements Runnable {

    DatagramSocket recSocket;
    HashMap friendMap;
    MainFrame mf;

    public recThread(DatagramSocket recSocket, HashMap friendMap, MainFrame mf) {
        this.recSocket = recSocket;
        this.friendMap = friendMap;
        this.mf = mf;
    }

    public void run() {

        String str, name, recPort, content;
        while (true) {
            try {
                byte[] data = new byte[Global.maxPackSize];
                //byte[] buf = new byte[200];
                DatagramPacket recPacket = new DatagramPacket(data, data.length);
                recSocket.receive(recPacket);
                str = new String(data, 0, recPacket.getLength());

                name = str.split(Global.split)[0];
                recPort = str.split(Global.split)[1];
                content = str.split(Global.split)[2];
                System.out.println("Receive Message:\n" + content + "\nFrom\n" + name + "@" + recPort);

                if (friendMap.containsKey(name)) {
                    FriendPanel fp = (FriendPanel) friendMap.get(name);
                    fp.ip = recPacket.getAddress().toString();
                    fp.ip = fp.ip.substring(1);
                    System.out.println("updated ip=" + fp.ip);
                    //friend is loging on
                    if (fp.state == false) {

                        fp.setIcon(true);
                        fp = mf.removeofflineFriend(fp.panelID);
                        fp.panelID = mf.addonlineFriend(fp);
                        fp.state = true;
                        mf.validate();
                    }
                    //friend is loging on
                    if (content.equals(Global.onlineMsg)) {
                        //update port as msgport
                        fp.port = new Integer(recPort).toString();
                        System.out.println("updated port=" + fp.port);
                        System.out.println("好友 " + fp.name + " 上线了.");
                    //new Thread(new TipThread(fp)).start();
                    } else if (content.equals(Global.offlineMsg)) {
                        //update msg port
                        fp.port = new Integer(recPort).toString();
                        System.out.println("updated port=" + fp.port);
                        fp.setIcon(false);
                        fp = mf.removeonlineFriend(fp.panelID);
                        fp.panelID = mf.addofflineFriend(fp);
                        fp.state = false;
                        fp.cf.receive("我已经下线了:)\n(此信息是系统信息).");
                        mf.validate();

                        System.out.println("好友 " + fp.name + " 下线了.");
                    //new Thread(new TipThread(fp)).start();
                    } else if (content.startsWith(Global.fileReqMsg)) {
                        String fileName = content.substring(Global.fileReqMsg.length());
                        new recFileFrame(fp.ip, recPort, fileName);
                    } else {
                        fp.port = new Integer(recPort).toString();
                        System.out.println("updated port=" + fp.port);

                        if (fp.cf == null) {
                            fp.cf = new ChatFrame(fp);
                            fp.cf.receive(content);
                            fp.cf.validate();
                            fp.cf.setVisible(true);
                        //fp.isChat = true;
                        } else {
                            fp.cf.receive(content);
                            fp.cf.setVisible(true);
                            System.out.println("append message");

                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}


