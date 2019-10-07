package model;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

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
			CoinPairImpl coinPair = new CoinPairImpl();
			while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
				coinPair.getCoin1().flip();
				coinPair.getCoin2().flip();
				gameEngineCallback.playerCoinUpdate(player, coinPair.getCoin1(), this);
				gameEngineCallback.playerCoinUpdate(player, coinPair.getCoin2(), this);
				delayIncrement1++;
				delayIncrement2++;
				Thread.sleep(initialDelay1);
				Thread.sleep(initialDelay2);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				initialDelay2 = (delayIncrement2 + initialDelay2);
			}
			player.setResult(coinPair);
			gameEngineCallback.playerResult(player, coinPair, this);
		} catch (InterruptedException e) {

		}
	}

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		gameEngineCallback = callback.get(0);
		try {
			CoinPairImpl coinPair = new CoinPairImpl();
			while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
				coinPair.getCoin1().flip();
				coinPair.getCoin2().flip();
				gameEngineCallback.spinnerCoinUpdate(coinPair.getCoin1(), this);
				gameEngineCallback.spinnerCoinUpdate(coinPair.getCoin2(), this);
				delayIncrement1++;
				delayIncrement2++;
				Thread.sleep(initialDelay1);
				Thread.sleep(initialDelay2);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				initialDelay2 = (delayIncrement2 + initialDelay2);
			}		
			applyBetResults(coinPair);
			gameEngineCallback.spinnerResult(coinPair, this);
		} catch (InterruptedException e) {

		}
	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		for (Player j: collection) {
			j.getBetType().applyWinLoss(j, spinnerResult);
		}
	}

	@Override
	public void addPlayer(Player player) {
		collection.add(player);

	}

	@Override
	public Player getPlayer(String id) {
		for (Player i: collection) {
			if(i.getPlayerId()==id) {
				return i;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		if (collection.remove(player)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callback.add(gameEngineCallback);
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (gameEngineCallback != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(collection);
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		player.setBetType(betType);
		if (player.setBet(bet) == true && bet > 0) {
			return true;
		} else {
			player.resetBet();
			return false;
		}

	}
}
