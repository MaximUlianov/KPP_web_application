package backend.com.edu.epam.kpp.Repository;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;

public interface ComplexNumberRepository {
    ComplexNumber getByParts(double real, double img);
}
