package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());
   
   
   public GameEngineCallbackImpl()
   {
      // NOTE need to also set the console to FINE in %JRE_HOME%\lib\logging.properties
      logger.setLevel(Level.FINE);
   }

   public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
      // TODO: complete this method to log intermediate results 
    logger.log(Level.FINE, String.format("%s coin %s flipped to %s", player.getPlayerName(), coin.getNumber(),coin.getFace().name()));
    System.out.println(String.format("%s coin %s flipped to %s", player.getPlayerName(), coin.getNumber(),coin.getFace().name()));
   }

   public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
   {
      // final results logged at Level.INFO
      // TODO: complete this method to log results
	   logger.log(Level.INFO, String.format("%s, Final Result=%s",player.getPlayerName(),coinPair.toString()));
	   System.out.println(String.format("%s, Final Result=%s",player.getPlayerName(),coinPair.toString()));
    
   }

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		// TODO Auto-generated method stub
		logger.log(Level.FINE, String.format("Spinner coin %s flipped to %s", coin.getNumber(), coin.getFace().name()));
		System.out.println(String.format("Spinner coin %s flipped to %s", coin.getNumber(), coin.getFace().name()));
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		logger.log(Level.INFO, String.format("Spinner Result: %s", coinPair.toString()));	
		System.out.println(String.format("Spinner Result: %s", coinPair.toString()));
		for(Player i: engine.getAllPlayers()) {
			i.toString();
			logger.log(Level.INFO, String.format("Final player Results:", i.toString()));
			System.out.println(i.toString());
		}
	}
	

}
