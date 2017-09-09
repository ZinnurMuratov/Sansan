package sansan.ru.rockylabs.sansan.utils;

/**
 * Created by Zinnur on 22.12.16.
 */

public class FormatUtil {
    public static String stripNonDigits(final String input ){
        final StringBuilder sb = new StringBuilder(input.length() );
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
