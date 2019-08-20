package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair{
	
	private Coin coin1;
	private Coin coin2;
	
	public CoinPairImpl(Coin coin1, Coin coin2) {
		this.coin1 = coin1;
		this.coin2 = coin2;
	
	}

	@Override
	public Coin getCoin1() {
		// TODO Auto-generated method stub
		return coin1;
	}

	@Override
	public Coin getCoin2() {
		// TODO Auto-generated method stub
		return coin2;
	}

	@Override
	public boolean equals(CoinPair coinPair) {
		if (coin1 == coinPair.getCoin1() && coin2 == coinPair.getCoin2()) {
			return true;
		}
		else {
			return false;
		}
	}

}
