package mpathozulu.assessment.investech.model;

public class Type {
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
        return "Type{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
