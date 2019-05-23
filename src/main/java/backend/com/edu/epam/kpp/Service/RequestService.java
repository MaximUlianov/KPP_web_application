package backend.com.edu.epam.kpp.Service;

import backend.com.edu.epam.kpp.Entity.InputList;
import backend.com.edu.epam.kpp.Entity.Request;
import backend.com.edu.epam.kpp.Entity.Response;

import java.util.List;

public interface RequestService {
    List<Long> addData(InputList list, long procId);
    List<Request> getDataById(long id);
    List<Request> getAllRequests();
    Response getResponse(long id);
    String calculateId(InputList list);
}
