package com.example.cfs_vr_companionapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscoverThread implements Runnable {

    public DatagramSocket _socket;
    InetAddress clientAddress;
    int clientPort;


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
                System.out.println(getClass().getName() + ">>>Ready to receive broadcast packets!");

                //Receive a packet
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                _socket.receive(packet);

                //Packet received
                System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
                System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

                //See if the packet holds the right command (message)
                String message = new String(packet.getData()).trim();
                if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
                    clientAddress = packet.getAddress();
                    clientPort = packet.getPort();
                    byte[] sendData = "Hello world".getBytes();

                    //Send a response
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    _socket.send(sendPacket);

                    System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
                }
            }
        } catch (IOException e) {
            System.out.println("error occurred");
        }
    }

    public static DiscoverThread getInstance() {
        return DiscoverThreadHolder.INSTANCE;
    }

    private static class DiscoverThreadHolder {
        private static final DiscoverThread INSTANCE = new DiscoverThread();
    }
}
