package helpers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pavlo.basiuk on 06/10/2014.
 */
public final class DataGenerator {

    public static String getDateTimeString() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return ft.format(dNow);
    }

    //TODO: implement
    public String generateUnique(int numberOfDigits) {
        throw new NotImplementedException();
    }
}
