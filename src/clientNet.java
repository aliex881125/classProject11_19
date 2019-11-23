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

        try{
            inetADDr = InetAddress.getLocalHost();
            hostIP = inetADDr.getHostAddress();
            hostName =inetADDr.getHostName();
        }catch (UnknownHostException e){
            System.out.println("Can't find IP information");
        }
        byte buff[] = new byte[1024];
        try {
            clientFram.setJta("正在與伺服器建立連線...");
            socket = new Socket(getHostIP(),getPort());
            clientFram.setJta("已經與伺服器取得連線...");
            inputStream=socket.getInputStream();
            int n = inputStream.read(buff);
            clientFram.setJta("從伺服器收到:");
            clientFram.setJta(new String(buff,0,n));
            inputStream.close();
            socket.close();
        }catch (Exception e){
            System.out.println("Error");
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
