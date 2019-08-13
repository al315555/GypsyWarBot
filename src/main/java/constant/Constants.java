package constant;

import java.util.HashMap;
import java.util.Map;

public final class Constants {
	public static final String HTML_TEMPLATE_WAR_BOT = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<title>Familia Gitanah War Bot</title>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
            "<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\n" +
            "<link rel=\"shortcut icon\" type=\"image/png\" href=\"https://cdn3.iconfinder.com/data/icons/outline-location-icon-set/64/Weapons_1-512.png\"/>" +
            "<style> .grid-container {\n" +
            "  display: grid;" +
            " overflow: auto !important;" +
            " border: 0;" +
            "  grid-template-columns: auto auto auto;\n" +
            "  background-color: #E8DC8A    ;\n" +
            "  padding: 3px; overflow: auto !important;\n" +
            "}\n" +
            ".grid-item {\n" +
            "  background-color: rgba(255, 255, 255, 0.8);\n" +
            "  border: 0 solid rgba(0, 0, 0, 0.8);\n" +
            "  padding: 3px;\n" +
            "  font-size: 0.8em;\n" +
            "  text-align: center; overflow: auto !important;\n" +
            "} " +
            "body,h1,h2,h3,h4,h5 {font-family: \"Raleway\", sans-serif}\n" +
            "</style>\n" +
            "<body class=\"w3-light-grey\">\n" +
            "\n" +
            "<!-- w3-content defines a container for fixed size centered content, \n" +
            "and is wrapped around the whole page content, except for the footer in this example -->\n" +
            "<div class=\"w3-content\" style=\"max-width:1400px\">\n" +
            "\n" +
            "<!-- Header -->\n" +
            "<header class=\"w3-container w3-center w3-padding-32\"> \n" +
            "  <h1><b>Familia Gitanah War Bot</b></h1>\n" +
            "  <p>Bienvenidos al simulador de batallas de la familia gitana." +
            "</header>\n" +
            "\n" +
            "<!-- Grid -->\n" +
            "<div class=\"w3-row\">\n" +
            "\n" +
            "<!-- START MEMBERS -->\n" +
            "<div class=\"w3-col l8 s12\">\n" +
            "  <!-- Blog entry -->\n" +
            "  <div class=\"w3-card-4 w3-margin w3-white\">\n" +
            "    <div class=\"w3-container\">\n" +
            "      <h3><b>La familia gitanah</b></h3>\n" +
            "    </div>\n" +
            "\n" +
            "    <div class=\"w3-container\">\n" +
            "     <div class=\"w3-large w3-center\" style=\" color:black;\" > %s </div>    " +
            "      </br>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <hr>\n" +
            "\n" +
            "<!-- END MEMBERS -->\n" +
            "</div>\n" +
            "\n" +
            "<!-- Introduction menu -->\n" +
            "<div class=\"w3-col l4\" \n" +
            "  <!-- About Card -->\n" +
            "  \n" +
            "  <!-- HISTORICAL -->\n" +
            "  <div class=\"w3-card w3-margin\" style=\"background-color:white;\">\n" +
            "    <div class=\"w3-container w3-padding\">\n" +
            "      <h4>&iquest;Qu&eacute; ha ocurrido hasta el momento?</h4>\n" +
            "    </div>\n" +
            "    <div>" +
            "       %s " +
            "    </div>   " +
            "  </div>\n" +
			"    <div>" +
			" 		</br><b><a href=\"/gamesHistory\">Ir al historial de partidas &#8594;</a></b></br>" +
			"    </div>   " +
            "<!-- END w3-content -->\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n";

