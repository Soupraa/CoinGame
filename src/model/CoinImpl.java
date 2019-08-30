package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {

	private int number;
	private CoinFace face;
	private double random = Math.random();

	public CoinImpl(int number) {
		this.number = number;
		if (random >= 0.5) {
			face = CoinFace.HEADS;
		}
		if (random < 0.5) {
			face = CoinFace.TAILS;
		}
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public CoinFace getFace() {
		return this.face;
	}

	@Override
	public void flip() {
		if (this.face.equals(CoinFace.HEADS)) {
			this.face = CoinFace.TAILS;
		} else if (this.face.equals(CoinFace.TAILS)) {
			this.face = CoinFace.HEADS;
		}
	}

	@Override
	public boolean equals(Coin coin) {
		if (number == coin.getNumber() && face == coin.getFace()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Coin " + number + ": " + face;

	}

	@Override
	public int hashCode() {
		if (equals(getFace())) {
			return getFace().hashCode();
		} else {
			return 0;
		}
	}

}
