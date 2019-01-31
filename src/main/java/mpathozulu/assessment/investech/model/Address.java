package mpathozulu.assessment.investech.model;

import java.util.Date;

public class Address {

    private Long id;
    private Type type;
    private AddressLineDetail addressLineDetail;
    private ProvinceOrState provinceOrState;
    private String cityOrTown;
    private Country country;
    private String postalCode;
    private Date lastUpdated;
    private String suburbOrDistrict;

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public AddressLineDetail getAddressLineDetail() {
        return addressLineDetail;
    }

    public ProvinceOrState getProvinceOrState() {
        return provinceOrState;
    }

    public String getCityOrTown() {
        return cityOrTown;
    }

    public Country getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public String getSuburbOrDistrict() {
        return suburbOrDistrict;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type=" + type +
                ", addressLineDetail=" + addressLineDetail +
                ", provinceOrState=" + provinceOrState +
                ", cityOrTown='" + cityOrTown + '\'' +
                ", country=" + country +
                ", postalCode='" + postalCode + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", suburbOrDistrict='" + suburbOrDistrict + '\'' +
                '}';
    }
}
