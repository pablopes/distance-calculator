package br.com.pablopes.tcc.controller;


import br.com.pablopes.tcc.model.dto.CalculateRequest;
import br.com.pablopes.tcc.service.CalculateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    private CalculateService service;
    public CalculateController(CalculateService service){
        this.service = service;
    }

    @GetMapping
    public Object generate(@RequestBody CalculateRequest config){
       return service.execute(config);
    }
}
