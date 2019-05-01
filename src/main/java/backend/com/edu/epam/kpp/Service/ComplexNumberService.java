package backend.com.edu.epam.kpp.Service;

import backend.com.edu.epam.kpp.Controller.ComplexNumberController;
import backend.com.edu.epam.kpp.Entity.ComplexNumber;
import backend.com.edu.epam.kpp.Entity.InputList;
import backend.com.edu.epam.kpp.Entity.ResponseArray;
import backend.com.edu.epam.kpp.Repository.ComplexNumberRepository;
import backend.com.edu.epam.kpp.Repository.Impl.ComplexNumberRepositoryImpl;
import backend.com.edu.epam.kpp.cache.Cache;
import backend.com.edu.epam.kpp.cache.InputParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@EnableCaching
public class ComplexNumberService {

    private static final Logger logger = Logger.getLogger(ComplexNumberController.class);
    private int i = 0;

    @Autowired
    private ComplexNumberRepository repository;

    public ComplexNumber getByParts(double real, double img){
        if (logger.isDebugEnabled()) {
            logger.debug("getByParts method is called!");
        }
        return repository.getByParts(real, img);
    }

    @Bean
    public ComplexNumberRepository repository() {
        return new ComplexNumberRepositoryImpl();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("complexNumbers");
    }


    @Autowired
    private Cache cache;


    public String checkParameters(String real, String img){
        if (logger.isDebugEnabled()) {
            logger.debug("checkParameters method is called!");
        }
        try{
            Double.parseDouble(img);
        }catch (NumberFormatException e){
            logger.error("Incorrect 'imaginary' input");
            return "imaginary";
        }
        try {
            Double.parseDouble(real);
        }catch (NumberFormatException e){
            logger.error("Incorrect 'real' input");
            return "real";
        }
        logger.debug("checking finished successfully");
        return "none";
    }

    public ComplexNumber buildNum(String real, String img){

        if (logger.isDebugEnabled()) {
            logger.debug("buildNum method is called!");
        }
        logger.debug("buildNum completed ");

        ComplexNumber cNum = new ComplexNumber(Double.parseDouble(real),Double.parseDouble(img));
        return cNum;

    }

    public synchronized int incrementCounter(){
        logger.debug("Counter: " + i);
        return ++i;
    }

    public ResponseArray validateParams(InputList array){
        ResponseArray responseArray = new ResponseArray();
        array.getParams().stream().
                forEach(value->{
            responseArray.addNum(buildNum(Double.toString(value.getReal()),
                    Double.toString(value.getImaginary())));
        });

        HashMap<ComplexNumber, Integer> map = new HashMap<>();
        responseArray.getComplexNumbers().forEach(value->{
            if(map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            }
            else{
                map.put(value, 0);
            }
        });
        int popular = map.values().stream().max(Integer::compareTo).get();
        map.forEach((k, v)->{
            if(v == popular){
                responseArray.setMostPopularRequest(k);
            }
        });
        responseArray.setAmountOfParameters(responseArray.getComplexNumbers().stream().count());
        responseArray.setMax(responseArray.getComplexNumbers().stream().max(ComplexNumber::compareTo).orElse(null));
        responseArray.setMin(responseArray.getComplexNumbers().stream().min(ComplexNumber::compareTo).orElse(null));

        return responseArray;
    }
}
