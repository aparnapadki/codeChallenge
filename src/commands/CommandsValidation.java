package commands;

import java.util.List;

import businessRulesChecks.CheckElement;
import concrete.Concrete;

/**
 * This class validates each input command
 * **/
public class CommandsValidation implements Check<Concrete> {

	private List<CheckElement<Concrete>> checkElements;

    @Override
    public void setValidationRules(List<CheckElement<Concrete>> checkElements) {
        this.checkElements = checkElements;
    }

    @Override
    public boolean validate(Concrete concreteObject) {
        for (CheckElement<Concrete> validationElement : checkElements) {
            boolean valid = validationElement.validate(concreteObject);
            if (!valid){
                return false;
            }
        }

        return true;
    }

    //Method to validate input for each business rule check object
    @Override
    public int findInvalidIndexValueIfItExists(Concrete concreteObject) {
        for (CheckElement<Concrete> validationElement : checkElements) {
            boolean valid = validationElement.validate(concreteObject);
            if (!valid){
            	
                return validationElement.findInvalidItemIndexOrReturnCollectionSizeIfValid(concreteObject);
            }
        }

        return concreteObject.getCommandsList().size();
    }

}
