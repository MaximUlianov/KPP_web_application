package backend.com.edu.epam.kpp.Entity;

import java.util.Objects;

public class ComplexNumber {
    private String errMsg;
    private String complexNumber;
    private double real;
    private double imaginary;
    private double phase;
    private double module;



    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        this.phase = calculatePhase();
        this.module = calculateModule();
        this.complexNumber = setComplexNumber();
    }

    public ComplexNumber(String errMsg) {
        this.errMsg = errMsg;
    }

    public ComplexNumber() {

    }

    public void setReal(double real) {
        this.real = real;
        setComplexNumber();
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
        setComplexNumber();
    }

    public double getPhase() {
        return phase;
    }

    public double getModule() {
        return module;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public String setComplexNumber()
    {
        String temp;
        if(this.imaginary >= 0){
            temp = "%s + %si";
        }
        else{
            temp = "%s %si";
        }
        return String.format(temp,Double.toString(real), Double.toString(imaginary));
    }

    public String getComplexNumber() {
        return complexNumber;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public double calculatePhase(){
        phase = Math.atan(imaginary/real);
        return phase;
    }

    public double calculateModule(){
        module = Math.sqrt(Math.pow(real,2) + Math.pow(imaginary, 2));
        return module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.real, real) == 0 &&
                Double.compare(that.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
