package com.tacambridge.png.chunkType.critical;

import com.tacambridge.misc.Convert;
import com.tacambridge.png.chunkType.Chunk;

/**
 * 
 * @author terrianne
 *
 */
public class IHDR extends Chunk {

	/**
	 * Width and height give the image dimensions in pixels. 
	 * They are 4-byte integers.
	 */
	private int width; //4 bytes
	/**
	 * Width and height give the image dimensions in pixels. 
	 * They are 4-byte integers.
	 */
	private int height; //4 bytes
	/**
	 * Bit depth is a single-byte integer giving the number of bits per sample 
	 * or per palette index (not per pixel)
	 */
	private int bitDepth; //1 byte
	/**
	 * Color type is a single-byte integer that describes the interpretation of the image data. 
	 */
	private int colorType; //1 byte
	/**
	 * Compression method is a single-byte integer that indicates the method 
	 * used to compress the image data. At present, only compression method 0 
	 * (deflate/inflate compression with a sliding window of at most 32768 bytes) is defined. 
	 */
	private int compressionMethod; //1 byte
	/**
	 * Filter method is a single-byte integer that indicates the preprocessing method applied 
	 * to the image data before compression. At present, only filter method 0 (adaptive 
	 * filtering with five basic filter types) is defined. 
	 */
	private int filterMethod; //1 byte
	/**
	 * Interlace method is a single-byte integer that indicates the transmission order of the 
	 * image data. Two values are currently defined: 0 (no interlace) or 1 (Adam7 interlace). 
	 */
	private int interlaceMethod; //1 byte

	/**
	 *    
   Color    Allowed    Interpretation
   Type    Bit Depths

   0       1,2,4,8,16  Each pixel is a grayscale sample.

   2       8,16        Each pixel is an R,G,B triple.

   3       1,2,4,8     Each pixel is a palette index;
                       a PLTE chunk must appear.

   4       8,16        Each pixel is a grayscale sample,
                       followed by an alpha sample.

   6       8,16        Each pixel is an R,G,B triple,
                       followed by an alpha sample.
	 * @param width
	 * @param height
	 * @param bitDepth
	 * @param colorType
	 * @param compressionMethod
	 * @param filterMethod
	 * @param interlaceMethod
	 * @throws Exception 
	 */
	public IHDR(String chunkType, int width, int height, int bitDepth, int colorType,
			int compressionMethod, int filterMethod, int interlaceMethod) throws Exception {
		super(chunkType);
		setWidth(width);
		setHeight(height);
		setColorType(colorType);
		setBitDepth(bitDepth);
		setCompressionMethod(compressionMethod);
		setFilterMethod(filterMethod);
		setInterlaceMethod(interlaceMethod);
	}
	
	/**
	 * 
	 * @param data
	 * @throws Exception 
	 */
	public IHDR(String chunkType, byte[] data) throws Exception {
		super(chunkType);
		int width = Convert.getUnsignedInt(0, data);
		int height = Convert.getUnsignedInt(4, data);
		int bitDepth = Convert.getUnsignedByte(8, data);
		int colorType = Convert.getUnsignedByte(9, data);
		int compressionMethod = Convert.getUnsignedByte(10, data);
		int filterMethod = Convert.getUnsignedByte(11, data);
		int interlaceMethod = Convert.getUnsignedByte(12, data);
		
		setWidth(width);
		setHeight(height);
		setColorType(colorType);
		setBitDepth(bitDepth);
		setCompressionMethod(compressionMethod);
		setFilterMethod(filterMethod);
		setInterlaceMethod(interlaceMethod);
	}

	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Zero is an invalid value. The maximum for each is 231-1 in order to accommodate 
	 * languages that have difficulty with unsigned 4-byte values.
	 * @param width
	 * @throws Exception 
	 */
	public void setWidth(int width) throws Exception {
		if(width == 0)
			throw new Exception("Invalid width: " + width);
		this.width = width;
	}

	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Zero is an invalid value. The maximum for each is 231-1 in order to accommodate 
	 * languages that have difficulty with unsigned 4-byte values.
	 * @param height
	 * @throws Exception 
	 */
	public void setHeight(int height) throws Exception {
		if(height == 0)
			throw new Exception("Invalid height: " + height);
		this.height = height;
	}

	/**
	 * 
	 * @return
	 */
	public int getBitDepth() {
		return bitDepth;
	}

