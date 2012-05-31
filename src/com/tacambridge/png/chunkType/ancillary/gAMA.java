package com.tacambridge.png.chunkType.ancillary;

import com.tacambridge.misc.Convert;
import com.tacambridge.png.chunkType.Chunk;

/**
 * These chunks relate the image samples to the desired display intensity.
 * The gAMA chunk specifies the relationship between the image samples and 
 * the desired display output intensity as a power function:
 * 
 *    sample = light_out ^ gamma
 *    
 *    Here sample and light_out are normalized to the range 0.0 (minimum intensity) to 1.0 (maximum intensity). Therefore:
 *    
 *       sample = integer_sample / (2^bitdepth - 1)
 *       The gAMA chunk contains:
 *       
 *    Gamma: 4 bytes
 *      
 * @author terrianne
 *
 */
public class gAMA extends Chunk {

	/**
	 * The value is encoded as a 4-byte unsigned integer, representing gamma times 100000. 
	 * For example, a gamma of 1/2.2 would be stored as 45455.
	 */
	private int gamma; // 4 bytes
	
	public gAMA(String chunkType, byte[] data) {
		super(chunkType);
		int gamma = Convert.getUnsignedInt(0, data);
		setGamma(gamma);
		// TODO Auto-generated constructor stub
	}

	public int getGamma() {
		return gamma;
	}

	public void setGamma(int gamma) {
		this.gamma = gamma;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "gAMA [gamma=" + gamma + "]";
	}

}
