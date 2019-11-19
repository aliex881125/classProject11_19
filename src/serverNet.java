import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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

    public serverNet(serve fra){
        this.setDaemon(true);
        serveFrame=fra;

        try{
            inetADDr = InetAddress.getLocalHost();
            hostIP = inetADDr.getHostAddress();
            hostName =inetADDr.getHostName();
        }catch (UnknownHostException e){
            System.out.println("can't");
        }
    }
    public String getHostIP(){
        return hostIP;
    }
    public String getHostName(){
        return hostName;
    }
}
