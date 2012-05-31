package com.tacambridge.misc;

/**
 * Converts between byte array and unsigned int/byte
 * @author terrianne
 *
 */
public class Convert {

	public static int getUnsignedInt(int offset, byte[] data) {
		int value = 0;
		for (int i = 0; i < 4; i++)
			value += (data[offset + i] & 0xff) << ((3 - i) * 8);
		return value;
	}

	public static int getUnsignedByte(int offset, byte[] data) {
		return (data[offset] & 0x00ff);
	}
	
	public static byte[] intToByteArray(int value) {
	    byte[] b = new byte[4];
	    for (int i = 0; i < 4; i++) {
	        int offset = (b.length - 1 - i) * 8;
	        b[i] = (byte) ((value >>> offset) & 0xFF);
	    }
	    return b;
	}

}
