package backend.com.edu.epam.kpp.Service;

import backend.com.edu.epam.kpp.Controller.ComplexNumberController;
import backend.com.edu.epam.kpp.Entity.ComplexNumber;
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
import org.springframework.stereotype.Service;



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


    public ComplexNumber checkCache(double real, double img) {
        return cache.getMap().get(new InputParam(real, img));
    }

    public void addIntoCache(double real, double img, ComplexNumber num) {
        cache.add(new InputParam(real, img), num);
    }


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
        ComplexNumber cacheResult = this.checkCache(Double.parseDouble(real),Double.parseDouble(img));

        if (cacheResult == null) {
            ComplexNumber cNum = new ComplexNumber(Double.parseDouble(real),Double.parseDouble(img));
            this.addIntoCache(Double.parseDouble(real), Double.parseDouble(img), cNum);
            return cNum;
        }

        logger.debug("buildNum completed ");
        return cacheResult;
    }

    public synchronized int incrementCounter(){
        logger.debug("Counter: " + i);
        return ++i;
    }
}
