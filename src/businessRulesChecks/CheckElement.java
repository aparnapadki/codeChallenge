package businessRulesChecks;

public interface CheckElement<T> {
	boolean validate(T concrete);
    int findInvalidItemIndexOrReturnCollectionSizeIfValid(T concrete);
}
