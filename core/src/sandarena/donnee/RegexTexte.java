package sandarena.donnee;

/**
 * Created by Guillaume on 12/06/2015.
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTexte {
    public static int CHARACTERS_PER_FRAME = 35;
    private static final String regex_per_frame = ".{1," + CHARACTERS_PER_FRAME + "}[ ]+";

    public static String[] parse_string(String line) {
        line += " ";
        Pattern p = Pattern.compile(regex_per_frame);
        Matcher m = p.matcher(line);
        ArrayList<String> liste = new ArrayList<String>();
        while (m.find()) {
            liste.add(m.group());
        }
        String[] fin = liste.toArray(new String[liste.size()]);
        return fin;
    }

}
