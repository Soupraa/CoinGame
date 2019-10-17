package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
//	private Collection<Player> collection;
	private List<GameEngineCallback> callback;
	private HashMap<String, Player> playerCollection;
	private int callbacks;

	public GameEngineImpl() {
		callback = new ArrayList<GameEngineCallback>();
		playerCollection = new HashMap<>();
		callbacks = 0;
	}

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		CoinPairImpl coinPair = new CoinPairImpl();
		coinPair.getCoin1().flip();
		coinPair.getCoin2().flip();
		System.out.println("hi");
		try {
			while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
				if (initialDelay1 < finalDelay1) {
					coinPair.getCoin1().flip();
					playerCoinUpdate(player, coinPair, coinPair.getCoin1().getNumber());
					delayIncrement1++;
					Thread.sleep(initialDelay1);
					initialDelay1 = (delayIncrement1 + initialDelay1);

				}
				if (initialDelay2 < finalDelay2) {
					coinPair.getCoin2().flip();
					playerCoinUpdate(player, coinPair, coinPair.getCoin2().getNumber());
					delayIncrement2++;
					Thread.sleep(initialDelay2);
					initialDelay2 = (delayIncrement2 + initialDelay2);

				}
			}
			player.setResult(coinPair);
			playerResult(player, coinPair);
		} catch (InterruptedException e) {

		}
	}

	private void playerCoinUpdate(Player player, CoinPair coinPair, int number) {
		for (int i = 0; i < callback.size(); i++) {
			if (number == 1) {
				callback.get(i).playerCoinUpdate(player, coinPair.getCoin1(), this);
			} else if (number == 2) {
				callback.get(i).playerCoinUpdate(player, coinPair.getCoin2(), this);
			}
		}
	}

	private void playerResult(Player player, CoinPair coinPair) {
		for (int i = 0; i < callback.size(); i++) {
			callback.get(i).playerResult(player, coinPair, this);
		}
	}

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		CoinPairImpl coinPair = new CoinPairImpl();
		/* Initialize coin on random face */
		coinPair.getCoin1().flip();
		coinPair.getCoin2().flip();
		for (GameEngineCallback cb : callback) {
			try {
				while (initialDelay1 < finalDelay1 && initialDelay2 < finalDelay2) {
					if (initialDelay1 < finalDelay1) {
						coinPair.getCoin1().flip();
						spinnerCoinUpdate(coinPair, coinPair.getCoin1().getNumber());
						delayIncrement1++;
						Thread.sleep(initialDelay1);
						initialDelay1 = (delayIncrement1 + initialDelay1);

					}
					if (initialDelay2 < finalDelay2) {
						coinPair.getCoin2().flip();
						spinnerCoinUpdate(coinPair, coinPair.getCoin2().getNumber());
						delayIncrement2++;
						Thread.sleep(initialDelay2);
						initialDelay2 = (delayIncrement2 + initialDelay2);
					}
				}
				applyBetResults(coinPair);
				spinnerResult(coinPair);
			} catch (InterruptedException e) {

			}
		}
	}

	private void spinnerCoinUpdate(CoinPair coinPair, int number) {

		for (int i = 0; i < callback.size(); i++) {
			if (number == 1) {
				callback.get(i).spinnerCoinUpdate(coinPair.getCoin1(), this);
			} else if (number == 2) {
				callback.get(i).spinnerCoinUpdate(coinPair.getCoin2(), this);
			}
		}
	}

	private void spinnerResult(CoinPair coinPair) {
		while (callback.iterator().hasNext()) {
			callback.iterator().next().spinnerResult(coinPair, this);
		}
		for (int i = 0; i < callback.size(); i++) {
			callback.get(i).spinnerResult(coinPair, this);
		}
	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
//		for (Player j: collection) {
//			j.getBetType().applyWinLoss(j, spinnerResult);
//		}
		Collection<Player> players = playerCollection.values();
		Iterator<Player> it = players.iterator();

		while (it.hasNext()) {
			Player player = it.next();
			BetType playerBetType = player.getBetType();
			playerBetType.applyWinLoss(player, spinnerResult);
			playerCollection.put(player.getPlayerId(), player);
		}
	}

	@Override
	public void addPlayer(Player player) {
//		collection.add(player);
		playerCollection.put(player.getPlayerId(), player);

	}

	@Override
	public Player getPlayer(String id) {
		Player tempPlayer = null;
		for(int i =0; i<this.playerCollection.size(); i++) {
			if(this.playerCollection.get(i).getPlayerId().equals(id)) {
				tempPlayer = playerCollection.get(i);
			}
		}
		return tempPlayer;
	}

	@Override
	public boolean removePlayer(Player player) {
//		if (collection.remove(player)) {
//			return true;
//		} else {
//			return false;
//		}
		return playerCollection.remove(player.getPlayerId(), player);

	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (gameEngineCallback != null) {
			callback.add(callbacks, gameEngineCallback);
			callbacks++;
		}

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if (callback.remove(gameEngineCallback)) {
			callbacks--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// return Collections.unmodifiableCollection(collection);
		return playerCollection.values();
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		Collection<Player> players = playerCollection.values();
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			player = it.next();
			if (player.setBet(bet) == true && bet > 0) {
				player.setBetType(betType);
				return true;
			} else {
				player.resetBet();
				return false;
			}
		}
		return player.setBet(bet);
//
	}
}
