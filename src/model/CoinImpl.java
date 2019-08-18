package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {
	private int coin1;
	private int coin2;
	
	public CoinImpl(int coin1, int coin2) {
		this.coin1 = coin1;
		this.coin2 = coin2;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CoinFace getFace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flip() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Coin coin) {
		// TODO Auto-generated method stub
		return false;
	}

}
