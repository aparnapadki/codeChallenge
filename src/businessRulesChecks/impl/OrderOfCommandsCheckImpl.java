package businessRulesChecks.impl;

import java.util.List;

import businessRulesChecks.CheckElement;
import businessRulesChecks.OrderOfCommandsCheck;
import commands.Commands;
import concrete.Concrete;

public class OrderOfCommandsCheckImpl implements OrderOfCommandsCheck<Concrete>,CheckElement<Concrete>{

	@Override
	public boolean validate(Concrete concrete) {
		
		return areCommandsInCorrectOrder(concrete);
	}

	@Override
	public int findInvalidItemIndexOrReturnCollectionSizeIfValid(Concrete concrete) {
		if (!validate(concrete)) {
            List<Commands> morningActions = concrete.getCommandsList();

            if (footwearAndSocksOrderCheck(morningActions))
                return morningActions.indexOf(Commands.PUT_ON_FOOTWEAR);
            if (shirtAndHeadwearOrderCheck(morningActions))
                return morningActions.indexOf(Commands.PUT_ON_HEAD_WEAR);
            if (shirtAndJacketOrderCheck(morningActions))
                return morningActions.indexOf(Commands.PUT_ON_JACKET);
            if (pantsAndFootwearOrderCheck(morningActions))
                return morningActions.indexOf(Commands.PUT_ON_FOOTWEAR);
        }

        return concrete.getCommandsList().size();
	}

	@Override
	public boolean areCommandsInCorrectOrder(Concrete concrete) {
		
		List<Commands> listOfCommands = concrete.getCommandsList();

        if (footwearAndSocksOrderCheck(listOfCommands)) return false;
        if (shirtAndHeadwearOrderCheck(listOfCommands)) return false;
        if (shirtAndJacketOrderCheck(listOfCommands)) return false;
        if (pantsAndFootwearOrderCheck(listOfCommands)) return false;

        return true;
	}
	
	private boolean footwearAndSocksOrderCheck(List<Commands> morningActions) {
        if (morningActions.contains(Commands.PUT_ON_FOOTWEAR) && morningActions.contains(Commands.PUT_ON_SOCKS)) {
            if (morningActions.indexOf(Commands.PUT_ON_SOCKS) > morningActions.indexOf(Commands.PUT_ON_FOOTWEAR))
                return true;
        }
        return false;
    }

    private boolean shirtAndHeadwearOrderCheck(List<Commands> morningActions) {
        if (morningActions.contains(Commands.PUT_ON_SHIRT) && morningActions.contains(Commands.PUT_ON_HEAD_WEAR)) {
            if (morningActions.indexOf(Commands.PUT_ON_SHIRT) > morningActions.indexOf(Commands.PUT_ON_HEAD_WEAR))
                return true;

        }
        return false;
    }

    private boolean shirtAndJacketOrderCheck(List<Commands> morningActions) {
        if (morningActions.contains(Commands.PUT_ON_SHIRT) && morningActions.contains(Commands.PUT_ON_JACKET)) {
            if (morningActions.indexOf(Commands.PUT_ON_SHIRT) > morningActions.indexOf(Commands.PUT_ON_JACKET))
                return true;
        }
        return false;
    }

    private boolean pantsAndFootwearOrderCheck(List<Commands> morningActions) {
        if (morningActions.contains(Commands.PUT_ON_PANTS) && morningActions.contains(Commands.PUT_ON_FOOTWEAR)) {
            if (morningActions.indexOf(Commands.PUT_ON_PANTS) > morningActions.indexOf(Commands.PUT_ON_FOOTWEAR))
                return true;
        }
        return false;
    }

}
