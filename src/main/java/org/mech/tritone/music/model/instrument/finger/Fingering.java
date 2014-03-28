package org.mech.tritone.music.model.instrument.finger;

public class Fingering {

	private int positionDistance;

	private int fingerIndex;
	
	private int startingString;

	public Fingering(int positionDistance, int fingerIndex, int startingString) {
		super();
		this.positionDistance = positionDistance;
		this.fingerIndex = fingerIndex;
		this.startingString = startingString;
	}

	public int getFingerIndex(int position, int string) {
		int ret;

		switch (fingerIndex) {
		case 1:
			ret = getFingerIndexFromIndex(position,string);
			break;
		case 2:
			ret = getFingerIndexFromMiddle(position,string);
			break;
		case 4:
			ret = getFingerIndexFromLittle(position,string);
			break;
		default:
			ret = -1;
			break;
		}

		return ret;
	}

	private int getFingerIndexFromLittle(int position, int string) {
		int ret = -1;

		final int diff = positionDistance - position;
		if ((diff == 0 && string == startingString )|| (diff >= -1 && diff <= 0 && string != startingString)) {
			ret = 4;
		} else if (diff == 1) {
			ret = 3;
		} else if (diff == 2) {
			ret = 2;
		} else if (diff >= 2 && diff <= 4) {
			ret = 1;
		}

		return ret;
	}

	private int getFingerIndexFromMiddle(int fPosition, int string) {
		int ret = -1;

		final int diff = fPosition - positionDistance;
		if (diff == 0) {
			ret = 2;
		} else if (diff < 0 && diff >= -2) {
			ret = 1;
		} else if (diff == 1) {
			ret = 3;
		} else if (diff > 1 && diff < 3) {
			ret = 4;
		}

		return ret;
	}

	private int getFingerIndexFromIndex(int position, int string) {
		int ret = -1;

		final int diff = position - positionDistance;
		if (diff == 0) {
			ret = 1;
		} else if (diff == 1) {
			ret = 2;
		} else if (diff == 2) {
			ret = 3;
		} else if (diff >= 2 && diff <= 4) {
			ret = 4;
		}

		return ret;

	}

}