	public static final String HTML_TEMPLATE_HISTORICAL_WAR_BOT = "<!DOCTYPE html>\n" +
			"<html>\n" +
			"<title>Historical Familia Gitanah War Bot</title>\n" +
			"<meta charset=\"UTF-8\">\n" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
			"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
			"<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\n" +
			"<link rel=\"shortcut icon\" type=\"image/png\" href=\"https://cdn3.iconfinder.com/data/icons/outline-location-icon-set/64/Weapons_1-512.png\"/>" +
			"<style> .grid-container {\n" +
			"  display: grid;" +
			" overflow: auto !important;" +
			" border: 0;" +
			"  grid-template-columns: auto auto auto;\n" +
			"  background-color: #E8DC8A    ;\n" +
			"  padding: 3px; overflow: auto !important;\n" +
			"}\n" +
			".grid-item {\n" +
			"  background-color: rgba(255, 255, 255, 0.8);\n" +
			"  border: 0 solid rgba(0, 0, 0, 0.8);\n" +
			"  padding: 25px;\n" +
			"  font-size: 0.8em;\n" +
			"  text-align: center; overflow: auto !important;\n" +
			"} " +
			"body,h1,h2,h3,h4,h5 {font-family: \"Raleway\", sans-serif}\n" +
			"</style>\n" +
			"<body class=\"w3-light-grey\">\n" +
			"\n" +
			"<!-- w3-content defines a container for fixed size centered content, \n" +
			"and is wrapped around the whole page content, except for the footer in this example -->\n" +
			"<div class=\"w3-content\" style=\"max-width:1400px\">\n" +
			"\n" +
			"<!-- Header -->\n" +
			"<header class=\"w3-container w3-center w3-padding-32\"> \n" +
			"  <h1><b>Familia Gitanah War Bot</b></h1>\n" +
			"  <p>Bienvenidos al historial de batallas de la familia gitana. " +
			"</header>\n" +
			"\n" +
			"<!-- Grid -->\n" +

			"<div class=\"w3-card-1 l8 s14\">\n" +
			"  <!-- Blog entry -->\n" +
			"  <div class=\"w3-card-4 w3-margin w3-white\">\n" +
			"    <div class=\"w3-container\">\n" +
			"      <h3><b>Historial de partidas</b></h3>\n" +
			"    </div>\n" +
			"\n" +
			"    <div class=\"w3-container\">\n" +
			"     <div class=\"w3-large w3-center\" > %s </div>    " +
			"      </br>\n" +
			"    </div>\n" +
			"	</br><b><a href=\"/\"> &#8592; Volver al simulador</a></b></br>" +
			"  </div>\n" +
			"\n" +
			"<!-- END MEMBERS -->\n" +
			"</div>\n" +

			"\n" +

			"<!-- END w3-content -->\n" +
			"</div>\n" +
			"</body>\n" +
			"</html>\n";

	public static final String HTML_DEFAULT_TABLE_TEXT = "Por ahora no hay miembros...";
	public static final String HTML_DEFAULT_SUBTABLE_TEXT = "Paciencia, a&uacute;n no ha comenzado el juego...";
	public static final String HTML_RESULT_TEMPLATE = "En %s, %s acaba de matar a %s con %s";//site, killer, dead, weapon
	public static final String HTML_RESULT_TEMPLATE_WINNER = "El ganador es %s con %s muertes realizadas.";
	public static final String RESULT_TABLE_TEMPLATE = "<table><tr><td><table cellspacing=\"10\" cellpadding=\"0\" width:\"100%%\" ><tr style=\"color:black;background-color:white; text-align:center; width:100%%;\">" +
            "<th style=\" color:black; text-align:center;font-size:1.2em !important;\">&iquest;Cu&aacute;ndo y c&oacute;mo ha ocurrido?</th>" +
            "<th style=\" color:black; text-align:center;font-size:1.2em !important;\">&nbsp;&nbsp;</th></tr></table></td></tr><tr><td><div style=\"height:28em; overflow:auto; border-bottom: 1px solid #ddd;\">" +
            "<table cellspacing=\"0\" cellpadding=\"1\" width:\"95%%\" style=\"color:black;background-color:white; font-size:1em;\">%s</table>";//bodyTable
	public static final String RESULT_HISTORICAL_TEMPLATE = "<table><tr><td><table cellspacing=\"10\" cellpadding=\"0\" width:\"100%%\" ><tr style=\"color:black;background-color:white; text-align:center; width:100%%;\">" +
			"<th style=\" color:black; text-align:center;font-size:1.2em !important;\">Partidas anteriores...</th>" +
			"<th style=\" color:black; text-align:center;font-size:1.2em !important;\">&nbsp;&nbsp;</th></tr></table></td></tr><tr><td><div style=\"height:28em; overflow:auto; border-bottom: 1px solid #ddd;\">" +
			"%s" +
			"</table>";

