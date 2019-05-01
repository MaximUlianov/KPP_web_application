package backend.com.edu.epam.kpp.Entity;

import lombok.Data;

import java.util.ArrayList;

public class ResponseArray {

    private ArrayList<ComplexNumber> complexNumbers;
    private long amountOfParameters;
    private long notValidParameters;
    private ComplexNumber max;
    private ComplexNumber min;
    private ComplexNumber mostPopularRequest;


    public ResponseArray() {
        this.complexNumbers = new ArrayList<>();
    }

    public ArrayList<ComplexNumber> getComplexNumbers() {
        return complexNumbers;
    }

    public void setComplexNumbers(ArrayList<ComplexNumber> complexNumbers) {
        this.complexNumbers = complexNumbers;
    }

    public long getAmountOfParameters() {
        return amountOfParameters;
    }

    public void setAmountOfParameters(long amountOfParameters) {
        this.amountOfParameters = amountOfParameters;
    }

    public long getNotValidParameters() {
        return notValidParameters;
    }

    public void setNotValidParameters(long notValidParameters) {
        this.notValidParameters = notValidParameters;
    }

    public ComplexNumber getMax() {
        return max;
    }

    public void setMax(ComplexNumber max) {
        this.max = max;
    }

    public ComplexNumber getMin() {
        return min;
    }

    public void setMin(ComplexNumber min) {
        this.min = min;
    }

    public ComplexNumber getMostPopularRequest() {
        return mostPopularRequest;
    }

    public void setMostPopularRequest(ComplexNumber mostPopularRequest) {
        this.mostPopularRequest = mostPopularRequest;
    }

    public void addNum(ComplexNumber complexNumber){
        this.complexNumbers.add(complexNumber);
    }
}
