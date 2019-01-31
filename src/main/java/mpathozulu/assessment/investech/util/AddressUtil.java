package mpathozulu.assessment.investech.util;

import mpathozulu.assessment.investech.model.Address;
import mpathozulu.assessment.investech.model.AddressLineDetail;
import mpathozulu.assessment.investech.model.Country;
import mpathozulu.assessment.investech.model.ProvinceOrState;
import mpathozulu.assessment.investech.model.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddressUtil {

    private final JSONParser parser = new JSONParser();

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final String file;

    public AddressUtil(String file) {
        this.file = file;
    }

    public void prettyPrintAddress() {
        for (Address address : getAddresses()) {
            System.out.println(prettyPrintAddress(address));
        }
    }

    public void printAddress(Address address) {
        printAddressLineDetail(address.getAddressLineDetail());
        printSuburbOrDistrict(address.getSuburbOrDistrict());
        printCityOrTown(address.getCityOrTown());
        printProvinceOrState(address.getProvinceOrState());
        printPostalCode(address.getPostalCode());
        printCountry(address.getCountry());
        System.out.println("-----------------------------");
    }

    public String prettyPrintAddress(Address address) {
        String type = prettyPrintType(address.getType());
        String addressLineDetail = prettyPrintAddressLineDetail(address.getAddressLineDetail());
        String cityOrTown = prettyPrintCity(address.getCityOrTown());
        String provinceOrState = prettyPrintProvinceOrState(address.getProvinceOrState());
        String postalCode = prettyPrintPostalCode(address.getPostalCode());
        String country = prettyPrintCountry(address.getCountry());
        String format = "%s: %s - %s - %s - %s – %s";
        return String.format(format, type, addressLineDetail, cityOrTown, provinceOrState, postalCode, country);
    }

    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        try {
            JSONArray parse = (JSONArray) parser.parse(new FileReader(file));
            Iterator iterator = parse.iterator();
            while (iterator.hasNext()) {
                Address address = new Address();
                setObject(address, (JSONObject) iterator.next());
                addresses.add(address);
            }
        } catch (IOException | ParseException | ReflectiveOperationException | java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return addresses;
    }

    private String prettyPrintCountry(Country country) {
        if (country == null) {
            return "__";
        }
        return String.format("%s (%s)", country.getName(), country.getCode());
    }

    private String prettyPrintPostalCode(String postalCode) {
        if (postalCode == null) {
            return "__";
        }
        return postalCode;
    }

    private String prettyPrintProvinceOrState(ProvinceOrState provinceOrState) {
        if (provinceOrState == null) {
            return "__";
        }
        return provinceOrState.getName();
    }

    private String prettyPrintCity(String cityOrTown) {
        if (cityOrTown == null) {
            return "__";
        }
        return cityOrTown;
    }

    private String prettyPrintType(Type type) {
        if (type == null) {
            return "__";
        }
        return type.getName();
    }

    private String prettyPrintAddressLineDetail(AddressLineDetail addressLineDetail) {
        if (addressLineDetail == null) {
            return "__";
        }
        return addressLineDetail.getLine1() + ", " + addressLineDetail.getLine2();
    }

    private void setObject(Object obj, JSONObject jsonObject) throws ReflectiveOperationException, java.text.ParseException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            Object value = jsonObject.get(field.getName());
            if (value == null) {
                continue;
            }
            if (value instanceof JSONObject) {
                Object instance = field.getType().newInstance();
                setObject(instance, (JSONObject) value);
                setVariable(obj, field.getName(), instance);
            } else if (field.getType().equals(String.class)) {
                setVariable(obj, field.getName(), value);
            } else if (field.getType().equals(Long.class)) {
                setVariable(obj, field.getName(), Long.valueOf((String) value));
            } else if (field.getType().equals(Date.class)) {
                setVariable(obj, field.getName(), format.parse((String) value));
            }
        }
    }

    private void setVariable(Object obj, String name, Object value) throws ReflectiveOperationException {
        Class clazz = obj.getClass();
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
        field.setAccessible(false);
    }

    private void printProvinceOrState(ProvinceOrState provinceOrState) {
        if (provinceOrState != null) {
            System.out.printf("Province or State \t\t : %s%n", provinceOrState.getName());
        }
    }

    private void printCityOrTown(String cityOrTown) {
        if (cityOrTown != null) {
            System.out.printf("City or Town \t\t\t : %s%n", cityOrTown);
        }
    }

    private void printAddressLineDetail(AddressLineDetail addressLineDetail) {
        if (addressLineDetail != null) {
            System.out.printf("Address Line Detail \t : %s, %s%n", addressLineDetail.getLine1(), addressLineDetail.getLine2());
        }
    }

    private void printPostalCode(String postalCode) {
        if (postalCode != null) {
            System.out.printf("Postal Code \t\t\t : %s%n", postalCode);
        }
    }

    private void printCountry(Country country) {
        if (country != null) {
            System.out.printf("Country \t\t\t\t : %s%n", country.getName());
        }
    }
    private void printSuburbOrDistrict(String suburbOrDistrict) {
        if (suburbOrDistrict != null) {
            System.out.printf("Suburb or District \t\t : %s%n", suburbOrDistrict);
        }
    }
}

