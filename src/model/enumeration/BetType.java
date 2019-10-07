package model.enumeration;

import model.interfaces.CoinPair;
import model.interfaces.Player;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */

public enum BetType
{
      COIN1
      {
         @Override
         public void applyWinLoss(Player player, CoinPair spinnerResult)
         {     	
        	 if(player.getResult().getCoin1().equals(spinnerResult.getCoin1())) {
        		 addBetResult(player, 1);
        	 }
        	 else {
        		 addBetResult(player, -1);
        	 }
        }
      },
	COIN2
	{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
       	{
			 if(player.getResult().getCoin2().equals(spinnerResult.getCoin2())) {
        		 addBetResult(player, 1);
        	 }
        	 else {
        		 addBetResult(player, -1);
        	 }
       	}
			
	},
	BOTH{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
       	{
      	 	if(player.getResult().getCoin1().equals(spinnerResult.getCoin1()) && player.getResult().getCoin2().equals(spinnerResult.getCoin2())) {
      	 		addBetResult(player, 2);
      	 	}
      	 	else {
      	 		addBetResult(player, -1);
      	 	}
       	}
		
	},
	NO_BET{
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult)
       	{
			//no action.
       	}
	};
	public void addBetResult(Player player, int betValue)
	{
		if(player.getPoints() < 0) {
			player.setPoints(0);
		}
		else {
			player.setPoints(player.getPoints() + (player.getBet() * betValue));
		}
		
	}
      
      // TODO finish this class with other enum constants
   
      /**
       * This method is to be overridden for each bet type<br>
       * see assignment specification for betting odds and how win/loss is applied
       * 
       * @param player - the player to apply the win/loss points balance adjustment
       * @param spinnerResult - the CoinPair result of the spinner to compare to
       */
      public abstract void applyWinLoss(Player player, CoinPair spinnerResult);
}