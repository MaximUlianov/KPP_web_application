package backend.com.edu.epam.kpp.Repository;

import backend.com.edu.epam.kpp.Entity.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
}
