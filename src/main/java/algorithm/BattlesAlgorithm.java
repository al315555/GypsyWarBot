package algorithm;

import service.EmailService;

public final class BattlesAlgorithm {

    public static final void execution(){
        System.out.println("Executing service algorithm... ");
        try {
            /*EmailService l_emailService = new EmailService();
            l_emailService.init();
            l_emailService.sendEmail();*/
            System.out.println("Done!!");
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("Done with errors!!");
        }
    }
}
