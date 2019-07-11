package factory;

import data.specific.Member;
import data.specific.Site;
import java.io.*;
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
        final InputStream is = MemberFactory.class.getClassLoader().getResourceAsStream("sites.txt");
        BufferedReader br = null;
		InputStreamReader isr = null;
        try{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
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
