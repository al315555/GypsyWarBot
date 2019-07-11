package algorithm;

import data.specific.Member;
import data.specific.Site;
import data.specific.Weapon;
import factory.MemberFactory;
import factory.SiteFactory;
import factory.WeaponFactory;
import service.EmailService;
import constant.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

public final class BattlesAlgorithm {

    private static Member winner = null;
	private static TreeMap<Long, String> historicOfKills= new TreeMap<Long, String>();
	public static String bodyTable = "";
	public static String htmlMembersList = "";
	public static String historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);

    public static final void execution(){
        System.out.println("Executing service algorithm... ");
        try {
            /*EmailService l_emailService = new EmailService();
            l_emailService.init();
            l_emailService.sendEmail();*/
            if (winner == null){
                MemberFactory factoryMember = new MemberFactory();
                SiteFactory factorySite= new SiteFactory();
                WeaponFactory factoryWeapon = new WeaponFactory();
                Weapon weapon = factoryWeapon.takeOneRandomly();
                Site site = factorySite.takeOneRandomly();
                Member killer = factoryMember.takeOneRandomly();
                Member dead = factoryMember.takeOneRandomly();
				boolean deadAndKillerSame = dead != null && dead.equals(killer);
				if(dead != null){
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
                    String strDateFormat = "dd/MM/yyyy hh:mm"; // El formato de fecha está especificado
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
                                Constants.DAY_OF_WEEK_ES.get(nowDate.get(Calendar.DAY_OF_WEEK)) + ", " +dateFormatted , winnerStr) + bodyTable;
                        System.out.println(String.format("¡WINNER!: %s", killer));
                    }
					historicalTable = String.format(Constants.RESULT_TABLE_TEMPLATE, bodyTable);
					htmlMembersList = factoryMember.getHtmlMembersList();

					/*Sending emails to suscriptors*/
                    EmailService l_emailService = new EmailService();
                    l_emailService.init();
                    l_emailService.sendEmailToSupcriptors();

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
}
