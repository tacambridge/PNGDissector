package com.tacambridge.png.chunkType.critical;

import com.tacambridge.png.chunkType.Chunk;

public class IEND extends Chunk {

	public IEND(String chunkType, byte[] data) {
		super(chunkType);
	}

	@Override
	public byte[] toByteArray() {
		return null;
	}

	@Override
	public String toString() {
		return "IEND []";
	}

	
}
