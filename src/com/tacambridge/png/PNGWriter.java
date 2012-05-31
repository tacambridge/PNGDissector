package com.tacambridge.png;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;

import com.tacambridge.png.chunkType.Chunk;
import com.tacambridge.png.chunkType.Chunks;
import com.tacambridge.png.chunkType.critical.IHDR;

/**
 * 
 * @author terrianne
 *
 */
public class PNGWriter {

	private final long signature = 0x89504e470d0a1a0aL;
	private DataOutputStream output;

	public PNGWriter(String file) throws IOException {
		output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

		PNGHeader h = writeHeader();
		if (!h.isValid())
			throw new IOException("PNG signature not found!");

	}

	private PNGHeader writeHeader() throws IOException {
		output.writeLong(signature);
		PNGHeader header = new PNGHeader(signature);
		return header;		
	}

	public void writeAllChunks(List<PNGChunk> chunks) throws Exception {
		for(PNGChunk chunk : chunks) {
			writeData(chunk);
		}
		close();
	}

	public void writeData(PNGChunk chunk) throws Exception {

		int length = chunk.getLength();
		output.writeInt(length);
		if (length < 0)
			throw new IOException("Sorry, that file is too long.");
		byte[] chunkType = chunk.getChunkType();
		output.write(chunkType);
		byte[] data = chunk.getData();
		if(data.length != length)
			throw new Exception("Size of data is not equal to predertermined length");
		output.write(data);
		int crc = (int)calculateCRC(chunkType, data);
		output.writeInt(crc);

	}

	protected void close() throws IOException {
		output.close();
	}

	@Override
	protected void finalize() {
		try {
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static long calculateCRC(byte[] chunkType, byte[] data) {
		CRC32 crc32 = new CRC32();
		crc32.update(chunkType);
		crc32.update(data);
		long calculatedCRC = crc32.getValue();
		return calculatedCRC;
	}

	public static void main(String[] args) {
		try {
			PNGWriter w = new PNGWriter("files/test3_copy.png");
			PNGReader r = new PNGReader("files/test3.png");
			Chunks chunks = r.readAllChunks();
			List<PNGChunk> encodedChunks = chunks.getEncodedChunks();
			/*PNGChunk encodedChunk = null;
			for(PNGChunk c : encodedChunks) {
				if("IHDR".equals(new String(c.getChunkType()))) {
					IHDR decodedChunk = (IHDR) chunks.decode(c);
					System.out.println("old width = " + decodedChunk.getWidth());
					decodedChunk.setColorType(0);
					decodedChunk.setBitDepth(4);
					encodedChunk = chunks.encode(decodedChunk);
					System.out.println("new width = " + decodedChunk.getWidth());
					System.out.println("new " + decodedChunk);
				}
			}
			encodedChunks.remove(0);
			encodedChunks.add(0, encodedChunk);
			for(PNGChunk c : encodedChunks) {
				if("IHDR".equals(new String(c.getChunkType()))) {
					IHDR decodedChunk = (IHDR) chunks.decode(c);
					c = chunks.encode(decodedChunk);
					System.out.println("check width = " + decodedChunk.getWidth());
					System.out.println("check " + decodedChunk);
				}
			}*/
			w.writeAllChunks(encodedChunks);

			/*
			List<Chunk> decodedChunks = chunks.getDecodedChunks();
			for(Chunk c : decodedChunks) {
				System.out.println(c + "\n===================================");
			}
			chunks.encodeAllChunks();
			List<PNGChunk> encodedChunks = r.readAllChunks().getEncodedChunks();

			w.writeAllChunks(encodedChunks);
			PNGReader r2 = new PNGReader("files/test3_copy.png");
			List<PNGChunk> chunks3 = r2.readAllChunks().getEncodedChunks();
			for(PNGChunk c : chunks3) {
				System.out.println(c + "\n===================================");
			}
			 */
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


}
