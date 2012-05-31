package com.tacambridge.png;

/**
 * 
 * @author terrianne
 *
 */
public class PNGHeader {

	public long signature;

	protected PNGHeader() {
		super();
	}

	public PNGHeader(long signature) {
		super();
		this.signature = signature;
	}

	public boolean isValid() {
		if (signature != 0x89504e470d0a1a0aL)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Signature: " + signature;
	}
}
