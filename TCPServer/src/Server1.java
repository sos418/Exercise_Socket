import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {  
    public static void main(String[] args) throws Exception{    
    	//服務端在20001端口監聽客戶端請求的TCP連接
        ServerSocket server = new ServerSocket(20001);  
        Socket client = null;  
        boolean f = true;  
        while(f){    
        	//等待客戶端的連接，如果沒有獲取連接
            client = server.accept();  
            System.out.println("與客戶端連接成功！");  
            //為每個客戶端開啟一個線程
            new Thread(new ServerThread(client)).start();  
        }  
        server.close();  
    }  
}  