package com.tacambridge.png.chunkType;

/**
 * Parent class for all chunk types
 * @author terrianne
 *
 */
public abstract class Chunk {

	private String chunkType;

	public Chunk(String chunkType) {
		super();
		this.chunkType = chunkType;
	}

	public String getChunkType() {
		return chunkType;
	}

	public void setChunkType(String chunkType) {
		this.chunkType = chunkType;
	}
	
	public abstract byte[] toByteArray();


}
