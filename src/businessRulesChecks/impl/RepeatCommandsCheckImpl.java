package businessRulesChecks.impl;

import java.util.List;

import businessRulesChecks.CheckElement;
import businessRulesChecks.RepeatCommandsCheck;

import commands.Commands;

import concrete.Concrete;

public class RepeatCommandsCheckImpl implements RepeatCommandsCheck<Concrete>,CheckElement<Concrete> {

	@Override
	public boolean validate(Concrete concrete) {
		
		if (hasRepeatedCommands(concrete)){
            return false;
        } else {
            return true;
        }
	}

	@Override
	public int findInvalidItemIndexOrReturnCollectionSizeIfValid(
			Concrete concrete) {
		if (!validate(concrete)){
            List<Commands> listOfCommands = concrete.getCommandsList();

            for (Commands command : listOfCommands) {
                int counter = 0;
                for (Commands item : listOfCommands) {
                    if (command == item)
                        counter++;
                }
                if (counter > 1) return listOfCommands.lastIndexOf(command);
            }
        }

        return concrete.getCommandsList().size();
	}

	@Override
	public boolean hasRepeatedCommands(Concrete concrete) {
		List<Commands> listOfCommands = concrete.getCommandsList();

        for (Commands command : listOfCommands) {
            int counter = 0;
            for (Commands item : listOfCommands) {
                if (command == item)
                    counter++;
            }
            if (counter > 1) return true;
        }

        return false;
	}

}
