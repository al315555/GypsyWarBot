package factory;

import data.specific.Site;
import data.specific.Weapon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WeaponFactory implements FactoryData<Weapon> {
    private static ArrayList<Weapon> weapons = loadSites();

    @Override
    public Weapon takeOneRandomly() {
        final int positionInList = (int) (Math.random() * weapons.size());
        return weapons.get(positionInList);
    }

    private static ArrayList<Weapon> loadSites() {
        final ArrayList<Weapon> l_members = new ArrayList<>();
        final URL url = SiteFactory.class.getClassLoader().getResource("weapons.txt");
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try{
            file = new File (String.valueOf(url.getPath()));
            fr = new FileReader (file);
            br = new BufferedReader(fr);
            StringBuilder nameBuilder = new StringBuilder();
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
