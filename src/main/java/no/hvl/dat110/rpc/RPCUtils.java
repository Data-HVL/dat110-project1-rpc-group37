package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		
		rpcmsg = new byte[payload.length +1];
		rpcmsg[0] = rpcid;
		
		//kopierer hele payload inn i rpcmsg, hvor 1. index = rpcid
		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		
		
		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// TODO - START
		
		payload = new byte[rpcmsg.length - 1];
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		
		//henter ut payload fra RPC melding
		System.arraycopy(rpcmsg, 1, payload, 0, rpcmsg.length - 1);
		
		// TODO - END
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		//konverterer str til et byte-array av type UTF-8
		encoded = str.getBytes(StandardCharsets.UTF_8);
		
		// TODO - END
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		// TODO - START 
		
		decoded = new String(data, StandardCharsets.UTF_8);		
		// TODO - END
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		//returnerer gyldig byte array, med ingen verdi
		encoded = new byte[0];
				
		// TODO - END
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO
	
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		//Må bruke ByteBuffer her for automatisk konvertering fra int til byte
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(x);
		
		//Gjør nå om bufferet til en byte[]
		encoded = buffer.array();
		
		
		// TODO - END
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START 
		
		if(data.length != 4) {
			throw new IllegalArgumentException("Error unmarshallInteger. bytes != 4");
		}
		
		ByteBuffer buffer = ByteBuffer.wrap(data);
		decoded = buffer.getInt();
		
		// TODO - END
		
		return decoded;
		
	}
}
