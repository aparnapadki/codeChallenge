package businessRulesChecks.impl;

import java.util.List;

import businessRulesChecks.AllClothingOnCheck;
import businessRulesChecks.CheckElement;

import commands.Commands;
import commands.Temperature;

import concrete.Concrete;

public class AllClothingOnCheckImpl implements AllClothingOnCheck<Concrete>,CheckElement<Concrete> {

	@Override
	public boolean areAllClothingsOn(Concrete concrete) {
		List<Commands> commands = concrete.getCommandsList();
        if (!stillDressing(commands) &&
        		areAllClothingOn(concrete)) {
            return true;
        }

        return false;
	}

	private boolean stillDressing(List<Commands> commands) {

        if (commands.size() > 0 &&
        		commands.contains(Commands.LEAVE_HOUSE) &&
        		commands.get(commands.size() - 1) != Commands.LEAVE_HOUSE) return true;

        return false;
    }

    private boolean areAllClothingOn(Concrete concrete) {
        if (concrete.getTemperature() == Temperature.COLD) {
            if (concrete.getCommandsList().size() != 8)
                return false;
        } else {
            if (concrete.getCommandsList().size() != 6)
                return false;
        }
        return true;
    }
    
	@Override
	public boolean validate(Concrete concrete) {
		
		return areAllClothingsOn(concrete);
	}

	@Override
	public int findInvalidItemIndexOrReturnCollectionSizeIfValid(Concrete concrete) {
		if (!validate(concrete)) {
            List<Commands> listOfCommands = concrete.getCommandsList();

            if (listOfCommands.size() > 0 &&
            		listOfCommands.contains(Commands.LEAVE_HOUSE) &&
            		listOfCommands.get(listOfCommands.size() - 1) != Commands.LEAVE_HOUSE)
                return listOfCommands.indexOf(Commands.LEAVE_HOUSE);

            if (listOfCommands.contains(Commands.LEAVE_HOUSE) &&
                    !areAllClothingOn(concrete))
                return listOfCommands.indexOf(Commands.LEAVE_HOUSE);

        }

        return concrete.getCommandsList().size();
	}

}