	public static final String RESULT_BODY_TABLE_TEMPLATE = "<tr><td style=\" border-bottom: 1px solid #ddd; \">&nbsp;%s&nbsp;&nbsp;</td><td  style=\" border-bottom: 1px solid #ddd; \">&nbsp;%s</td></tr>";//date, result
    public static final HashMap<Integer, String> DAY_OF_WEEK_ES = new HashMap<Integer, String>(){{
        put(2, "Lunes");put(3, "Martes");put(4, "Miercoles");put(5, "Jueves");put(6, "Viernes");put(7, "Sabado");put(1, "Domingo");
    }};

    public static final String EMAIL_SUBJECT_KILL_PRODUCED = "War Bot. Se ha producido el ASESINATO %s en la Familia";
    public static final String EMAIL_SUBJECT_START = "War Bot. Han comenzado las batallas.";
    public static final String DATE_HOUR_FORMAT =  "dd/MM/yyyy hh:mm";
    public static final String HTML_MSG_INIT_PLAY = "&iexcl;ATENTOS! Las batallas acaban de empezar.";
    public static final String EMAIL_BODY_START = "<!DOCTYPE html>\n" +
			"<html>\n" +
			"<title>Familia Gitanah War Bot</title>\n" +
			"<meta charset=\"UTF-8\">\n" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
			"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
			"<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\"/>\n" +
			"<link rel=\"shortcut icon\" type=\"image/png\" href=\"https://cdn3.iconfinder.com/data/icons/outline-location-icon-set/64/Weapons_1-512.png\"/>" +
			"<style>" +
			".body{background-image: url(\"https://www.sinembargo.mx/wp-content/uploads/2013/07/peleando-por-un-sueno.jpg\");\n" +
			"  background-repeat: no-repeat;\n" +
			"  background-attachment: fixed;}" +
			"</style>" +
			"<body class=\"w3-light-grey\" style=\"font-family: \"Raleway\", sans-serif;\">\n" +
			"<!-- Header -->\n" +
			"<header class=\"w3-container w3-center w3-padding-32\"> \n" +
			"  <h2>Familia Gitanah War Bot</h2>"+
			"  <h1><b>&iexcl;Est&aacute;n apunto de comenzar las batallas!</b></h1>\n" +
			"  <p>La guerra de la familia, ha comenzado. Paciencia, en cuanto ocurra algo, tendr&aacute;s un nuevo email. As&iacute; no est&aacute;s pendiente todo el tiempo de lo que sucede. A no ser que te lo cuenten antes... <p>&iquest;Ser&aacute;s el primero en enterarte? " +
			" Echa un vistazo a las batallas desde el simulador. "+
			"<p>Para ir al simulador pulsa en el <a href=\"https://gypsywarbot.herokuapp.com/\">enlace</a>"+
			"</header>\n" +
			"</body>\n" +
			"</html>\n";
    public static final String EMAIL_BODY_KILL_PRODUCED = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<title>Familia Gitanah War Bot</title>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
            "<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\"/>\n" +
            "<link rel=\"shortcut icon\" type=\"image/png\" href=\"https://cdn3.iconfinder.com/data/icons/outline-location-icon-set/64/Weapons_1-512.png\"/>" +
            "<body class=\"w3-light-grey\" style=\"font-family: \"Raleway\", sans-serif;\">\n" +
            "<!-- Header -->\n" +
            "<header class=\"w3-container w3-center w3-padding-32\"> \n" +
            "  <h1><b>Familia Gitanah War Bot</b></h1>\n" +
            "  <p>Echa un vistazo a las batallas. &iquest;Habr&aacute; ganado ya alguien?" +
            "<p>Para ir al simulador pulsa en el <a href=\"https://gypsywarbot.herokuapp.com/\">enlace</a>"+
            "</header>\n" +
            "</body>\n" +
            "</html>\n";
    public static final String ALIVES = "ALIVES";
    public static final String DEADS = "DEADS";
    public static final String STRING_DEADS = "STRING_DEADS";
    public static final String STRING_ALIVES= "STRING_ALIVES";
    public static final String HTML_BODY_TABLE= "HTML_BODY_TABLE";


	public static final String HTML_BODY_HISTORICAL = "HTML_BODY_HISTORICAL";
}
