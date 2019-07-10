package factory;

import data.specific.Weapon;

public class WeaponFactory implements FactoryData<Weapon> {
    @Override
    public Weapon takeOneRandomly() {
        return null;
    }
}
