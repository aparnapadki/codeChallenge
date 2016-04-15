package factory.impl;


import commands.Check;
import commands.Temperature;

import concrete.Concrete;
import convertInput.ConvertNumericCmdToDesc;
import convertInput.ConvertTemperature;
import factory.CheckFactory;
import factory.ConcreteFactory;

/**
 * This class handles the implementation of the concrete class that readies the input for further processing
 * **/
public class ConcreteFactoryImpl implements ConcreteFactory {
	 private CheckFactory<Concrete> checkFactory;
	 private ConvertNumericCmdToDesc convertCommand;
	 private ConvertTemperature convertTemp;
	 
	 public ConcreteFactoryImpl(CheckFactory<Concrete> checkFactory,ConvertNumericCmdToDesc convertCommand,ConvertTemperature convertTemp) {
		 this.checkFactory = checkFactory;
		 this.convertCommand = convertCommand;
		 this.convertTemp = convertTemp;
}
	@Override
	public Concrete createObject(String temperature, String commands) {
		if (temperature.equals("") || commands.equals("")) throw new RuntimeException("Please provide valid input");
		Temperature temp = convertTemp.mapTemperature(temperature);
        Check<Concrete> checkedCommands = checkFactory.createValidations();
        Concrete concrete = new Concrete(temp, checkedCommands);
        String[] actions = commands.split("\\s*,\\s*");
        convertCommand.convertNumericCmdToDesc(actions, concrete);

        return concrete;
    }
}
