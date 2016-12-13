package com.jbee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jbee on 2016. 12. 12..
 */
public class Receiver extends Thread {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String name = "";
        try {
            name = in.readUTF();
            Server.sendToAll("#" + name + "님이 들어오셨습니다.");
            Server.clients.put(name, out);
            System.out.println("현재 서버 접속자 수는 " + Server.clients.size() + "입니다.");
            while(in != null) {
                Server.sendToAll(in.readUTF());
            }
        } catch(IOException e) {
        } finally {
            Server.sendToAll("#" + name + "님이 나가셨습니다.");
            Server.clients.remove(name);
            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "에서 접속을 종료했습니다.");
            System.out.println("현재 서버 접속자 수는 " + Server.clients.size() + "입니다.");
        }
    }
}
