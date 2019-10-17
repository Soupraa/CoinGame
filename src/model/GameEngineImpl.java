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
	private Collection<Player> collection;
	private List<GameEngineCallback> callback;

	public GameEngineImpl() {
		collection = new ArrayList<Player>();
		callback = new ArrayList<GameEngineCallback>();
	}

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		//gameEngineCallback = callback.get(0);
		CoinPairImpl coinPair = new CoinPairImpl();
		for(GameEngineCallback cb: callback) {
		try {
			while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
				if(initialDelay1 < finalDelay1) {
					delayIncrement1++;
					Thread.sleep(initialDelay1);
					initialDelay1 = (delayIncrement1 + initialDelay1);
					coinPair.getCoin1().flip();
					
				}
				coinPair.getCoin1().flip();
				coinPair.getCoin2().flip();
				cb.playerCoinUpdate(player, coinPair.getCoin1(), this);
				cb.playerCoinUpdate(player, coinPair.getCoin2(), this);
				delayIncrement1++;
				delayIncrement2++;
				Thread.sleep(initialDelay1);
				Thread.sleep(initialDelay2);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				initialDelay2 = (delayIncrement2 + initialDelay2);
			}
			player.setResult(coinPair);
			cb.playerResult(player, coinPair, this);
		} catch (InterruptedException e) {

		}
	}
	}

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		//	gameEngineCallback = callback.get(0);
		CoinPairImpl coinPair = new CoinPairImpl();
		for(GameEngineCallback cb: callback) {
		try {
			
			while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
				coinPair.getCoin1().flip();
				coinPair.getCoin2().flip();
				cb.spinnerCoinUpdate(coinPair.getCoin1(), this);
				cb.spinnerCoinUpdate(coinPair.getCoin2(), this);
				delayIncrement1++;
				delayIncrement2++;
				Thread.sleep(initialDelay1);
				Thread.sleep(initialDelay2);
				initialDelay1 = (delayIncrement1 + initialDelay1);
				initialDelay2 = (delayIncrement2 + initialDelay2);
			}		
			applyBetResults(coinPair);
			cb.spinnerResult(coinPair, this);
		} catch (InterruptedException e) {

		}
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
	//	System.out.println(player.getPlayerId()+player.getPlayerName() + player.getPoints());
	}

	@Override
	public Player getPlayer(String id) {
		for (Player i: collection) {
			if(i.getPlayerId() != null) {
				System.out.println(i.getPlayerId());
				return i;
			}
		}
		return null;
	}
//		for (Player i: collection) {
//			if(i.getPlayerId() != null) {
//				System.out.println(i.getPlayerId());
//				id = i.getPlayerId();
//				return id;
//				}
//			}					
//		}

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
			return callback.remove(gameEngineCallback);
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
		for (Player j : collection) {
			if (j.setBet(bet) == true && bet > 0) {
				j.setBetType(betType);
				return true;
			} else {
				j.resetBet();
				return false;
			}
		}
		return player.setBet(bet);

	}
}
