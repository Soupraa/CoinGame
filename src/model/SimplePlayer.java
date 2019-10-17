package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private static final BetType NO_BET = null;
	private String playerID;
	private String playerName;
	private int initialPoints;
	private int playerbet;
	private int newPoints;
	private BetType betType;
	private CoinPair coinResult;

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
		return (newPoints+initialPoints);
	}

	@Override
	public void setPoints(int points) {
		this.newPoints = points;

	}

	@Override
	public String getPlayerId() {
		return playerID;
		
	}

	@Override
	public boolean setBet(int bet) {
		this.playerbet = bet;
		if (bet > 0 && bet < initialPoints) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return playerbet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;

	}

	@Override
	public BetType getBetType() {
		return betType;
	}

	@Override
	public void resetBet() {
		setBet(0);
		setBetType(NO_BET);
		
	}

	@Override
	public CoinPair getResult() {
		return coinResult;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		this.coinResult = coinPair;
		
	}
	@Override
	public String toString() {
		return "Player id=" + playerID + ", Name="+playerName +", Bet=" + playerbet + ", BetType="+ getBetType() + ", Points="+ getPoints() + ""
				+ ", RESULTS..."+ getResult().toString();	
	}

}

