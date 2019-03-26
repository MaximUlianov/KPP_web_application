package backend.com.edu.epam.kpp.Service;

import backend.com.edu.epam.kpp.Controller.ComplexNumberController;
import backend.com.edu.epam.kpp.Entity.ComplexNumber;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service
public class ComplexNumberService {

    private static final Logger logger = Logger.getLogger(ComplexNumberController.class);

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

        ComplexNumber num = new ComplexNumber(Double.parseDouble(real),Double.parseDouble(img));
        logger.debug("buildNum completed ");
        return num;
    }
}
