package businessRulesChecks.impl;

import java.util.List;

import businessRulesChecks.CheckElement;
import businessRulesChecks.TemperatureClothesCheck;

import commands.Commands;
import commands.Temperature;

import concrete.Concrete;

public class TemperatureClothesCheckImpl implements TemperatureClothesCheck<Concrete>,CheckElement<Concrete>{

	@Override
	public boolean validate(Concrete concrete) {
		
		return isClothesForTemperatureCorrect(concrete);
	}

	@Override
	public int findInvalidItemIndexOrReturnCollectionSizeIfValid(Concrete concrete) {
		 if (!validate(concrete)){
	            List<Commands> listOfCommands = concrete.getCommandsList();
	            int socksIndex = listOfCommands.contains(Commands.PUT_ON_SOCKS) ? listOfCommands.indexOf(Commands.PUT_ON_SOCKS) : Integer.MAX_VALUE;
	            int jacketIndex = listOfCommands.contains(Commands.PUT_ON_JACKET) ? listOfCommands.indexOf(Commands.PUT_ON_JACKET) : Integer.MAX_VALUE;

	            return socksIndex > jacketIndex ? jacketIndex : socksIndex;
	        }

	        return concrete.getCommandsList().size();
	    }
	

	@Override
	public boolean isClothesForTemperatureCorrect(Concrete concrete) {
		List<Commands> listOfCommands = concrete.getCommandsList();
        if (concrete.getTemperature() == Temperature.HOT) {
            if (listOfCommands.contains(Commands.PUT_ON_SOCKS) ||
            		listOfCommands.contains(Commands.PUT_ON_JACKET))
                return false;
        }

        return true;
	}

}
