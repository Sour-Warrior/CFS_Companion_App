package com.example.cfs_vr_companionapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//https://michieldemey.be/blog/network-discovery-using-udp-broadcast/


public class UDPBroadcaster implements Runnable {
    // This will begin a UDP broadcast so that the VR headset can find the IP address of the device running this app

    private Thread thread;
    public DatagramSocket _socket;
    InetAddress clientAddress;
    int clientPort;

    public UDPBroadcaster() {
        this.thread = new Thread( this );
        this.thread.setPriority( Thread.NORM_PRIORITY );
        this.thread.start();
    }


    public void sendMessage(String data) {
        byte[] sendData = data.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
        try {
            _socket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    @Override
    public void run() {
        try {
            this._socket = new DatagramSocket(9999, InetAddress.getByName("0.0.0.0"));
            _socket.setBroadcast(true);
            while (true) {
                System.out.println(getClass().getName() + ">>>Ready to receive");


                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                _socket.receive(packet);


                System.out.println(getClass().getName() + ">>>Packet received from: " + packet.getAddress().getHostAddress());
                System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));


                String message = new String(packet.getData()).trim();
                if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
                    clientAddress = packet.getAddress();
                    clientPort = packet.getPort();
                    // Send test package
                    byte[] sendData = "Hello world".getBytes();

                    //Send a response
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    _socket.send(sendPacket);

                    System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
                }
            }
        } catch (IOException e) {
            System.out.println("error occurred with discovery");
        }
    }
}
