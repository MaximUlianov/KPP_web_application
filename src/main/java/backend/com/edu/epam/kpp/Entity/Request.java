package backend.com.edu.epam.kpp.Entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long procId;
    private double imaginaryPart;
    private double realPart;


    @OneToOne(fetch = FetchType.EAGER)
    private Response response;

    public Request() {
    }

    public Request(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public long getProcId() {
        return procId;
    }

    public void setProcId(long procId) {
        this.procId = procId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                Double.compare(request.realPart, realPart ) == 0 &&
                Double.compare(request.imaginaryPart, imaginaryPart) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", real=" + realPart +
                ", imaginary=" + imaginaryPart +
                ", response=" + response +
                '}';
    }
}
