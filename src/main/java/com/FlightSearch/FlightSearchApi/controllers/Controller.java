package com.FlightSearch.FlightSearchApi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/AssBoardsController")
public class Controller {

    @GetMapping("/getAllData")
    public String getAllData(){
        return "merve";
    }
}
