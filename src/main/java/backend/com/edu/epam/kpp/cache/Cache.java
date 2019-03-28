package backend.com.edu.epam.kpp.cache;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Cache {
    private static final Logger logger = Logger.getLogger(Cache.class);


    HashMap<InputParam, ComplexNumber> map = new HashMap<>();

    public Cache() {
    }


    public void add(InputParam parameters, ComplexNumber entity){
        map.put(parameters, entity);
        logger.info("Component added into cache");
    }

    public HashMap<InputParam, ComplexNumber> getMap() {
        return map;
    }

    public void setMap(HashMap<InputParam, ComplexNumber> map) {
        this.map = map;
    }
}
