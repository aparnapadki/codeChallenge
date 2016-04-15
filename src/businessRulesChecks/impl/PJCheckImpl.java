package businessRulesChecks.impl;

import java.util.List;

import businessRulesChecks.CheckElement;
import businessRulesChecks.PJCheck;
import commands.Commands;
import concrete.Concrete;

public class PJCheckImpl implements PJCheck<Concrete>,CheckElement<Concrete>{

	@Override
	public boolean validate(Concrete concrete) {
		return arePJsOff(concrete);
	}

	@Override
	public int findInvalidItemIndexOrReturnCollectionSizeIfValid(
			Concrete concrete) {
		if (!validate(concrete)) {
            List<Commands> morningActions = concrete.getCommandsList();
            if (morningActions.get(0) != Commands.TAKE_OFF_PAJAMAS)
                return morningActions.indexOf(Commands.TAKE_OFF_PAJAMAS);
        }

        return concrete.getCommandsList().size();
	}

	@Override
	public boolean arePJsOff(Concrete concrete) {
		 List<Commands> morningActions = concrete.getCommandsList();
	        if (morningActions.get(0) != Commands.TAKE_OFF_PAJAMAS) return false;
	        return true;
	}

}
