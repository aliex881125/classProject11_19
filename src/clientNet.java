import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientNet extends Thread {
    private String hostIP, hostName;
    private InetAddress inetADDr;
    private int port =5300;
    private  client clientFram;
    private Socket socket;
    private OutputStream outStream;
    private InputStream inputStream;

    public clientNet(client fra){
        this.setDaemon(true);
        clientFram=fra;
    }
    public void run() {
        byte buff[] = new byte[1024];
        int n =0;
        try {
            clientFram.appendMessage("Connect to Server...\n");
            socket = new Socket(getHostIP(), getPort());
            clientFram.appendMessage("Connect!!\n");
            clientFram.repaint();
            outStream=socket.getOutputStream();
            inputStream = socket.getInputStream();
            while (true) {
                n = inputStream.read(buff);
                clientFram.appendMessage("Serve:" + new String(buff,0 ,n));
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public void sendMsg(String msg){
        if(socket!=null){
            try{
                outStream.write(msg.getBytes());
            }catch (Exception e){
                JOptionPane.showMessageDialog(clientFram,e.toString(),"Error",1);
            }
        }
    }
    public void disconnect(){
        sendMsg("#disconnect#");
        try{
            socket.close();
            inputStream.close();
            outStream.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(clientFram,e.toString(),"Error",1);
        }
    }
    public String getHostIP(){
        return hostIP;
    }
    public String getHostName(){
        return hostName;
    }
    public int getPort() {return port;}
}
