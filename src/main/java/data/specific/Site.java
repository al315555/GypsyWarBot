package data.specific;

import data.generic.RandomData;

public class Site extends RandomData {

    private static int COUNT_ID = 1 ;

    public Site(final String label){
        super(COUNT_ID++, label);
    }


    @Override
    public String dataType() {
        return Site.class.getName();
    }
}
