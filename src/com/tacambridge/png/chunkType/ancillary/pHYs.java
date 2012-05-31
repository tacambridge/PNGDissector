package com.tacambridge.png.chunkType.ancillary;

import com.tacambridge.misc.Convert;
import com.tacambridge.png.chunkType.Chunk;

/**
 * The pHYs chunk specifies the intended pixel size or aspect ratio for display of the image.
 * ppu = pixels per unit
 * @author terrianne
 *
 */
public class pHYs extends Chunk {

	private int ppuXAxis; // 4 byte
	private int ppuYAxis; // 4 byte
	private int unitSpecifier; // 1 byte

	public pHYs(String chunkType, byte[] data) {
		super(chunkType);
		ppuXAxis = Convert.getUnsignedInt(0, data);
		ppuYAxis = Convert.getUnsignedInt(0, data);
		unitSpecifier = Convert.getUnsignedByte(0, data);

		setPpuXAxis(ppuXAxis);
		setPpuYAxis(ppuYAxis);
		setUnitSpecifier(unitSpecifier);
	}

	public int getPpuXAxis() {
		return ppuXAxis;
	}

	public void setPpuXAxis(int ppuXAxis) {
		this.ppuXAxis = ppuXAxis;
	}

	public int getPpuYAxis() {
		return ppuYAxis;
	}

	public void setPpuYAxis(int ppuYAxis) {
		this.ppuYAxis = ppuYAxis;
	}

	public int getUnitSpecifier() {
		return unitSpecifier;
	}

	/**
	 * The following values are defined for the unit specifier:
	 * 
	 * 0: unit is unknown
	 * 1: unit is the meter
	 * 
	 * @param unitSpecifier
	 */
	public void setUnitSpecifier(int unitSpecifier) {
		this.unitSpecifier = unitSpecifier;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "pHYs [ppuXAxis=" + ppuXAxis + ", ppuYAxis=" + ppuYAxis
				+ ", unitSpecifier=" + unitSpecifier + "]";
	}

}
