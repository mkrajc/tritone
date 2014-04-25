package org.mech.tritone.music.model.instrument.finger;

public class Fingering {

	private final int positionDistance;

	private final FingerType finger;
	
	private final int startingString;
	
	public Fingering(final FingeredPitch fPitch) {
		this(fPitch.getPitch().getPosition(), fPitch.getFinger(), fPitch.getPitch().getStringIndex());
	}

	public Fingering(final int positionDistance, final FingerType finger, final int startingString) {
		super();
		this.positionDistance = positionDistance;
		this.finger = finger;
		this.startingString = startingString;
	}
	
	private int leftOffset(){
		return 2 + finger.ordinal();
	}
	
	public int from(){
		return Math.max(0, positionDistance - leftOffset());
	}
	
	public int to(){
		return positionDistance + rightOffset();
	}
	
	public int rightOffset(){
		return 6 - finger.ordinal();
	}
	

	public FingerType getFinger(final int position, final int string) {
		FingerType ret = null;

		switch (finger) {
		case INDEX:
			ret = getFingerIndexFromIndex(position);
			break;
		case MIDDLE:
			ret = getFingerIndexFromMiddle(position);
			break;
		case PINKY:
			ret = getFingerIndexFromLittle(position,string);
			break;
		default:
			break;
		}

		return ret;
	}

	private FingerType getFingerIndexFromLittle(final int position, final int string) {
		FingerType ret = null;

		final int diff = positionDistance - position;
		if ((diff == 0 && string == startingString )|| (diff >= -1 && diff <= 0 && string != startingString)) {
			ret = FingerType.PINKY;
		} else if (diff == 1) {
			ret = FingerType.RING;
		} else if (diff == 2) {
			ret = FingerType.MIDDLE;
		} else if (diff >= 2 && diff <= 4) {
			ret = FingerType.INDEX;
		}

		return ret;
	}

	private FingerType getFingerIndexFromMiddle(final int fPosition) {
		FingerType ret = null;

		final int diff = fPosition - positionDistance;
		if (diff == 0) {
			ret = FingerType.MIDDLE;
		} else if (diff < 0 && diff >= -2) {
			ret = FingerType.INDEX;
		} else if (diff == 1) {
			ret = FingerType.RING;
		} else if (diff > 1 && diff < 3) {
			ret = FingerType.PINKY;
		}

		return ret;
	}

	private FingerType getFingerIndexFromIndex(final int position) {
		FingerType ret = null ;

		final int diff = position - positionDistance;
		if (diff == 0) {
			ret = FingerType.INDEX;
		} else if (diff == 1) {
			ret = FingerType.MIDDLE;
		} else if (diff == 2) {
			ret = FingerType.RING;
		} else if (diff >= 2 && diff <= 4) {
			ret = FingerType.PINKY;
		}

		return ret;

	}

}
