package businessRulesChecks;

public interface RepeatCommandsCheck<T> {
	boolean hasRepeatedCommands(T concrete);
}
