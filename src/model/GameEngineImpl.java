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
		for (int i = 0; i < callback.size(); i++) {
			callback.get(i).spinnerResult(coinPair, this);
		}
	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
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
		playerCollection.put(player.getPlayerId(), player);

	}

	@Override
	public Player getPlayer(String id) {
		Collection<Player> players = playerCollection.values();
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player tempPlayer = it.next();
			tempPlayer = playerCollection.get(id);
			return tempPlayer;
			}
		return null;
	}
	@Override
	public boolean removePlayer(Player player) {
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
		return playerCollection.values();
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		player.setBetType(betType);
		return player.setBet(bet);

	}
}