	/**
	 * Valid values are 1, 2, 4, 8, and 16, although not all values are allowed 
	 * for all color types.
	 * 
   Color    Allowed    Interpretation
   Type    Bit Depths

   0       1,2,4,8,16  Each pixel is a grayscale sample.

   2       8,16        Each pixel is an R,G,B triple.

   3       1,2,4,8     Each pixel is a palette index;
                       a PLTE chunk must appear.

   4       8,16        Each pixel is a grayscale sample,
                       followed by an alpha sample.

   6       8,16        Each pixel is an R,G,B triple,
                       followed by an alpha sample.
	 * @param bitDepth
	 * @throws Exception 
	 */
	public void setBitDepth(int bitDepth) throws Exception {
		boolean isValid = false;

		if(colorType == 0 && (bitDepth == 1 || bitDepth == 2 || bitDepth == 4 || bitDepth == 8 || bitDepth == 16))
			isValid = true;
		if(colorType == 2 && (bitDepth == 8 || bitDepth == 16))
			isValid = true;
		if(colorType == 3 && (bitDepth == 1 || bitDepth == 2 || bitDepth == 4 || bitDepth == 8))
			isValid = true;
		if(colorType == 4 && (bitDepth == 8 || bitDepth == 16))
			isValid = true;
		if(colorType == 6 && (bitDepth == 8 || bitDepth == 16))
			isValid = true;

		if(!isValid)
			throw new Exception("Invalid bitDepth: " + bitDepth + "(colorType: " + colorType + ")");

		this.bitDepth = bitDepth;
	}

	/**
	 * 
	 * @return
	 */
	public int getColorType() {
		return colorType;
	}

	/**
	 * Color type codes represent sums of the following values: 
	 * 1 (palette used), 2 (color used), and 4 (alpha channel used). 
	 * Valid values are 0, 2, 3, 4, and 6.
	 * @param colorType
	 * @throws Exception 
	 */
	public void setColorType(int colorType) throws Exception {
		if(!(colorType == 0 || colorType == 2 || colorType == 3 || colorType == 4 || colorType == 6))
			throw new Exception("Invalid colorType: " + colorType);

		this.colorType = colorType;
	}

	/**
	 * 
	 * @return
	 */
	public int getCompressionMethod() {
		return compressionMethod;
	}

	/**
	 * Valid values is 0
	 * @param compressionMethod
	 * @throws Exception 
	 */
	public void setCompressionMethod(int compressionMethod) throws Exception {
		if(compressionMethod != 0)
			throw new Exception("Invalid compressionMethod: " + compressionMethod);

		this.compressionMethod = compressionMethod;
	}

	/**
	 * 
	 * @return
	 */
	public int getFilterMethod() {
		return filterMethod;
	}

	/**
	 * Valid value is 0
	 * @param filterMethod
	 * @throws Exception 
	 */
	public void setFilterMethod(int filterMethod) throws Exception {
		if(filterMethod != 0)
			throw new Exception("Invalid filterMethod: " + filterMethod);

		this.filterMethod = filterMethod;
	}

	/**
	 * 
	 * @return
	 */
	public int getInterlaceMethod() {
		return interlaceMethod;
	}

	/**
	 * Valid values are 0 and 1
	 * @param interlaceMethod
	 * @throws Exception 
	 */
	public void setInterlaceMethod(int interlaceMethod) throws Exception {
		if(!(interlaceMethod == 0 || interlaceMethod == 1))
			throw new Exception("Invalid interlaceMethod: " + interlaceMethod);

		this.interlaceMethod = interlaceMethod;
	}

	@Override
	public String toString() {
		return "IDHR [width=" + width + ", height=" + height + ", bitDepth="
				+ bitDepth + ", colorType=" + colorType
				+ ", compressionMethod=" + compressionMethod
				+ ", filterMethod=" + filterMethod + ", interlaceMethod="
				+ interlaceMethod + "]";
	}

	@Override
	public byte[] toByteArray() {
		byte[] data = new byte[13];
		byte[] width = Convert.intToByteArray(this.width);
		byte[] height = Convert.intToByteArray(this.height);
		for(int i = 0; i < 4; i++) {
			data[i] = width[i];
			data[i + 4] = height[i];
		}
		data[8] = (byte) this.bitDepth;
		data[9] = (byte) this.colorType;
		data[10] = (byte) this.compressionMethod;
		data[11] = (byte) this.filterMethod;
		data[12] = (byte) this.interlaceMethod;
		
		return data;
	}

}
