package org.mech.tritone.music.model;

import java.util.List;

public class TonePattern {
	private Pattern pattern;
	private Tone root;

	public TonePattern(final Pattern pattern, final Tone root) {
		super();
		this.pattern = pattern;
		this.root = root;
	}

	public List<Tone> toTones() {
		return pattern.toTones(root);

	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(final Pattern pattern) {
		this.pattern = pattern;
	}

	public Tone getRoot() {
		return root;
	}

	public void setRoot(final Tone root) {
		this.root = root;
	}

}
