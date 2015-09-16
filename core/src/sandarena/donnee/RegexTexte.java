package sandarena.donnee;

/**
 * Created by Guillaume on 12/06/2015.
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTexte {
    private static String regex_per_frame = ".{1," + 35 + "}[ ]+";

    public static String[] parse_string(String line) {
        line += " ";
        Pattern p = Pattern.compile(regex_per_frame);
        Matcher m = p.matcher(line);
        ArrayList<String> liste = new ArrayList<String>();
        while (m.find()) {
            liste.add(m.group());
        }
        return liste.toArray(new String[liste.size()]);
    }

    public static void changeCharacter(int nombre){
        regex_per_frame = ".{1," + nombre + "}[ ]+";
    }

}
