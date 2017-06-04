package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Warden on 2017/6/4.
 */
public class InputConnection {
    private DataInputStream dataInputStream;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private String RemoteAddress;

    public String getRemoteAddress() {
        return RemoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        RemoteAddress = remoteAddress;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
