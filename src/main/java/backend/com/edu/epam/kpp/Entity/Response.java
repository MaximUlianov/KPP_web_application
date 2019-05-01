package backend.com.edu.epam.kpp.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "response")
public class Response {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String complexNumber;
    private double module;
    private double phase;

    public Response() {
    }

    public Response(String complexNumber, double module, double phase) {
        this.complexNumber = complexNumber;
        this.module = module;
        this.phase = phase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComplexNumber() {
        return complexNumber;
    }

    public void setComplexNumber(String complexNumber) {
        this.complexNumber = complexNumber;
    }

    public double getModule() {
        return module;
    }

    public void setModule(double module) {
        this.module = module;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return id == response.id &&
                Double.compare(response.module, module) == 0 &&
                Double.compare(response.phase, phase) == 0 &&
                Objects.equals(complexNumber, response.complexNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, complexNumber, module, phase);
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", complexNumber='" + complexNumber + '\'' +
                ", module=" + module +
                ", phase=" + phase +
                '}';
    }
}
