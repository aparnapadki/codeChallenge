package convertInput;

import commands.Commands;

import concrete.Concrete;

/**
 * This class converts the numeric  input commands to the description of the corresponding numeric command
 * 
 * **/
public class ConvertNumericCmdToDesc {
	
	public void convertNumericCmdToDesc(String[] values, Concrete concrete) {
    	
        for (String action : values) {
        	concrete.addCommands(Commands.getAction(Integer.valueOf(action.trim())));
        }
        
    }
}
