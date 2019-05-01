package backend.com.edu.epam.kpp.cache;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Cache {
    private static final Logger logger = Logger.getLogger(Cache.class);


    HashMap<InputParam, Integer> map = new HashMap<>();

    public Cache() {
    }


    public void add(InputParam parameters, Integer num){
        map.put(parameters, num);
        logger.info("Component added into cache");
    }

    public HashMap<InputParam, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<InputParam, Integer> map) {
        this.map = map;
    }
}
