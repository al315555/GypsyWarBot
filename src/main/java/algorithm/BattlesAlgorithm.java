package algorithm;

import data.specific.Member;
import data.specific.Site;
import data.specific.Weapon;
import factory.MemberFactory;
import factory.SiteFactory;
import factory.WeaponFactory;
import service.EmailService;
import constant.Constants;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public final class BattlesAlgorithm {

    public static final String VIEW_STATUS_TXT = "target/viewStatus.txt";
    public static final String ALGO_STATUS_DEADS_TXT = "target/algoStatusDeads.txt";
    public static final String ALGO_STATUS_ALIVE_TXT = "target/algoStatusAlive.txt";
    private static Member winner = null;
	private static TreeMap<Long, String> historicOfKills= new TreeMap<Long, String>();
	public static String bodyTable = "";
	private static boolean fisrtBlood = false;
	public static String htmlMembersList = "";
	public static String historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);

    public static final void execution(){
        System.out.println("Executing service algorithm... ");
        try {
            /*EmailService l_emailService = new EmailService();
            l_emailService.init();
            l_emailService.sendEmail();*/
            //loadData();
            if(!fisrtBlood) new EmailService().init().sendEmailToSupcriptorsInit();
            if (winner == null){
                MemberFactory factoryMember = new MemberFactory();
                SiteFactory factorySite= new SiteFactory();
                WeaponFactory factoryWeapon = new WeaponFactory();
                Weapon weapon = factoryWeapon.takeOneRandomly();
                Site site = factorySite.takeOneRandomly();
                Member killer = factoryMember.takeOneRandomly();
                Member dead = factoryMember.takeOneRandomly();
				boolean deadAndKillerSame = dead != null && dead.equals(killer);
				if(dead != null) {
                    String strDateFormat = Constants.DATE_HOUR_FORMAT;
                    if (!fisrtBlood) {
                        fisrtBlood = true;
                        Calendar nowDate = Calendar.getInstance();
                        Long instant = Calendar.getInstance().getTimeInMillis();
                        nowDate.setTimeInMillis(instant);
                        SimpleDateFormat formatSimple = new SimpleDateFormat(strDateFormat);
                        String dateFormatted = formatSimple.format(nowDate.getTime());
                        bodyTable = String.format(Constants.RESULT_BODY_TABLE_TEMPLATE,
                                Constants.DAY_OF_WEEK_ES.get(nowDate.get(Calendar.DAY_OF_WEEK)) + ", " + dateFormatted, Constants.HTML_MSG_INIT_PLAY) + bodyTable;
                    }else{
                        while (deadAndKillerSame && !factoryMember.isThereAWinner()) {
                            killer = factoryMember.takeOneRandomly();
                            dead = factoryMember.takeOneRandomly();
                            deadAndKillerSame = dead != null && dead.equals(killer);
                        }
                        factoryMember.takeKiller(killer);
                        factoryMember.takeDead(dead);
                        System.out.println(String.format("KILLER: %s", killer));
                        System.out.println(String.format("DEAD: %s", dead));
                        System.out.println(String.format("WITH: %s", weapon));
                        System.out.println(String.format("TAKE PLACE IN: %s", site));
                        //site, killer, dead, weapon
                        final String resultOfTheBattle = String.format(Constants.HTML_RESULT_TEMPLATE, site, killer, dead, weapon);
                        Long instant = Calendar.getInstance().getTimeInMillis();
                        Calendar nowDate = new GregorianCalendar();
                        nowDate.setTimeInMillis(instant);
                        historicOfKills.put(instant, resultOfTheBattle);
                        SimpleDateFormat formatSimple = new SimpleDateFormat(strDateFormat);
                        String dateFormatted = formatSimple.format(nowDate.getTime());
                        bodyTable = String.format(Constants.RESULT_BODY_TABLE_TEMPLATE,
                                Constants.DAY_OF_WEEK_ES.get(nowDate.get(Calendar.DAY_OF_WEEK)) + ", " + dateFormatted, resultOfTheBattle) + bodyTable;
                        if (factoryMember.isThereAWinner()) {
                            winner = killer;
                            final String winnerStr = String.format(Constants.HTML_RESULT_TEMPLATE_WINNER, killer, killer.getKills());
                            instant = Calendar.getInstance().getTimeInMillis();
                            nowDate.setTimeInMillis(instant);
                            historicOfKills.put(instant, winnerStr);
                            dateFormatted = formatSimple.format(nowDate.getTime());
                            bodyTable = String.format(Constants.RESULT_BODY_TABLE_TEMPLATE,
                                    Constants.DAY_OF_WEEK_ES.get(nowDate.get(Calendar.DAY_OF_WEEK)) + ", " + dateFormatted, winnerStr) + bodyTable;
                            System.out.println(String.format("¡WINNER!: %s", killer));
                        }

                        /*Sending emails to suscriptors*/
                        EmailService l_emailService = new EmailService();
                        l_emailService.init();
                        l_emailService.sendEmailToSupcriptors(MemberFactory.getDeadMembers().size());
                    }
                    historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);
                    htmlMembersList = factoryMember.getHtmlMembersList();
                    //storeData();
                    System.out.println("Done!!");

				}else{
					System.out.println("Any member in the list. NOTHING TO DONE");
				}
                
            }else{
                System.out.println("Finished. The winner is " + winner.toString());
            }
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Done with errors!!");
        }
    }


    private static void storeData(){
        try {
            storeViewInFile();
            storeDeadsDataInFile();
            storeAliveDataInFile();

        }catch (Exception expcetion){
            expcetion.printStackTrace();
        }
    }

    private static void storeViewInFile() throws Exception{
            String sFichero = VIEW_STATUS_TXT;
            File fichero = new File(sFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            bw.write("" + winner + "\n");
            bw.write("" + htmlMembersList + "\n");
            bw.write("" + historicalTable + "\n");
            bw.close();
    }

    private static void storeDeadsDataInFile() throws Exception{

            String sFichero = ALGO_STATUS_DEADS_TXT;
            File fichero = new File(sFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            /*if (fichero.exists()) {
                for (int x=0;x<;x++)

            } else {

            }*/
            for(Member member : MemberFactory.getDeadMembers()){
                bw.write("" + member.toString() + "\n");
            }

            bw.close();
    }

    private static void storeAliveDataInFile() throws Exception{
            String sFichero = ALGO_STATUS_ALIVE_TXT;
            File fichero = new File(sFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
            /*if (fichero.exists()) {
                for (int x=0;x<;x++)

            } else {

            }*/
            for(Member member : MemberFactory.getAliveMembers()){
                bw.write("" + member.toString() + "\n");
            }

            bw.close();
    }

    private static void loadData(){
        try {
            fisrtBlood = false;
            loadAliveMembers();
            loadDeadMembers();
            loadView();
            fisrtBlood = !MemberFactory.getDeadMembers().isEmpty();
        }catch (Exception exception){
            exception.printStackTrace();

        }
    }

    private static void loadAliveMembers() throws Exception{
        final ArrayList<Member> aliveMembers = new ArrayList<>();
        String sFichero1 = ALGO_STATUS_ALIVE_TXT;
        File fichero1 = new File(sFichero1);
        BufferedReader br1 = new BufferedReader(new FileReader(sFichero1));
        String memberAlive = br1.readLine();
        while(memberAlive != null){
            final String name = memberAlive;
            final Member member = new Member(name);
            aliveMembers.add(member);
            memberAlive = br1.readLine();
        }
        br1.close();
        MemberFactory.setAliveMembers(aliveMembers);
    }

    private static void loadDeadMembers() throws Exception{
        final ArrayList<Member> deadMembers = new ArrayList<>();
        String sFichero1 = ALGO_STATUS_DEADS_TXT;
        File fichero1 = new File(sFichero1);
        BufferedReader br1 = new BufferedReader(new FileReader(sFichero1));
        String memberDead = br1.readLine();
        while(memberDead != null){
            final String name = memberDead;
            final Member member = new Member(name);
            deadMembers.add(member);
            memberDead = br1.readLine();
        }
        MemberFactory.setDeadMembers(deadMembers);
        br1.close();
    }

    private static void loadView() throws Exception{
        String sFichero1 = VIEW_STATUS_TXT;
        File fichero1 = new File(sFichero1);
        BufferedReader br1 = new BufferedReader(new FileReader(sFichero1));
        winner = new Member(br1.readLine());
        htmlMembersList = br1.readLine();
        historicalTable = br1.readLine();
        br1.close();
    }
}
