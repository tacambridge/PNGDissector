package com.tacambridge.png;

/**
 * 
 * @author terrianne
 *
 */
public class PNGChunk {

	private int length; //4bytes
	private byte[] chunkType; //4bytes 
	private byte[] data; //<length> bytes
	private long decodedCrc;
	private int encodedCrc; //4bytes

	/**
	 * 
	 */
	protected PNGChunk() {
		super();
	}

	/**
	 * 
	 * @param length
	 * @param chunkType
	 * @param data
	 * @param decodedCrc
	 * @param encodedCrc
	 */
	public PNGChunk(int length, byte[] chunkType, byte[] data, long decodedCrc,
			int encodedCrc) {
		super();
		this.length = length;
		this.chunkType = chunkType;
		this.data = data;
		this.decodedCrc = decodedCrc;
		this.encodedCrc = encodedCrc;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getChunkType() {
		return chunkType;
	}

	public void setChunkType(byte[] chunkType) {
		this.chunkType = chunkType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getDecodedCrc() {
		return decodedCrc;
	}

	public void setDecodedCrc(long decodedCrc) {
		this.decodedCrc = decodedCrc;
	}

	public int getEncodedCrc() {
		return encodedCrc;
	}

	public void setEncodedCrc(int encodedCrc) {
		this.encodedCrc = encodedCrc;
	}

	@Override
	public String toString() {
		StringBuilder data = new StringBuilder();
		for(int i = 0; i < getLength(); i++)
			data.append(this.getData()[i] + " ");

		return "Length: " + getLength() + " ChunkType: " + new String(getChunkType()) + " Data: " + data + " Decoded CRC: " + getDecodedCrc() + " Encoded CRC: " + getEncodedCrc();
	}

}
