package businessRulesChecks;

public interface OrderOfCommandsCheck<T> {
	 boolean areCommandsInCorrectOrder(T concrete);
}
