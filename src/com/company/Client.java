package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Warden on 2017/5/15.
 */
public class Client {
    public static void main (String[] args){
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            //接收
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        String msg = null;
                        try {
                            msg = dataInputStream.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("收到服务器消息"+msg);
                    }
                }
            }.start();
            //发送
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        Scanner scanner = new Scanner(System.in);
                        String str = scanner.next();
                        try {
                            dataOutputStream.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
