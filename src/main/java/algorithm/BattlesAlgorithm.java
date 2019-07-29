package algorithm;

import data.specific.Member;
import data.specific.Site;
import data.specific.Weapon;
import factory.MemberFactory;
import factory.SiteFactory;
import factory.WeaponFactory;
import service.BackUpService;
import service.EmailService;
import constant.Constants;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public final class BattlesAlgorithm {

    public static final String VIEW_STATUS_TXT = "target/viewStatus.txt";
    public static final String ALGO_STATUS_DEADS_TXT = "target/algoStatusDeads.txt";
    public static final String ALGO_STATUS_ALIVE_TXT = "target/algoStatusAlive.txt";
    public static final int MILISECONDS_SHIFT_INT = 7200000;
    private static Member winner = null;
	private static TreeMap<Long, String> historicOfKills= new TreeMap<Long, String>();
	public static String bodyTable = "";
	private static boolean fisrtBlood = false;
	public static String htmlMembersList = "";
	public static String historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);

    public static final void execution(){
        System.out.println("Executing service algorithm... ");
        try {
            loadData();
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
                        Long instant = nowDate.getTimeInMillis() + MILISECONDS_SHIFT_INT;
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
                        Calendar nowDate = Calendar.getInstance();;
                        Long instant = nowDate.getTimeInMillis() + MILISECONDS_SHIFT_INT;
                        nowDate.setTimeInMillis(instant);
                        historicOfKills.put(instant, resultOfTheBattle);
                        SimpleDateFormat formatSimple = new SimpleDateFormat(strDateFormat);
                        String dateFormatted = formatSimple.format(nowDate.getTime());
                        bodyTable = String.format(Constants.RESULT_BODY_TABLE_TEMPLATE,
                                Constants.DAY_OF_WEEK_ES.get(nowDate.get(Calendar.DAY_OF_WEEK)) + ", " + dateFormatted, resultOfTheBattle) + bodyTable;
                        if (factoryMember.isThereAWinner()) {
                            winner = killer;
                            final String winnerStr = String.format(Constants.HTML_RESULT_TEMPLATE_WINNER, killer, killer.getKills());
                            nowDate = Calendar.getInstance();;
                            instant = nowDate.getTimeInMillis() + MILISECONDS_SHIFT_INT;
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
                    storeData();
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

            final HashMap<String, Object> backup = new HashMap<>();
            /*backup.put(Constants.STRING_ALIVES, aliveString);
            backup.put(Constants.STRING_DEADS, deadString);*/
            backup.put(Constants.ALIVES, MemberFactory.getAliveMembers());
            backup.put(Constants.DEADS, MemberFactory.getDeadMembers());
            backup.put(Constants.HTML_BODY_TABLE, bodyTable);
            BackUpService.toBackUpData(backup);

            /*storeViewInFile();
            storeDeadsDataInFile();
            storeAliveDataInFile();*/

        }catch (Exception expcetion){
            expcetion.printStackTrace();
        }
    }

    private static void loadData(){
        try {
            final HashMap<String, Object> backup = new HashMap<>();
            BackUpService.toRetrieveData(backup);
            bodyTable = backup.get(Constants.HTML_BODY_TABLE) != null ? (String ) backup.get(Constants.HTML_BODY_TABLE) : "";
            if(backup.get(Constants.HTML_BODY_TABLE) != null){
                MemberFactory.setAliveMembers((ArrayList<Member>) backup.get(Constants.ALIVES));
                MemberFactory.setDeadMembers((ArrayList<Member>) backup.get(Constants.DEADS));
            }else{
                MemberFactory.loadResourcesData();
            }
            historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);
            htmlMembersList = MemberFactory.getHtmlMembersList();
            fisrtBlood = !"".equals(bodyTable);
        }catch (Exception exception){
            exception.printStackTrace();

        }
    }
}
