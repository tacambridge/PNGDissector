package com.tacambridge.png.chunkType.ancillary;

import com.tacambridge.misc.Convert;
import com.tacambridge.png.chunkType.Chunk;

/**
 * Perceptual intent is for images preferring good adaptation to the output device gamut 
 * at the expense of colorimetric accuracy, like photographs.
 * 
 * Relative colorimetric intent is for images requiring color appearance matching 
 * (relative to the output device white point), like logos.
 * 
 * Saturation intent is for images preferring preservation of saturation at the expense 
 * of hue and lightness, like charts and graphs.
 * 
 * Absolute colorimetric intent is for images requiring preservation of absolute colorimetry, 
 * like proofs (previews of images destined for a different output device).
 * 
 * @author terrianne
 *
 */
public class sRGB extends Chunk {

	private int renderingIntent; // 1 byte

	public sRGB(String chunkType, byte[] data) {
		super(chunkType);
		this.renderingIntent = Convert.getUnsignedByte(0, data);
		setRenderingIntent(renderingIntent);
	}

	public int getRenderingIntent() {
		return renderingIntent;
	}

	/**
	 *    0: Perceptual
	 *    1: Relative colorimetric
	 *    2: Saturation
	 *    3: Absolute colorimetric
	 *    
	 * @param renderingIntent
	 */
	public void setRenderingIntent(int renderingIntent) {
		if(renderingIntent >= 0 && renderingIntent <=3)
			this.renderingIntent = renderingIntent;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}



}
