package concrete;

import java.util.ArrayList;
import java.util.List;

import commands.Commands;
import commands.Temperature;
import commands.Check;


/**
 * This is the concrete class of the project. It holds methods to prepare the final result.
 * **/
public class Concrete {
	private final Check<Concrete> concreteValidation;
    private final Temperature temperature;
    private final List<Commands> listOfCommands = new ArrayList<>();

    private static final String FAIL = "fail";

    public Concrete(Temperature temperature, Check<Concrete> concreteValidation) {
        this.temperature = temperature;
        this.concreteValidation = concreteValidation;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public List<Commands> getCommandsList() {
        return listOfCommands;
    }

    //method to add converted input Command enums to an arraylist
    public void addCommands(Commands command) {
    	listOfCommands.add(command);
    }

    //Makes the final
    public String whatIshouldWear() {
        int endIndex = concreteValidation.findInvalidIndexValueIfItExists(this);
        return decideWhatIshouldWear(listOfCommands, endIndex);
    }

    //prepares the final result
    private String decideWhatIshouldWear(List<Commands> actions, int endIndex) {
        if (endIndex < 0) return FAIL;

        List<Commands> values = actions.subList(0, endIndex);
        StringBuilder results = new StringBuilder();

        String prefix = "";
        for (Commands command : values) {
        	results.append(prefix);
        	prefix = ", ";
            results.append(command.getDescription(temperature));
        }
        

        if (temperature == Temperature.COLD) {
            if (values.size() < 8) {
            	results.append(prefix);
                results.append(FAIL);
            }
        } else if (temperature == Temperature.HOT) {
        	if (values.size() < 6) {
        		results.append(prefix);
            	results.append(FAIL);
            }
        }

        return results.toString();
    }

}
