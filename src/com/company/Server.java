package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static List<InputConnection> inputConnectionList = new ArrayList<>();

    public static void main(String[] args) {
        startServer();
    }
    public static void  startServer(){
        try {
            //open port8888
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("监听端口：8888");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("有连接过来"+socket);
                InputStream inputStream = socket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                InputConnection inputConnection = new InputConnection();
                inputConnection.setDataInputStream(dataInputStream);
                inputConnection.setDataOutputStream(dataOutputStream);
                inputConnection.setSocket(socket);
                inputConnection.setRemoteAddress(socket.getRemoteSocketAddress().toString());
                inputConnectionList.add(inputConnection);
                receiveMsg(dataInputStream,socket.getRemoteSocketAddress().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void receiveMsg(DataInputStream dataInputStream,String remoteAdress){
        //接收
        new Thread(){
            @Override
            public void run() {
                while (true){
                    String msg = null;
                    try {
                        msg = dataInputStream.readUTF();
                    } catch (IOException e) {
                        System.out.println("客户端已下线");
                        e.printStackTrace();
                        return;
                    }
                    System.out.println(remoteAdress+"：\n"+msg);
                    sendMsgs(remoteAdress,msg);
                }
            }
        }.start();

    }
    private static void sendMsgs(String remoteAdress,String msg){
        //发送
        new Thread(){
            @Override
            public void run() {
                for (InputConnection inputConnection:inputConnectionList
                     ) {
                    System.out.println(remoteAdress);
                    System.out.println(inputConnection.getRemoteAddress());
                    DataOutputStream dataOutputStream = inputConnection.getDataOutputStream();
                    if (!inputConnection.getRemoteAddress().equals(remoteAdress)){
                        try {
                            dataOutputStream.writeUTF(msg);
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                    }
                }

            }
        }.start();
    }
}
