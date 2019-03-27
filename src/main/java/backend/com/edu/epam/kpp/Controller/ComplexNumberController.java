package backend.com.edu.epam.kpp.Controller;

import backend.com.edu.epam.kpp.Entity.ComplexNumber;

import backend.com.edu.epam.kpp.Service.ComplexNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/complexNumber")
public class ComplexNumberController {

    ComplexNumberService service;

    @Autowired
    public ComplexNumberController(ComplexNumberService service){
        this.service = service;
    }
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<ComplexNumber> complexNumber( @RequestParam(name = "real",defaultValue = "0",required = false) String real,
                                                        @RequestParam(name = "imaginary", defaultValue = "0",required = false) String imaginary) {

        String tmp = service.checkParameters(real,imaginary);
        if(tmp.equals("real")){
            return ResponseEntity.badRequest().body(new ComplexNumber("Incorrect 'real' input"));
        }
        else if(tmp.equals("imaginary")){
            return ResponseEntity.badRequest().body(new ComplexNumber("Incorrect 'imaginary' input"));
        }
        return ResponseEntity.ok(service.buildNum(real, imaginary));
    }

    @RequestMapping(value = "/getByParts", method = RequestMethod.GET)
    public ResponseEntity<ComplexNumber> getByParts( @RequestParam(name = "real",defaultValue = "0",required = false) String real,
                                                        @RequestParam(name = "imaginary", defaultValue = "0",required = false) String imaginary){
        ComplexNumber num = service.getByParts(Double.parseDouble(real), Double.parseDouble(imaginary));
        return ResponseEntity.ok(num);
    }

}
