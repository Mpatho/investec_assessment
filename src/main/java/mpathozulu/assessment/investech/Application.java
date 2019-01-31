package mpathozulu.assessment.investech;

import mpathozulu.assessment.investech.util.AddressUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    public static void main(String... args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.parse("2015-06-21T00:00:00.000Z"));
        new Date();
        String file = AddressUtil.class.getClassLoader().getResource("addresses.json").getFile();
        new AddressUtil(file).prettyPrintAddress();
    }

}
