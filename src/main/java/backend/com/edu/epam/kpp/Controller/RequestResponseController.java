package backend.com.edu.epam.kpp.Controller;

import backend.com.edu.epam.kpp.Entity.InputList;
import backend.com.edu.epam.kpp.Entity.Request;
import backend.com.edu.epam.kpp.Entity.Response;
import backend.com.edu.epam.kpp.Service.RequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@RestController
@RequestMapping("/api/complexNumber/request")
public class RequestResponseController {

    private static final Logger logger = Logger.getLogger(RequestResponseController.class);


    @Autowired
    private RequestService requestService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<List<Request>> sequence(@RequestBody InputList list){
        requestService.addData(list);
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @PostMapping(value = "/future")
    @ResponseBody
    public ResponseEntity<List<Response>> futureSequence(@RequestBody InputList list){
        Callable task = () -> {
            return requestService.addData(list);
        };
        FutureTask<List<Long>> future = new FutureTask<>(task);
        new Thread(future).start();
        while(!future.isDone()){
            logger.debug("calculate result");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<Long> answers = new ArrayList<>();
        try {
            answers = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<Response> responses = new ArrayList<>();
        answers.forEach(value->{
            responses.add(requestService.getResponse(value));
        });
        return ResponseEntity.ok(responses);

    }
}
