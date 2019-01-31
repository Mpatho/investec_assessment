package mpathozulu.assessment.investech.util;

import mpathozulu.assessment.investech.exception.AddressException;
import mpathozulu.assessment.investech.model.Address;
import mpathozulu.assessment.investech.model.AddressLineDetail;
import mpathozulu.assessment.investech.model.Country;
import mpathozulu.assessment.investech.model.ProvinceOrState;
import mpathozulu.assessment.investech.model.Type;

public class AddressUtil extends BaseAddressUtil {

    public AddressUtil(String file) {
        super(file);
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
        System.out.println("----------------------------------------");
    }

    public String isValidAddress(Address address) {
        try {
            validatePostalCode(address.getPostalCode());
            validateAddressLineDetail(address.getAddressLineDetail());
            if (isCountySouthAfrica(address.getCountry())) {
                validateProvince(address.getProvinceOrState());
            }
            return "Address is valid";
        } catch (AddressException e) {
            return String.format("Address is not valid \t : %s", e.getMessage());
        }
    }

    private String prettyPrintAddress(Address address) {
        String type = prettyPrintType(address.getType());
        String addressLineDetail = prettyPrintAddressLineDetail(address.getAddressLineDetail());
        String cityOrTown = prettyPrintCity(address.getCityOrTown());
        String provinceOrState = prettyPrintProvinceOrState(address.getProvinceOrState());
        String postalCode = prettyPrintPostalCode(address.getPostalCode());
        String country = prettyPrintCountry(address.getCountry());
        String format = "%s: %s - %s - %s - %s – %s";
        return String.format(format, type, addressLineDetail, cityOrTown, provinceOrState, postalCode, country);
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

    private void validateProvince(ProvinceOrState provinceOrState) {
        if (provinceOrState == null) {
            throw new AddressException("Province is null.");
        }
        if (provinceOrState.getName() == null) {
            throw new AddressException("Province name is blank.");
        }
        if (provinceOrState.getCode() == null) {
            throw new AddressException("Province code is blank.");
        }
    }

    private void validateAddressLineDetail(AddressLineDetail addressLineDetail) {
        if (addressLineDetail == null) {
            throw new AddressException("Line Detail is null.");
        }
        if (addressLineDetail.getLine1() == null && addressLineDetail.getLine2() == null) {
            throw new AddressException("Line Detail is blank.");
        } else if (addressLineDetail.getLine1().isEmpty() && addressLineDetail.getLine2().isEmpty()) {
            throw new AddressException("Line Detail is blank.");
        }
    }

    private void validatePostalCode(String postalCode) {
        if (postalCode == null) {
            throw new AddressException("Postal Code is null.");
        }
        if (!postalCode.matches("^[0-9]+$")) {
            throw new AddressException("Postal Code is not a number.");
        }
    }

    private boolean isCountySouthAfrica(Country country) {
        if (country != null) {
            return "ZA".equalsIgnoreCase(country.getCode());
        }
        return false;
    }

}

