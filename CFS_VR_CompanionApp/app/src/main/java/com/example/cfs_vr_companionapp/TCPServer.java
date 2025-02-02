package com.example.cfs_vr_companionapp;

import android.os.PowerManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer implements Runnable
{

    private Thread thread;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private BufferedReader bufferedReader;

    String message;


    private boolean isConnected;

    public TCPServer()
    {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
        this.isConnected = false;
        message = "";

    }

    @Override
    public void run()
    {
        try
        {
            this.serverSocket = new ServerSocket( 6000 );
        }
        catch ( IOException e )
        {
            System.out.println( "failed to start server socket" );
            e.printStackTrace();
        }

        Log.d( MainActivity.TAG, "waiting for connections" );
        try
        {
            this.socket = serverSocket.accept();
        }
        catch ( IOException e )
        {
            Log.d(MainActivity.TAG, "idk");
            e.printStackTrace();
        }
        System.out.println( "vr client connected" );
        MainActivity.setClientConnected(true);

        this.isConnected = true;

        try
        {
            this.dataOutputStream = new DataOutputStream( new BufferedOutputStream( this.socket.getOutputStream() ) );
            this.dataInputStream = new DataInputStream( new BufferedInputStream( this.socket.getInputStream() ) );
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));


        }
        catch ( IOException e )
        {
            System.out.println( "failed to create streams" );
            e.printStackTrace();
        }

        try
        {
            byte[] testData = "Ping from Android device".getBytes();
            this.dataOutputStream.write(testData );
            this.dataOutputStream.flush();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to send" );
            e.printStackTrace();
        }

        while ( true )
        {
            try
            {
                int x = this.dataInputStream.read();
                System.out.println(x);
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                break;
            }
        }

        System.out.println( "server thread stopped" );
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Begin a new thread and send a message to the TCP client
    public void sendMessage(String msg) {

        Thread messageThread = new Thread(new Runnable()  {
            // New thread for when the app needs to send a message
            @Override
            public void run() {
                if (isConnected && dataOutputStream != null && dataInputStream != null) {
                    try {
                        byte[] bytes = msg.getBytes();
                        dataOutputStream.write(bytes);
                        dataOutputStream.flush();

                    } catch (Exception e) {
                        System.out.println("failed to send");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No client connected");
                }
            }
        });

        messageThread.start();
        return;
    }

}