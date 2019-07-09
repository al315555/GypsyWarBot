import static spark.Spark.*;

public class Main {
    public static final int PORT_BY_DEFAULT = 4567;
    public static final String PORT = "PORT";
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/", (req, res) -> "Main Screen: Gypsy family bot. Created by Ruben H.");
        get("/about", (req, res) -> "Gypsy family bot. Created by Ruben H.");
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get(PORT) != null) {
            return Integer.parseInt(processBuilder.environment().get(PORT));
        }
        return PORT_BY_DEFAULT; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}