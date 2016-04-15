package factory;

import concrete.Concrete;


public interface ConcreteFactory {
	
	Concrete createObject(String temperature, String commands);
}
