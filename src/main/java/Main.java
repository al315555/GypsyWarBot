import constant.Constants;
import job.ScheduleExecution;
import service.EmailService;

import static spark.Spark.*;

public class Main {
    public static final int PORT_BY_DEFAULT = 4567;
    public static final String PORT = "PORT";
    private static EmailService emailService = new EmailService();
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", (req, res) -> Constants.HTML_MAIN_PAGE );
        get("/about", (req, res) -> "Gypsy family bot. Created by Ruben H.");
        get("/sendEmailTest", (req, res) -> {
            emailService.sendEmail();
            return "Gypsy family bot. Created by Ruben H.";
        });
        ScheduleExecution.execute();
    }
    static int getHerokuAssignedPort() {
        try {
            emailService.init();
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (processBuilder.environment().get(PORT) != null) {
                return Integer.parseInt(processBuilder.environment().get(PORT));
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }

        return PORT_BY_DEFAULT; //return default port if heroku-port isn't set (i.e. on localhost)

    }
}