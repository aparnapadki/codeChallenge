package convertInput;

import commands.Temperature;

/**
 * This class converts the string input of temperature (HOT/COLD) to the Temperature enum
 * */
public class ConvertTemperature {
	public Temperature mapTemperature(String value) {
    	Temperature temperature;
        String passedInTemp = value;
        if (passedInTemp.equalsIgnoreCase("HOT")) {
            temperature = Temperature.HOT;
        } else if (passedInTemp.equalsIgnoreCase("COLD")) {
            temperature = Temperature.COLD;
        }else {
            throw new RuntimeException("Temperature you passed: " + value + " is not recognized; please use HOT or COLD only");
        }

        return temperature;
    }
}
