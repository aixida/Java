package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void startServer(){
        System.out.println("====启动服务器====");
        try {
            //获取配置文件server.properties中的port
            int port = Integer.parseInt(ServerFileReader.getValue("port"));
            ServerSocket server = new ServerSocket(port);
            while(true){
                Socket socket = server.accept();
                new ServerHandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
