package factory;

import data.specific.Site;
import data.specific.Weapon;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class WeaponFactory implements FactoryData<Weapon> {
    private static ArrayList<Weapon> weapons = loadWeapons();

    @Override
    public Weapon takeOneRandomly() {
        final int positionInList = (int) (Math.random() * weapons.size());
        return weapons.get(positionInList);
    }

    private static ArrayList<Weapon> loadWeapons() {
        final ArrayList<Weapon> l_members = new ArrayList<>();
        final InputStream is = MemberFactory.class.getClassLoader().getResourceAsStream("weapons.txt");
        BufferedReader br = null;
		InputStreamReader isr = null;
        try{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
            String linea = br.readLine();
            while(linea != null){
                final String name = linea;
                final Weapon member = new Weapon(name);
                l_members.add(member);
                linea = br.readLine();
            }
            return l_members;

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }
}
