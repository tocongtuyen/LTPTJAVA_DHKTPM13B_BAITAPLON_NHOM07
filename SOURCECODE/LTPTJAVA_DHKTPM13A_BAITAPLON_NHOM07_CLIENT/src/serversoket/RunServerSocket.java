package serversoket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

import entities.BenhNhan;
import ui.KhamBenh_GUI;

public class RunServerSocket {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
    	ServerSocket ss = new ServerSocket(7777);
        while(true) {
        	try {
        		
        		Gson gson = new Gson();
        		
        		System.out.println("ServerSocket awaiting connections...");
                Socket client = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
                System.out.println("Connection from " + client + "!");

                // get the input stream from the connected socket
                InputStream inputStream = client.getInputStream();
                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                // read the list of messages from the socket
                List<String> listOfMessages = (List<String>) objectInputStream.readObject();
                System.out.println("Received [" + listOfMessages.size() + "] messages from: " + client);
                // print out the text of every message
                System.out.println("All messages:");
                
                
                listOfMessages.forEach((msg)-> {
                	BenhNhan bn = gson.fromJson(msg, BenhNhan.class);
                	
                	System.out.println(bn.getMaBN());
                });

                System.out.println("Closing sockets.");
                client.close();
        	} catch (Exception e) {
				break;
			}
            
        }
        ss.close();
    }
}