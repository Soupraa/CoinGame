package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair{
	
	private Coin coin1;
	private Coin coin2;
	
	public CoinPairImpl() {
		coin1 = new CoinImpl(1);
		coin2 = new CoinImpl(2);
	}

	@Override
	public Coin getCoin1() {
		return coin1;
	}

	@Override
	public Coin getCoin2() {
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
