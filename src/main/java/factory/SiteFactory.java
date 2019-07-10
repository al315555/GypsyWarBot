package factory;

import data.specific.Member;
import data.specific.Site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SiteFactory implements FactoryData<Site> {

    private static ArrayList<Site> sites = loadSites();

    @Override
    public Site takeOneRandomly() {
        final int positionInList = (int) (Math.random() * sites.size());
        return sites.get(positionInList);
    }

    private static ArrayList<Site> loadSites() {
        final ArrayList<Site> l_members = new ArrayList<>();
        final URL url = SiteFactory.class.getClassLoader().getResource("sites.txt");
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
                final Site member = new Site(name);
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
