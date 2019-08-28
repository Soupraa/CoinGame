package model;


import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {
	
	private int number;
	private CoinFace face;
	private double random = Math.random();
	
	
	public CoinImpl(int number) {
		this.number = number;
		if(random >= 0.5) {
			this.face = CoinFace.HEADS;
		}
		if(random < 0.5) {
			this.face = CoinFace.TAILS;			
		}	
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	@Override
	public CoinFace getFace() {
		return this.face;
	}
	@Override
	public void flip() {
		if (this.face !=CoinFace.HEADS) {
			face = CoinFace.HEADS;
		if (this.face !=CoinFace.TAILS) {
			face = CoinFace.TAILS;
			}
		}
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
	@Override
	public String toString() {
		return "Coin " + number + ": "+ face;
		
	}

}
