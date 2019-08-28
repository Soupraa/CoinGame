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
      logger.setLevel(Level.INFO);
   }

   public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
	  //logger.log(Level.INFO, "Intermediate data to log .. String.format() is good here!");
      // TODO: complete this method to log intermediate results 
    logger.log(Level.INFO, String.format("%s coin %s flipped to %s", player.getPlayerName(), coin.getNumber(),coin.getFace().name()));
   }

   public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
   {
      // final results logged at Level.INFO
      //logger.log(Level.INFO, "Result data to log .. String.format() is good here!");
      // TODO: complete this method to log results
	   logger.log(Level.INFO, String.format("%s, Final Result=%s, %s",player.getPlayerName(),coinPair.getCoin1().toString(), coinPair.getCoin2().toString()));

    
   }

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, String.format("Spinner coin %s flipped to %s", coin.getNumber(), coin.getFace().name()));

	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
		System.out.println(engine.getAllPlayers());
		logger.log(Level.INFO, String.format("Final player Results %s\n Player: id=", engine.getAllPlayers()));		

	}

	// TODO: implement rest of interface
}
