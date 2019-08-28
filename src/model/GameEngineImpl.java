package model;

import java.util.Collection;

import java.util.ArrayList;
import java.util.List;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;



public class GameEngineImpl implements GameEngine {
	
	private Player id;
	private CoinPair coinPair;
	private GameEngine engine;
	private GameEngineCallback gameEngineCallback;
	private Collection<Player> collection;
	private List<GameEngineCallback> callback;
	
	public GameEngineImpl() {
		collection = new ArrayList<Player>();
		callback = new ArrayList<GameEngineCallback>();		
	}
	
	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		gameEngineCallback = callback.get(0);
		try {
			coinPair = new CoinPairImpl();
			while (initialDelay1 < finalDelay1) {
				coinPair.getCoin1().flip();
				gameEngineCallback.playerCoinUpdate(player, coinPair.getCoin1(), engine);
				delayIncrement1++;
				Thread.sleep(initialDelay1);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				if (initialDelay2 < finalDelay2) {
					coinPair.getCoin2().flip();
					gameEngineCallback.playerCoinUpdate(player, coinPair.getCoin2(), engine);
					delayIncrement2++;
					Thread.sleep(initialDelay2);
					initialDelay2 = (delayIncrement2 + initialDelay2);
				}
			}
			gameEngineCallback.playerResult(player, coinPair, engine);
		} catch (InterruptedException e) {

		}
	}
	

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		gameEngineCallback = callback.get(0);
		try {
			coinPair = new CoinPairImpl();
			while (initialDelay1 < finalDelay1) {
				coinPair.getCoin1().flip();
				gameEngineCallback.spinnerCoinUpdate(coinPair.getCoin1(), engine);
				delayIncrement1++;
				Thread.sleep(initialDelay1);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				if (initialDelay2 < finalDelay2) {
					coinPair.getCoin2().flip();
					gameEngineCallback.spinnerCoinUpdate(coinPair.getCoin2(), engine);
					delayIncrement2++;
					Thread.sleep(initialDelay2);
					initialDelay2 = (delayIncrement2 + initialDelay2);
				}
			}
			gameEngineCallback.spinnerResult(coinPair, engine);
		} catch (InterruptedException e) {

		}
	}
	



	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		gameEngineCallback.spinnerResult(spinnerResult, engine);

	}

	@Override
	public void addPlayer(Player player) {	
		collection.add(player);
			
	}

	@Override
	public Player getPlayer(String id) {
		if(id == this.id.getPlayerId()) {
			return this.id;			
		}
		else {
			return null;
		}
	}
	

	@Override
	public boolean removePlayer(Player player) {
		if(collection.remove(player)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callback.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (gameEngineCallback !=null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {	
		collection.addAll(getAllPlayers());
		return collection;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		player.getPlayerName();
		player.setBet(bet);
		if (player.setBet(bet) == true && bet > 0) {
			return true;	
		}
		else {
			player.resetBet();
			return false;
		}
		
	}

}

