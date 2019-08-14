package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerID;
	private String playerName;
	private int initalPoints;
	private int points;
	private int bet;

	public SimplePlayer(String playerID, String playerName, int initalPoints) {
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPoints(int points) {
		this.points=points;

	}

	@Override
	public String getPlayerId() {
		return playerID;
		
	}

	@Override
	public boolean setBet(int bet) {
		if (bet > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBetType(BetType betType) {
		// TODO Auto-generated method stub

	}

	@Override
	public BetType getBetType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetBet() {
		// TODO Auto-generated method stub

	}

	@Override
	public CoinPair getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		// TODO Auto-generated method stub

	}

}

