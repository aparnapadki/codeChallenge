package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import businessRulesChecks.CheckElement;
import businessRulesChecks.impl.AllClothingOnCheckImpl;
import businessRulesChecks.impl.OrderOfCommandsCheckImpl;
import businessRulesChecks.impl.PJCheckImpl;
import businessRulesChecks.impl.RepeatCommandsCheckImpl;
import businessRulesChecks.impl.TemperatureClothesCheckImpl;

import commands.Check;
import commands.Commands;
import commands.CommandsValidation;
import commands.Temperature;

import concrete.Concrete;

public class TestCases {
	private List<CheckElement<Concrete>> checkedElements;
    private Check<Concrete> checkCommands;

   
    Check<Concrete> validation;

    @Before
    public void setBusinessRules() {
    	checkCommands = new CommandsValidation();

    	checkedElements = new ArrayList<>();
    	checkedElements.add(new PJCheckImpl());
    	checkedElements.add(new RepeatCommandsCheckImpl());
    	checkedElements.add(new TemperatureClothesCheckImpl());
    	checkedElements.add(new OrderOfCommandsCheckImpl());
    	checkedElements.add(new AllClothingOnCheckImpl());

    	checkCommands.setValidationRules(checkedElements);
    }

    @Test
    public void didNotTakePJsOff(){
    	Concrete concrete = new Concrete(Temperature.COLD, validation);

    	concrete.addCommands(Commands.PUT_ON_PANTS);
    	
        boolean isValid = checkCommands.validate(concrete);
        assertFalse(isValid);
    }

    @Test
    public void allClothingOnForCold(){
    	Concrete concrete = new Concrete(Temperature.COLD, validation);

    	concrete.addCommands(Commands.TAKE_OFF_PAJAMAS);
    	concrete.addCommands(Commands.PUT_ON_PANTS);
    	concrete.addCommands(Commands.PUT_ON_SOCKS);
    	concrete.addCommands(Commands.PUT_ON_SHIRT);
        concrete.addCommands(Commands.PUT_ON_HEAD_WEAR);
        concrete.addCommands(Commands.PUT_ON_JACKET);
        concrete.addCommands(Commands.PUT_ON_FOOTWEAR);
        concrete.addCommands(Commands.LEAVE_HOUSE);

        boolean isValid = checkCommands.validate(concrete);
        assertTrue(isValid);
    }

    @Test
    public void woreSocksOnHotDay() {
    	Concrete concrete = new Concrete(Temperature.HOT, validation);

    	concrete.addCommands(Commands.TAKE_OFF_PAJAMAS);
        concrete.addCommands(Commands.PUT_ON_PANTS);
        concrete.addCommands(Commands.PUT_ON_SOCKS);

        int fail = checkCommands.findInvalidIndexValueIfItExists(concrete);
        assertEquals(2, fail);
    }

    @Test
    public void repeatedCommands() {
    	Concrete concrete = new Concrete(Temperature.HOT, validation);

        concrete.addCommands(Commands.TAKE_OFF_PAJAMAS);
        concrete.addCommands(Commands.PUT_ON_PANTS);
        concrete.addCommands(Commands.PUT_ON_PANTS);
        
        int fail = checkCommands.findInvalidIndexValueIfItExists(concrete);
        assertEquals(2, fail);
    }
    
    @Test
    public void didNotWearAllClothingOnColdDay(){
    	Concrete concrete = new Concrete(Temperature.COLD, validation);

        concrete.addCommands(Commands.TAKE_OFF_PAJAMAS);
        concrete.addCommands(Commands.PUT_ON_PANTS);
        concrete.addCommands(Commands.PUT_ON_SOCKS);
        concrete.addCommands(Commands.PUT_ON_SHIRT);
        concrete.addCommands(Commands.PUT_ON_HEAD_WEAR);
        concrete.addCommands(Commands.PUT_ON_JACKET);
        concrete.addCommands(Commands.LEAVE_HOUSE);

        int index = checkCommands.findInvalidIndexValueIfItExists(concrete);
        assertEquals(6, index);
    }

    @Test
    public void allClothingWornOnHotDay(){
    	Concrete concrete = new Concrete(Temperature.HOT, validation);

        concrete.addCommands(Commands.TAKE_OFF_PAJAMAS);
        concrete.addCommands(Commands.PUT_ON_PANTS);
        concrete.addCommands(Commands.PUT_ON_SHIRT);
        concrete.addCommands(Commands.PUT_ON_HEAD_WEAR);
        concrete.addCommands(Commands.PUT_ON_FOOTWEAR);
        concrete.addCommands(Commands.LEAVE_HOUSE);

        boolean isValid = checkCommands.validate(concrete);
        assertTrue(isValid);
    }
}
