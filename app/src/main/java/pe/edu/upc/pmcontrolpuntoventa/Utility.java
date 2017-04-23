package pe.edu.upc.pmcontrolpuntoventa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ALEXANDER23 on 23/04/2017.
 */

public class Utility {
    private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            //"^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@"
            //        + "[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$";

    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
}
