package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.services.CarDataService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    CarDataService carDataService;

    @Autowired
    public WelcomeController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @RequestMapping(value = {"/"})
    public String homePage(Model model) {
        List<Car> cars = carDataService.loadAllCars();
        Collections.shuffle(cars);

        model.addAttribute("cars", cars.stream().limit(3).collect(Collectors.toList()));

        return "home";
    }
    @RequestMapping(value = {"/login"})
    public String loginPage(Model model) {


        return "login";
    }

    @RequestMapping(value = {"/access-denied"})
    public String accessDenied(Model model) {


        return "access-denied";
    }


}
