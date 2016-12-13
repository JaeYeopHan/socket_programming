package com.jbee;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Server {
    static Map clients;

    public Server() {
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }

    public void start() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(7779);
            while (true) {
                System.out.println("[ Ready to connect ]");

                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+ socket.getPort() + " 에서 접속했습니다.");

                Receiver thread = new Receiver(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendToAll(String msg) {
        Iterator it = clients.keySet().iterator();

        while(it.hasNext()) {
            try {
                DataOutputStream out = (DataOutputStream) clients.get(it.next());
                out.writeUTF(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
