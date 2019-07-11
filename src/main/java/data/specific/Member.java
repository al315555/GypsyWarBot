package data.specific;

import data.generic.RandomData;

public class Member extends RandomData {

    private static int COUNT_ID = 1 ;

    private int kills = 0;

    public Member(final String label){
        super(COUNT_ID++, label);
    }

    public void newKill(){kills++;}

    @Override
    public String dataType() {
        return Member.class.getName();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if(!(this instanceof Member))
            return false;
        return this.distinctCode == ((Member)obj).distinctCode;

    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
