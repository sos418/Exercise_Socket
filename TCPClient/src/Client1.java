import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client1 {  
    public static void main(String[] args) throws IOException {    
    	//客戶端請求與本機在20001端口建立TCP連線
        Socket client = new Socket("127.0.0.1", 20001);  
        client.setSoTimeout(10000);  
        //獲取鍵盤輸入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
        //獲取Socket的輸出流，用來發送數據到服務端
        PrintStream out = new PrintStream(client.getOutputStream());      
        //獲取Socket的輸入流，用來接收從服務端發送過來的數據
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));  
        boolean flag = true;  
        while(flag){  
            System.out.print("输入訊息：");  
            String str = input.readLine();  
            //發送數據到服務端
            out.println(str);  
            if("bye".equals(str)){  
                flag = false;  
            }else{  
                try{  
                	//從服務端接收數據有時間限制(系統自設，也可以自己設置)，超過這個時間，便會拋出異常
                    String echo = buf.readLine();  
                    System.out.println(echo);  
                }catch(SocketTimeoutException e){  
                    System.out.println("Time out, No response");  
                }  
            }  
        }  
        input.close();  
        if(client != null)
            client.close();   
        }  
    }  