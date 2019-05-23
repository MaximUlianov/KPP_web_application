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
import java.util.Random;
import java.util.concurrent.*;


@RestController
@RequestMapping("/api/complexNumber/request")
public class RequestResponseController {

    private static final Logger logger = Logger.getLogger(RequestResponseController.class);


    @Autowired
    private RequestService requestService;

    @PostMapping
    @ResponseBody
    public ResponseEntity futureSequence(@RequestBody InputList list){
        Random random = new Random();
        long procId = random.nextInt(5000);
        Callable task = () -> {
            return requestService.addData(list, procId);
        };
        FutureTask<List<Long>> future = new FutureTask<>(task);
        new Thread(future).start();
        logger.debug("return");
        return ResponseEntity.ok("Your request is available on id: " + procId);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Request>> getDataById(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(requestService.getDataById(id));
    }
}
