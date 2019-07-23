import constant.Constants;
import job.ScheduleExecution;
import service.EmailService;
import factory.MemberFactory;
import algorithm.BattlesAlgorithm;

import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

public class Main {
    public static final int PORT_BY_DEFAULT = 4567;
    public static final String PORT = "PORT";
    private static EmailService emailService = new EmailService();
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        get("/", (req, res) -> { return String.format( Constants.HTML_TEMPLATE_WAR_BOT, BattlesAlgorithm.htmlMembersList, BattlesAlgorithm.historicalTable); } );
        get("/about", (req, res) -> "Gypsy family bot. Created by Ruben H.");
        get("/sendEmailTest", (req, res) -> {
            emailService.sendEmail();
            return "Gypsy family bot. Created by Ruben H.";
        });

        ScheduleExecution.execute();
    }
    static int getHerokuAssignedPort() {
        Integer portReturn = PORT_BY_DEFAULT;

        try {
            emailService.init();
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (processBuilder.environment().get(PORT) != null) {
                portReturn =  Integer.parseInt(processBuilder.environment().get(PORT));
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }

        return portReturn; //return default port if heroku-port isn't set (i.e. on localhost)

    }
}