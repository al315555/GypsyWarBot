package algorithm;

import data.specific.Member;
import data.specific.Site;
import data.specific.Weapon;
import factory.MemberFactory;
import factory.SiteFactory;
import factory.WeaponFactory;
import service.EmailService;

public final class BattlesAlgorithm {

    private static Member winner = null;
    private Member dead;

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
                    while (dead.equals(killer) && !factoryMember.isThereAWinner()) {
                        killer = factoryMember.takeOneRandomly();
                        dead = factoryMember.takeOneRandomly();
                    }
                    factoryMember.takeKiller(killer);
                    factoryMember.takeDead(dead);
                    System.out.println(String.format("KILLER: %s", killer));
                    System.out.println(String.format("DEAD: %s", dead));
                    System.out.println(String.format("WITH: %s", weapon));
                    System.out.println(String.format("TAKE PLACE IN: %s", site));
                    if (factoryMember.isThereAWinner()) {
                        winner = killer;
                        System.out.println(String.format("¡WINNER!: %s", killer));
                    }
                    System.out.println("Done!!");

                
            }else{
                System.out.println("Finished. The winner is " + winner.toString());
            }
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Done with errors!!");
        }
    }
}
