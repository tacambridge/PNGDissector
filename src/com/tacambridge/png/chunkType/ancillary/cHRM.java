package com.tacambridge.png.chunkType.ancillary;

import com.tacambridge.misc.Convert;
import com.tacambridge.png.chunkType.Chunk;

public class cHRM extends Chunk {

	private int whitePointX;// 4 bytes
	private int whitePointY;// 4 bytes
	private int redX;// 4 bytes
	private int redY;// 4 bytes
	private int greenX;// 4 bytes
	private int greenY;// 4 bytes
	private int blueX;// 4 bytes
	private int blueY;// 4 bytes

	public cHRM(String chunkType, byte[] data) {
		super(chunkType);
		
		whitePointX = Convert.getUnsignedInt(0, data);
		whitePointY = Convert.getUnsignedInt(4, data);
		redX = Convert.getUnsignedInt(8, data);
		redY = Convert.getUnsignedInt(12, data);
		greenX = Convert.getUnsignedInt(16, data);
		greenY = Convert.getUnsignedInt(20, data);
		blueX = Convert.getUnsignedInt(24, data);
		blueY = Convert.getUnsignedInt(28, data);	

		setWhitePointX(whitePointX);
		setWhitePointY(whitePointY);
		setRedX(redX);
		setRedY(redY);
		setGreenX(greenX);
		setGreenY(greenY);
		setBlueX(blueX);
		setBlueY(blueY);
	}

	public int getWhitePointX() {
		return whitePointX;
	}

	public void setWhitePointX(int whitePointX) {
		this.whitePointX = whitePointX;
	}

	public int getWhitePointY() {
		return whitePointY;
	}

	public void setWhitePointY(int whitePointY) {
		this.whitePointY = whitePointY;
	}

	public int getRedX() {
		return redX;
	}

	public void setRedX(int redX) {
		this.redX = redX;
	}

	public int getRedY() {
		return redY;
	}

	public void setRedY(int redY) {
		this.redY = redY;
	}

	public int getGreenX() {
		return greenX;
	}

	public void setGreenX(int greenX) {
		this.greenX = greenX;
	}

	public int getGreenY() {
		return greenY;
	}

	public void setGreenY(int greenY) {
		this.greenY = greenY;
	}

	public int getBlueX() {
		return blueX;
	}

	public void setBlueX(int blueX) {
		this.blueX = blueX;
	}

	public int getBlueY() {
		return blueY;
	}

	public void setBlueY(int blueY) {
		this.blueY = blueY;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "cHRM [whitePointX=" + whitePointX + ", whitePointY="
				+ whitePointY + ", redX=" + redX + ", redY=" + redY
				+ ", greenX=" + greenX + ", greenY=" + greenY + ", blueX="
				+ blueX + ", blueY=" + blueY + "]";
	}

}
