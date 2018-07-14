package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.DefaultDictionaryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarDataController {

    private CarDataService carDataService;
    private DefaultDictionaryService defaultDictionaryService;

    public CarDataController(CarDataService carDataService, DefaultDictionaryService defaultDictionaryService) {
        this.carDataService = carDataService;
        this.defaultDictionaryService = defaultDictionaryService;
    }

    @GetMapping
    public String showAvailableCars(Model model) {
        List<Car> cars = carDataService.loadAllAvailableCars();
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping("/new")
    public String addCarForm(Model model) {
        AddCarDropDownListDto addCarDropDownListDto = new AddCarDropDownListDto();
        setFields(addCarDropDownListDto);
        model.addAttribute("dropList", addCarDropDownListDto);
        model.addAttribute("addedCar",new Car());
        return "addCar";
    }

    @PostMapping
    public String saveVehicle(@Valid @ModelAttribute("addedCar") Car carToSave) {
        carDataService.addCar(carToSave);

        return "redirect:/cars";
    }

    private void setFields(AddCarDropDownListDto addCarDropDownListDto) {
        addCarDropDownListDto.setBrands(defaultDictionaryService.getBrands());
        addCarDropDownListDto.setCarModels(defaultDictionaryService.getCarModels());
        addCarDropDownListDto.setCarTypes(defaultDictionaryService.getCarTypes());
        addCarDropDownListDto.setFuels(defaultDictionaryService.getFuels());
        addCarDropDownListDto.setProductionYears(defaultDictionaryService.getProductionYear());
        addCarDropDownListDto.setTransmissions(defaultDictionaryService.getTransmission());
    }
}
