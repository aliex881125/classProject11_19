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

    public serverNet(serve fra){
        this.setDaemon(true);
        serveFrame=fra;

        try{
            inetADDr = InetAddress.getLocalHost();
            hostIP = inetADDr.getHostAddress();
            hostName =inetADDr.getHostName();
        }catch (UnknownHostException e){
            System.out.println("Can't find IP information");
        }
         try{
             ServerSocket svs = new ServerSocket(5300);
             serveFrame.setjta("等待客戶端得請求中...");
             socket=svs.accept();
             serveFrame.setjta("客戶端已和本機聯上線...");
             outStream =socket.getOutputStream();
             String str ="Hello~~";
             serveFrame.setjta("資料正在傳送中...");
             outStream.write(str.getBytes());
             outStream.close();
             socket.close();
             serveFrame.setjta("資料傳送完畢...");
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
    public int getPort() { return port;}
}
