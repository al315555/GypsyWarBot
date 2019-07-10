package factory;

import data.generic.RandomData;
import factory.FactoryData;

public class RandomFactoryData<T extends RandomData> implements FactoryData {
    @Override
    public T takeOneRandomly() {
        return null;
    }
}
