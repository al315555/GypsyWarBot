package factory;

import data.specific.Member;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class MemberFactory implements FactoryData<Member> {
    private static ArrayList<Member> deadMembers = new ArrayList<>();
    private static ArrayList<Member> members = loadMembers();
    @Override
    public Member takeOneRandomly() {
        if( members.size() > 0) {
            final int positionInList = (int) (Math.random() * members.size());
            return members.get(positionInList);
        }else{
            return null;
        }
    }

    public boolean isThereAWinner(){
        return members.size() < 2;
    }
    public void takeKiller(final Member killer){
        killer.newKill();
    }

    public void takeDead(final Member dead){
        deadMembers.add(dead);
        members.removeAll(deadMembers);
    }

    private static ArrayList<Member> loadMembers() {
        final ArrayList<Member> l_members = new ArrayList<>();
        final URL url = MemberFactory.class.getClassLoader().getResource("members.txt");
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
                final Member member = new Member(name);
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
