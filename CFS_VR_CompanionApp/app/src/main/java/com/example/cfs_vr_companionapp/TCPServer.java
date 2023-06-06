package com.example.cfs_vr_companionapp;

import android.os.PowerManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private int test;

    private boolean isConnected;

    public TCPServer(int _test)
    {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
        this.isConnected = false;
        this.test = _test;
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

        System.out.println( "waiting for connections" );
        try
        {
            this.socket = serverSocket.accept();
        }
        catch ( IOException e )
        {
            System.out.println( "failed to accept" );
            e.printStackTrace();
        }
        System.out.println( "vr client connected" );

        this.isConnected = true;


        try
        {
            this.dataOutputStream = new DataOutputStream( new BufferedOutputStream( this.socket.getOutputStream() ) );
            this.dataInputStream = new DataInputStream( new BufferedInputStream( this.socket.getInputStream() ) );

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

        // Listen for incoming data
        while ( true )
        {
            try
            {
                byte test = this.dataInputStream.readByte();
                System.out.println( "byte received: "+test );


                if ( test == 42 ) break;
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