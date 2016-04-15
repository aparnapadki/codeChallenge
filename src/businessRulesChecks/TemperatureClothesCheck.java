package businessRulesChecks;

public interface TemperatureClothesCheck<T> {
	boolean isClothesForTemperatureCorrect(T concrete);
}
