package mpathozulu.assessment.investech.model;

public class ProvinceOrState {
    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProvinceOrState{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
