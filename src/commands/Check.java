package commands;
import java.util.List;



import businessRulesChecks.*;

public interface Check<T> {
	 void setValidationRules(List<CheckElement<T>> validationElements);
	 boolean validate(T domain);
	 int findInvalidIndexValueIfItExists(T domain);
}
