package com.jbee;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("USAGE : java MultiChat Client 대화명 ");
            System.exit(0);
        }
        try {
            String serverIp = "127.0.0.1";
            Socket socket = new Socket(serverIp, 7779);
            System.out.println("Connecting to " + serverIp);

            Thread sender = new Thread(new Sender(socket, args[0]));
            Thread receiver = new Thread(new Receiver(socket));
            sender.start();
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Sender extends Thread {
        Socket socket;
        DataOutputStream out;
        String name;

        Sender(Socket socket, String name) {
            this.socket = socket;
            try {
                out = new DataOutputStream(socket.getOutputStream());
                this.name = name;
            } catch (Exception e) {
            }
        }

        public void run() {
            Scanner scanner = new Scanner(System.in);
            try {
                if(out != null) {
                    out.writeUTF(name);
                }

                while(out != null) {
                    out.writeUTF("[" + name + "]" + scanner.nextLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Receiver extends Thread {
        Socket socket;
        DataInputStream in;

        Receiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
            }
        }

        public void run() {
            while(in != null) {
                try {
                    System.out.println(in.readUTF());
                } catch(IOException e) {
                }
            }
        }
    }
}
