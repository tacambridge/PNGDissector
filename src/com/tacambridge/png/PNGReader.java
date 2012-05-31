package com.tacambridge.png;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.CRC32;

import com.tacambridge.png.chunkType.Chunks;

/**
 * 
 * @author terrianne
 *
 */
public class PNGReader {

	private DataInputStream input;

	public PNGReader(String file) throws IOException {
		input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		PNGHeader h = readHeader();
		if (!h.isValid())
			throw new IOException("PNG signature not found!");
	}

	protected PNGHeader readHeader() throws IOException {
		long signature = input.readLong();
		PNGHeader header = new PNGHeader(signature);
		return header;		
	}

	public Chunks readAllChunks() throws Exception {
		Chunks chunks = new Chunks();
		while(input.available() > 0) {
			chunks.addEncodedChunk(readData());
		}
		chunks.decodeAllChunks();
		close();
		return chunks;
	}

	public PNGChunk readData() throws IOException {

		int length = input.readInt();
		if (length < 0)
			throw new IOException("Sorry, that file is too long. Lenght = " + length);
		byte[] chunkType = new byte[4];
		input.readFully(chunkType);
		byte[] data = new byte[length];
		input.readFully(data);
		int encodedCrc = input.readInt();
		long decodedCrc = encodedCrc & 0x00000000ffffffffL; // Make it
		// unsigned.
		if (verifyCRC(chunkType, data, decodedCrc) == false)
			throw new IOException("That file appears to be corrupted.");

		PNGChunk chunk = new PNGChunk(length, chunkType, data, decodedCrc, encodedCrc);

		return chunk;
	}

	protected void close() throws IOException {
		input.close();
	}

	@Override
	protected void finalize() {
		try {
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static boolean verifyCRC(byte[] chunkType, byte[] data, long crc) {
		CRC32 crc32 = new CRC32();
		crc32.update(chunkType);
		crc32.update(data);
		long calculated = crc32.getValue();
		return (calculated == crc);
	}

	public static void main(String[] args) {
		try {
			PNGReader r = new PNGReader("files/test4.png");
			List<PNGChunk> chunks = r.readAllChunks().getEncodedChunks();
			for(PNGChunk c : chunks)
				System.out.println(c + "\n===================================");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


}
