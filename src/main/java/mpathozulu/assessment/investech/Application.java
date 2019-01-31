package mpathozulu.assessment.investech;

import mpathozulu.assessment.investech.model.Address;
import mpathozulu.assessment.investech.util.AddressUtil;

import java.text.ParseException;

public class Application {

    public static void main(String... args) throws ParseException {
        String file = AddressUtil.class.getClassLoader().getResource("addresses.json").getFile();
        AddressUtil addressUtil = new AddressUtil(file);
        addressUtil.prettyPrintAddress();
        for (Address address : addressUtil.getAddresses()) {
            addressUtil.printAddress(address);
        }
    }

}
