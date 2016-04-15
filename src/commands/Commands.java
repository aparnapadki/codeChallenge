package commands;

import java.util.NoSuchElementException;

/**
 * Enum defining different types of commands
 * **/
public enum Commands {
	 	PUT_ON_FOOTWEAR("sandals", "boots"),
	    PUT_ON_SOCKS("fail", "socks"),
	    PUT_ON_JACKET("fail", "jacket"),
	    PUT_ON_HEAD_WEAR("sun visor", "hat"),
	    PUT_ON_SHIRT("t-shirt", "shirt"),
	    PUT_ON_PANTS("shorts", "pants"),
	    TAKE_OFF_PAJAMAS("Removing PJs", "Removing PJs"),
	    LEAVE_HOUSE("leaving house", "leaving house");

	    private final String coldDesc;
	    private final String hotDesc;

	    Commands(String hotDesc, String coldDesc) {
	        this.hotDesc = hotDesc;
	        this.coldDesc = coldDesc;
	    }

	    public String getHotDesc() {
	        return hotDesc;
	    }

	    public String getColdDesc() {
	        return coldDesc;
	    }

	    public String getDescription(Temperature temperature){
	        if (temperature == Temperature.COLD)
	            return getColdDesc();
	        else {
	            return getHotDesc();
	        }
	    }
	    
	    //convert numeric command to Command enum
	    public static Commands getAction(int number){
	       switch(number){
	           case 1 :
	               return PUT_ON_FOOTWEAR;
	           case 2 :
	               return PUT_ON_HEAD_WEAR;
	           case 3 :
	               return PUT_ON_SOCKS;
	           case 4 :
	               return PUT_ON_SHIRT;
	           case 5 :
	               return PUT_ON_JACKET;
	           case 6 :
	               return PUT_ON_PANTS;
	           case 7 :
	               return LEAVE_HOUSE;
	           case 8 :
	               return TAKE_OFF_PAJAMAS;
	           default :
	               throw new NoSuchElementException("You have provided an invalid argument. There is no such value as: " + number);
	       }
	    }
}
