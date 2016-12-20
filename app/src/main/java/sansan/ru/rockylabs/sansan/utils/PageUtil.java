package sansan.ru.rockylabs.sansan.utils;

/**
 * Created by Zinnur on 19.12.16.
 */

public class PageUtil {

    public static int getPage(int bidsCount){
        int page = 0;
        int cachedPages = 0;
        page = bidsCount  / Const.LIMIT;
        return page;
    }
}