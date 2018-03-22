package com.sun.slava.sun;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.Socket;



public class Client {
    private String mServerName = "192.168.43.109";
    private int mServerPort = 1111;
    private Socket mSocket = null;
    private static final String LOG_TAG = "myServerApp";


    public void openConnection() throws Exception {
        closeConnection();
        try {
            mSocket = new Socket(mServerName, mServerPort);

        } catch (IOException e) {

            //throw new Exception("Невозможно создать сокет: " + e.getMessage());
        }
    }


    public void closeConnection() {
        if (mSocket != null && !mSocket.isClosed()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                //Log.e(LOG_TAG, "Невозможно закрыть сокет: " + e.getMessage());
            } finally {
                mSocket = null;
            }
        }
        mSocket = null;
    }


    public void sendData(byte[] data) throws Exception {
        if (mSocket == null || mSocket.isClosed()) {
            throw new Exception("Невозможно отправить данные. Сокет не создан или закрыт");
        }
        try {
            mSocket.getOutputStream().write(data);
            mSocket.getOutputStream().flush();
        } catch (IOException e) {
            //throw new Exception("Невозможно отправить данные: " + e.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeConnection();
    }







}