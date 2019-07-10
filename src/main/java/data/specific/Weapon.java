package data.specific;

import data.generic.RandomData;

public class Weapon extends RandomData {

    private static int COUNT_ID = 1 ;

    public Weapon(final String label){
        super(COUNT_ID++, label);
    }

    @Override
    public String dataType() {
        return Weapon.class.getName();
    }
}
