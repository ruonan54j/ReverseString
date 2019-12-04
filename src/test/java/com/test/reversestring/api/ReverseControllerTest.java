package com.test.reversestring.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReverseControllerTest {
    private MockMvc mockMvc;
    private ReverseController reverseController;

    @Before
    public void setUp() {
        reverseController = new ReverseController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(reverseController).build();
    }

    @Test
    public void reverseString() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "orangepeel"))
                .andExpect(status().isOk())
                .andExpect(content().string("leepegnaro"));
    }

    //test case: string is empty
    @Test
    public void reverseStringEmpty() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", ""))
                .andExpect(status().isNotFound());
    }

    //test case: string is made up of only spaces, expects exception to be thrown
    @Test(expected = Exception.class)
    public void reverseStringEmptySpaces() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "     "));
    }

    //test case: string contains special characters
    @Test
    public void reverseStringSpecialChars() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "!123abc--^^"))
                .andExpect(status().isOk())
                .andExpect(content().string("^^--cba321!"));
    }

    //test case: string of numbers
    @Test
    public void reverseStringIntegers() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(content().string("0987654321"));
    }

    //test case: string contains spaces
    @Test
    public void reverseStringWithSpaces() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "inserting multiple spaces into the sentence "))
                .andExpect(status().isOk())
                .andExpect(content().string("ecnetnes eht otni secaps elpitlum gnitresni"));
    }

    //test case: string is list of repeating characters
    @Test
    public void reverseStringRepeatChar() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "aaaaaaaaaa"))
                .andExpect(status().isOk())
                .andExpect(content().string("aaaaaaaaaa"));
    }

    //test case: string is a palindrome
    @Test
    public void reverseStringPalindrome() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "racecar"))
                .andExpect(status().isOk())
                .andExpect(content().string("racecar"));
    }

    //test case: string is very long
    @Test
    public void reverseStringLong() throws Exception {
        this.mockMvc.perform(get("/reverse/{string}", "gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds gurwfsfndsjfdsnfjdsfndjfnds"))
                .andExpect(status().isOk())
                .andExpect(content().string("sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug sdnfjdnfsdjfnsdfjsdnfsfwrug"));
    }
}