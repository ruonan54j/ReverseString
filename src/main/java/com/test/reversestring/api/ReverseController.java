package com.test.reversestring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("reverse")
@RestController
public class ReverseController {

    //reverse a string passed by URL
    @GetMapping(path = "{string}")
    public String reverseString(@PathVariable("string") String string){
        if(string.trim().length() == 0) {
            throw new RuntimeException("String can not be empty");
        }

        return new StringBuilder(string).reverse().toString();
    }

}
