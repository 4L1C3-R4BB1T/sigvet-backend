package br.com.sigvet.sigvetapi.common.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping
public class SwaggerController {
    
    @Operation(hidden = true)
    @GetMapping
    public ModelAndView get() {
        return new ModelAndView("redirect:/swagger-ui/index.html");
    }
}
