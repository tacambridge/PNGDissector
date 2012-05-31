package com.tacambridge.png.chunkType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tacambridge.png.PNGChunk;
import com.tacambridge.png.chunkType.ancillary.cHRM;
import com.tacambridge.png.chunkType.ancillary.gAMA;
import com.tacambridge.png.chunkType.ancillary.pHYs;
import com.tacambridge.png.chunkType.ancillary.sRGB;
import com.tacambridge.png.chunkType.critical.IEND;
import com.tacambridge.png.chunkType.critical.IHDR;

/**
 * Contains list of encoded and decoded chunks in a png file
 * @author terrianne
 *
 */
public class Chunks {

	/**
	 * String: chunkType
	 * Integer: id
	 */
	public static Map<String, Integer> chunkTypeMap = new HashMap<String, Integer>();
	private List<PNGChunk> encodedChunks = new LinkedList<PNGChunk>();
	private List<Chunk> decodedChunks = new LinkedList<Chunk>();

	static {
		int id = 0;
		chunkTypeMap.put("bKGD", id++); //0
		chunkTypeMap.put("cHRM", id++); //1
		chunkTypeMap.put("gAMA", id++); //2
		chunkTypeMap.put("hIST", id++); //3
		chunkTypeMap.put("iCCP", id++); //4
		chunkTypeMap.put("iTXt", id++); //5
		chunkTypeMap.put("pHYs", id++); //6
		chunkTypeMap.put("sBIT", id++); //7
		chunkTypeMap.put("sPLT", id++); //8
		chunkTypeMap.put("sRGB", id++); //9
		chunkTypeMap.put("tEXt", id++); //10
		chunkTypeMap.put("tIME", id++); //11
		chunkTypeMap.put("tRNS", id++); //12
		chunkTypeMap.put("zTXt", id++); //13
		chunkTypeMap.put("IDAT", id++); //14
		chunkTypeMap.put("IEND", id++); //15
		chunkTypeMap.put("IHDR", id++); //16
		chunkTypeMap.put("PLTE", id++); //17
	}

	public List<PNGChunk> getEncodedChunks() {
		return encodedChunks;
	}

	public List<Chunk> getDecodedChunks() {
		return decodedChunks;
	}

	public void addEncodedChunk(PNGChunk encodedChunk) {
		encodedChunks.add(encodedChunk);
	}

	public void decodeAllChunks() throws Exception {
		if(!decodedChunks.isEmpty())
			decodedChunks.clear();

		for(PNGChunk encodedChunk : encodedChunks) {
			Chunk decodedChunk = decode(encodedChunk);
			addDecodedChunk(decodedChunk);
		}
	}
	
	public void encodeAllChunks() {
		if(!encodedChunks.isEmpty())
			encodedChunks.clear();
		
		for(Chunk decodedChunk : decodedChunks) {
			PNGChunk encodedChunk = encode(decodedChunk);
			addEncodedChunk(encodedChunk);
		}
	}

	public void addDecodedChunk(Chunk decodedChunk) {
		decodedChunks.add(decodedChunk);
	}

	/**
	 * 
	 * @param encodedChunk
	 * @return
	 * @throws Exception
	 */
	public Chunk decode(PNGChunk encodedChunk) throws Exception {
		byte[] chunkType = encodedChunk.getChunkType();
		String chunkTypeString = new String(chunkType);
		Chunk decodedChunk = null;

		Integer id = chunkTypeMap.get(chunkTypeString);
		if(id != null) {
			switch(id) {
			//case 0: decodedChunk = new bKGD(chunkTypeString, encodedChunk.getData());
			//break;
			case 1: decodedChunk = new cHRM(chunkTypeString, encodedChunk.getData());
			break;
			case 2: decodedChunk = new gAMA(chunkTypeString, encodedChunk.getData());
			break;
			//case 3: decodedChunk = new hIST(chunkTypeString, encodedChunk.getData());
			//break;
			//case 4: decodedChunk = new iCCP(chunkTypeString, encodedChunk.getData());
			//break;
			//case 5: decodedChunk = new iTXt(chunkTypeString, encodedChunk.getData());
			//break;
			case 6: decodedChunk = new pHYs(chunkTypeString, encodedChunk.getData());
			break;
			//case 7: decodedChunk = new sBIT(chunkTypeString, encodedChunk.getData());
			//break;
			//case 8: decodedChunk = new sPLT(chunkTypeString, encodedChunk.getData());
			//break;
			case 9: decodedChunk = new sRGB(chunkTypeString, encodedChunk.getData());
			break;
			//case 10: decodedChunk = new tEXt(chunkTypeString, encodedChunk.getData());
			//break;
			//case 11: decodedChunk = new tIME(chunkTypeString, encodedChunk.getData());
			//break;
			//case 12: decodedChunk = new tRNS(chunkTypeString, encodedChunk.getData());
			//break;
			//case 13: decodedChunk = new zTXt(chunkTypeString, encodedChunk.getData());
			//break;
			//case 14: decodedChunk = new IDAT(chunkTypeString, encodedChunk.getData());
			//break;
			case 15: decodedChunk = new IEND(chunkTypeString, encodedChunk.getData());
			break;
			case 16: decodedChunk = new IHDR(chunkTypeString, encodedChunk.getData());
			break;
			//case 17: decodedChunk = new PLTE(chunkTypeString, encodedChunk.getData());
			//break;
			default:
				System.out.println("No decoder for " + chunkTypeString);
				break;
			} 
		}
		else 
			System.out.println(chunkTypeString + " not found");

		if(decodedChunk != null)
			System.out.println(decodedChunk);

		return decodedChunk;
	}
	
	/**
	 * 
	 * @param decodedChunk
	 * @return
	 */
	public PNGChunk encode(Chunk decodedChunk) {
		String chunkTypeString = decodedChunk.getChunkType();
		byte[] chunkType = chunkTypeString.getBytes();
		byte[] data = decodedChunk.toByteArray();
		int length = data.length;
		PNGChunk encodedChunk = new PNGChunk(length, chunkType, data, 0, 0);
		return encodedChunk;
	}
}
