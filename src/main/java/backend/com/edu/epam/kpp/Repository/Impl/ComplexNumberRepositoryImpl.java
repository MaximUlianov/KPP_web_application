package backend.com.edu.epam.kpp.Repository.Impl;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;
import backend.com.edu.epam.kpp.Repository.ComplexNumberRepository;
import org.springframework.cache.annotation.Cacheable;

public class ComplexNumberRepositoryImpl implements ComplexNumberRepository {

    @Override
    @Cacheable("complexNumbers")
    public ComplexNumber getByParts(double real, double img){
        imulateSlowService();
        return new ComplexNumber(real, img);
    }


    public void imulateSlowService(){
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
