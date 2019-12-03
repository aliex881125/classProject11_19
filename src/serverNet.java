import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverNet extends Thread {
    private String hostIP, hostName;
    private InetAddress inetADDr;
    private int port =5300;
    private  serve serveFrame;
    private Socket socket;
    private OutputStream outStream;
    private InputStream inputStream;
    private ServerSocket serverSocket;

    public serverNet(serve fra) {
        this.setDaemon(true);
        serveFrame = fra;

        try {
            inetADDr = InetAddress.getLocalHost();
            hostIP = inetADDr.getHostAddress();
            hostName = inetADDr.getHostName();
        } catch (UnknownHostException e) {
            System.out.println("Can't find IP information");
        }
    }
    public void run(){
        byte data[] = new byte[1024];
        int n;
        try{
            serverSocket= new ServerSocket(port);
            serveFrame.appendMessage("Server start  watting client connect....");
            serveFrame.repaint();
            socket=serverSocket.accept();
            serveFrame.appendMessage("Client connect....");
            outStream=socket.getOutputStream();
            while (true) {
                inputStream = socket.getInputStream();
                n = inputStream.read(data);
                if ((new String(data, 0, n)).equals("#disConnect")) {
                    serveFrame.appendMessage("Client is disconnect#");
                    close();
                    break;
                } else {
                    serveFrame.appendMessage("Client:" + new String(data, 0, n));
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(serveFrame , e.toString() , "Error",1);
        }

    }
    public void sendMsg(String msg){
        if(socket!=null){
            try{
                outStream.write(msg.getBytes());
            }catch (Exception e){
                JOptionPane.showMessageDialog(serveFrame,e.toString(),"Error",1);
            }
        }
    }
    public void close(){
        try {
            outStream.close();
            inputStream.close();
            socket.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(serveFrame,e.toString(),"Error",1);
        }


    }

    public String getHostIP(){
        return hostIP;
    }
    public String getHostName(){
        return hostName;
    }
    public int getPort() { return port;}
}
