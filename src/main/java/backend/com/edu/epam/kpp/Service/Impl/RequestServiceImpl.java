package backend.com.edu.epam.kpp.Service.Impl;

import backend.com.edu.epam.kpp.Controller.ComplexNumberController;
import backend.com.edu.epam.kpp.Entity.*;
import backend.com.edu.epam.kpp.Repository.RequestRepository;
import backend.com.edu.epam.kpp.Repository.ResponseRepository;
import backend.com.edu.epam.kpp.Service.RequestService;
import backend.com.edu.epam.kpp.cache.Cache;
import backend.com.edu.epam.kpp.cache.InputParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger logger = Logger.getLogger(RequestServiceImpl.class);
    RequestRepository requestRepository;
    ResponseRepository responseRepository;
    Cache cache;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, ResponseRepository responseRepository,
                              Cache cache) {
        this.requestRepository = requestRepository;
        this.responseRepository = responseRepository;
        this.cache = cache;
    }

    @Override
    public List<Long> addData(InputList list,long procId) {
        ArrayList<Long> answers = new ArrayList<>();
        list.getParams().stream().forEach(value->{
            logger.debug("add data to db");
            ComplexNumber complexNumber = new ComplexNumber(value.getReal(), value.getImaginary());
            Response response = new Response(complexNumber.getComplexNumber(),
                    complexNumber.getModule(), complexNumber.getPhase());
            responseRepository.save(response);
            answers.add(response.getId());
            Request request = new Request(value.getReal(), value.getImaginary());
            request.setResponse(response);
            request.setProcId(procId);
            requestRepository.save(request);
            cache.add(new InputParam(value.getReal(), value.getImaginary()), 0);

        });
        return answers;
    }

    @Override
    public List<Request> getDataById(long id) {
        return requestRepository.findByProcId(id);
    }

    @Override
    public List<Request> getAllRequests() {
        return (List<Request>)requestRepository.findAll();
    }

    @Override
    public Response getResponse(long id){
        return responseRepository.findById(id).get();
    }


    private void addDBDataToCache(){
        List<Request> list = getAllRequests();
        list.stream().forEach(value->{
            cache.add(new InputParam(value.getRealPart(), value.getImaginaryPart()), 0);
        });
    }

    @Override
    public String calculateId(InputList list) {
        Random random = new Random();
        long procId = random.nextInt(5000);
        Callable task = () -> {
            return addData(list, procId);
        };
        FutureTask<List<Long>> future = new FutureTask<>(task);
        new Thread(future).start();
        return "Your request is available on id: " + procId;
    }
}
