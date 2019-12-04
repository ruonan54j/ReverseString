package com.test.reversestring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("reverse")
@RestController
public class ReverseController {

    /** 
    * @param  string path variable passed in through url
    * @return if string is not empty, returns the input string in reverse
    * @throws RuntimeException if string is empty 
    */
    @GetMapping(path = "{string}")
    public String reverseString(@PathVariable("string") String string){
        String trimString = string.trim();

        if(trimString.length() == 0) {
            throw new RuntimeException("String can not be empty");
        }

        return new StringBuilder(trimString).reverse().toString();
    }

}
