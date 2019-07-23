package factory;

import data.specific.Member;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class MemberFactory implements FactoryData<Member> {
    private static ArrayList<Member> deadMembers = new ArrayList<>();
    private static ArrayList<Member> members = new ArrayList<>();
    private static ArrayList<Member> allMembers = new ArrayList<>();

    @Override
    public Member takeOneRandomly() {
        if( members.size() > 0) {
            final int positionInList = (int) (Math.random() * members.size());
            return members.get(positionInList);
        }else{
            return null;
        }
    }

	public static ArrayList<Member> getDeadMembers(){
		return (ArrayList<Member> )deadMembers.clone();
	}
	
	public static ArrayList<Member> getAliveMembers(){
		return (ArrayList<Member> )members.clone();
	}

    public static void setDeadMembers(ArrayList<Member> l_mem ){
        deadMembers = (ArrayList<Member> )l_mem.clone();
    }

    public static void setAliveMembers( ArrayList<Member> l_mem){
        members = (ArrayList<Member> )l_mem.clone();
    }
	
	public String getHtmlMembersList(){
        String res = "<div class=\"grid-container\">";
        for(Member member: allMembers){
            boolean redColor = deadMembers.contains(member);
            res += String.format("<div class=\"grid-item\"><strong style=\"padding: 1px 1px 1px 1px ;color:%s\"> %s </strong></div>", redColor ? "red;text-decoration:line-through;" : "black;"  , member.toString());
        }
		return res + "</div>";
	}
	
    public boolean isThereAWinner(){
        return members != null && members.size() < 2;
    }
    public void takeKiller(final Member killer){
        killer.newKill();
    }

    public void takeDead(final Member dead){
        deadMembers.add(dead);
        members.removeAll(deadMembers);
    }

    static {
        final ArrayList<Member> l_members = new ArrayList<>();
        final InputStream is = MemberFactory.class.getClassLoader().getResourceAsStream("members.txt");
        BufferedReader br = null;
		InputStreamReader isr = null;
        try{
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
            String linea = br.readLine();
            while(linea != null){
                final String name = linea;
                final Member member = new Member(name);
                members.add(member);
                allMembers.add(member);
                linea = br.readLine();
            }

        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
