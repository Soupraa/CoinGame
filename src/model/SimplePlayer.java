package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private static final BetType NO_BET = null;
	private String playerID;
	private String playerName;
	private int initialPoints;
	private int bet;
	private BetType betType;
	private CoinPair coinPair;

	public SimplePlayer(String playerID, String playerName, int initialPoints) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName=playerName;

	}

	@Override
	public int getPoints() {
	
		return initialPoints;
	}

	@Override
	public void setPoints(int points) {
		points = initialPoints;

	}

	@Override
	public String getPlayerId() {
		return playerID;
		
	}

	@Override
	public boolean setBet(int bet) {
		if (bet > 0) {
			resetBet();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void setBetType(BetType betType) {
		betType = this.betType;

	}

	@Override
	public BetType getBetType() {
		return this.betType;
	}

	@Override
	public void resetBet() {
		setBet(0);
		setBetType(NO_BET);
		
	}

	@Override
	public CoinPair getResult() {
		return coinPair;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		this.coinPair=coinPair;

	}
	@Override
	public String toString() {
		return "Player id=" + playerID + ", Name="+playerName +", Bet="+ getBet() + ", BetType="+ getBetType() + ", Points"+ getPoints() + ""
				+ ", RESULTS.."+ getResult();	
	}

}

