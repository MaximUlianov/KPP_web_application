package backend.com.edu.epam.kpp.Controller;

import backend.com.edu.epam.kpp.Service.ComplexNumberService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ComplexNumberController.class, ComplexNumberService.class})
public class ComplexNumberControllerTest {

    private MockMvc mvc;

    @Autowired
    private ComplexNumberService service;

    @Before
    public void set(){
        this.mvc = standaloneSetup(new ComplexNumberController(service)).build();
    }


    @Test
    public void complexNumber() throws Exception {
        mvc.perform(get("/api/complexNumber?real=50&imaginary=6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getByParts() throws Exception{
        mvc.perform(get("/api/complexNumber/getByParts?real=50&imaginary=6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test200() throws Exception{
        int status = mvc.perform(get("/api/complexNumber?real=50&imaginary=6"))
                .andReturn().getResponse().getStatus();
        Assertions.assertThat(status).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    public void test400_1() throws Exception{
        int status = mvc.perform(get("/api/complexNumber")
                .param("real","jdf").param("imaginary",""))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void test400_2() throws Exception{
        int status = mvc.perform(get("/api/complexNumber")
                .param("real","").param("imaginary","a"))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void test400_3() throws Exception{
        int status = mvc.perform(get("/api/complexNumber")
                .param("real","dfgf").param("imaginary","agfdgdf"))
                .andReturn().getResponse().getStatus();

        Assertions.assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}