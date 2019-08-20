package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {
	
	private int number;
	private CoinFace face;
	
	public CoinImpl(int number, CoinFace face) {
		this.number = number;
		this.face = face;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public CoinFace getFace() {
		// TODO Auto-generated method stub
		return face;
	}

	@Override
	public void flip() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Coin coin) {
		if (number == coin.getNumber() && face == coin.getFace()) {
			return true;
		}
		else {
			return false;
		}
	}

}
