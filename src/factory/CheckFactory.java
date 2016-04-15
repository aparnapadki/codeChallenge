package factory;

import commands.Check;



public interface CheckFactory<T> {
	Check<T> createValidations();
}
