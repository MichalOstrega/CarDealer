package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.model.Brand;
import pl.sdacademy.cardealer.model.CarModel;
import pl.sdacademy.cardealer.services.DictionaryService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dict")
public class DictionaryController {

    DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/brands/new")
    public String addBrandForm(Model model) {
        Brand attributeValue = new Brand();

        model.addAttribute("newBrand", attributeValue);
        return "addBrand";
    }

    @PostMapping("/brands")
    public String saveBrand(
            @Valid @ModelAttribute("newBrand") Brand brand,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brand", brand);
            return "addBrand";
        }

        dictionaryService.addBrand(brand);

        return "redirect:/";
    }

    @GetMapping("/models/new")
    public String addModelForm(Model model) {
        List<Brand> brands = dictionaryService.getBrands();
        CarModel carModel = new CarModel();

        model.addAttribute("brands", brands);
        model.addAttribute("carModel", carModel);
        return "addCarModel";
    }

    @PostMapping("/models")
    public String saveModel(
            @Valid @ModelAttribute("carModel") CarModel carModel,
            BindingResult bindingResult,
            Model model) {



        if (bindingResult.hasErrors()) {
            model.addAttribute("carModel", carModel);
            return "addCarModel";
        }

        String[] splitted;
        splitted =        carModel.getName().split(",");
        List<String> strings = Arrays.asList(splitted);
        List<CarModel> collect = strings.stream().map(s -> new CarModel(s)).collect(Collectors.toList());
        collect.stream().forEach(carModel1 -> carModel1.setBrand(carModel.getBrand()));


        dictionaryService.addModel(collect);

        return "redirect:/";
    }

    @GetMapping("/models/all")
    @ResponseBody
    public List<CarModel> getModels(@RequestParam("brand") Long brandCarId) {
        List<CarModel> carModels= dictionaryService.getCarModels(brandCarId);
        return carModels;
    }


}
