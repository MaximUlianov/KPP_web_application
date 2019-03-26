package backend.com.edu.epam.kpp.Service;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComplexNumberServiceTest {


    ComplexNumberService service;

    @Before
    public void init() {
        service = new ComplexNumberService();
    }
    @Test
    public void checkParameters() {
        String expected = service.checkParameters("10","10");
        String actual = "none";

        Assert.assertEquals(expected, actual);

        Assert.assertSame(expected,actual);
    }

    @Test
    public void buildNum() {
        ComplexNumber expected = service.buildNum("10","10");
        ComplexNumber actual = new ComplexNumber(10,10);

        Assert.assertEquals(expected, actual);

        expected = service.buildNum("2","2");
        actual.setReal(2);
        actual.setImaginary(2);

        Assert.assertEquals(expected, actual);
    }
}