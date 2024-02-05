 package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) throws IOException {

		byte[] data;
		
		// TODO - START
		
		data = message.getData();
		outStream.writeInt(data.length);
		outStream.write(data);
		outStream.flush();
			
		// TODO - END

	}

	public Message receive() {

		Message message = null;
		byte[] data;
		
		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message
		try {
			int length = inStream.readInt();
			if(length > 0) {
				data = new byte[length];
				inStream.readFully(data);
				message = new Message(data);
			}
		} catch (IOException e) {
			System.out.println("Feil i innlesing av header!");
			e.printStackTrace();
		}
		
		
		// TODO - END
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}