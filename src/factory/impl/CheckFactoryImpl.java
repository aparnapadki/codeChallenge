package factory.impl;

import java.util.ArrayList;
import java.util.List;

import businessRulesChecks.CheckElement;
import businessRulesChecks.impl.*;
import commands.Check;
import commands.CommandsValidation;
import concrete.Concrete;
import factory.CheckFactory;

/**
 * This is a validation class where an arrayList holds the objects of all the business rules check classes.
 * **/
public class CheckFactoryImpl implements CheckFactory<Concrete> {

	@Override
	public Check<Concrete> createValidations() {
		Check<Concrete> commandsCheck;

        commandsCheck = new CommandsValidation();

        List<CheckElement<Concrete>> checkElements = new ArrayList<>();
        checkElements.add(new PJCheckImpl());
        checkElements.add(new RepeatCommandsCheckImpl());
        checkElements.add(new TemperatureClothesCheckImpl());
        checkElements.add(new OrderOfCommandsCheckImpl());
        checkElements.add(new AllClothingOnCheckImpl());

        commandsCheck.setValidationRules(checkElements);
        
        return commandsCheck;
	}

}
