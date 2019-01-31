package mpathozulu.assessment.investech.util;

import mpathozulu.assessment.investech.model.Address;
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

public abstract class BaseAddressUtil {
    private final String file;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private final JSONParser parser = new JSONParser();

    protected BaseAddressUtil(String file) {
        this.file = file;
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
}
