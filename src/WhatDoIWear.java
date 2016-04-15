import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import concrete.Concrete;
import convertInput.ConvertNumericCmdToDesc;
import convertInput.ConvertTemperature;
import factory.CheckFactory;
import factory.ConcreteFactory;
import factory.impl.CheckFactoryImpl;
import factory.impl.ConcreteFactoryImpl;



/**
 * In this project, Factory Design pattern is used. I intended to obtain encapsulation and hide intricate 
 * implementation logic from the client. Hence the decision to implement Factory Design.
 * 
 * The use of interfaces makes the project flexible and maintainable if more functionality needs to be added.
 * 
 * ***/

/**
 * Class WhatDoIWear:
 * This class is the client or main class. This class accepts input and returns the result.
 * */
public class WhatDoIWear {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter temperature type(HOT/COLD) and a comma separated list of commands (e.g HOT 8,6,4,2,1,7)");
		String temperature = "";
		String commands = "";
		try {
			//Accept input
			String[] input = br.readLine().split(" ");
			if (input == null ||input.length == 0) throw new RuntimeException("You provided no inputs. Please run again and provide.");
			temperature = input[0];
			commands = input[1];
			
		} catch (Exception e) {
			System.out.println("You provided no inputs. Please run again and provide.");
			
		}
		//Use Factory object to get the results.
		 ConcreteFactory concreteFactory = createConcreteFactory();
	     Concrete concrete = concreteFactory.createObject(temperature,commands);
		
	        String results = concrete.whatIshouldWear();
	        System.out.println(results);
		
		
	}
	
	//Factory method taking the responsibility of calling appropriate implementation classes
	private static ConcreteFactory createConcreteFactory() {
        CheckFactory<Concrete> personValidationFactory = new CheckFactoryImpl();
        ConvertNumericCmdToDesc personMapper = new ConvertNumericCmdToDesc();
        ConvertTemperature temperatureProcessor = new ConvertTemperature();

        ConcreteFactory concreteFactory = new ConcreteFactoryImpl(personValidationFactory, personMapper, temperatureProcessor);
        return concreteFactory;
    }

}
