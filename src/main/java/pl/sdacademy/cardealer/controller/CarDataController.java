package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.DictionaryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarDataController {

    private CarDataService carDataService;
    private DictionaryService dictionaryService;

    public CarDataController(CarDataService carDataService, DictionaryService dictionaryService) {
        this.carDataService = carDataService;
        this.dictionaryService = dictionaryService;
    }



    @GetMapping
    public String showAvailableCars(Model model) {
        List<Car> cars = carDataService.loadAllAvailableCars();
        model.addAttribute("headerMsg","Car For Sale");
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping("/all")
    public String showAllCars(Model model) {
        List<Car> cars = carDataService.loadAllCars();
        model.addAttribute("headerMsg","All Cars");
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping("/sold")
    public String showAllSoldCars(Model model) {
        List<Car> cars = carDataService.loadAllSoldCars();
        model.addAttribute("headerMsg","Sold Cars");
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping("/{id}/sell")
    public String sellVehicle(@PathVariable("id") Long vehId, Model model){

        Car car = carDataService.loadCarById(vehId);
        car.setSold(true);
        carDataService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/new")
    public String addCarForm(Model model) {
        AddCarDropDownListDto addCarDropDownListDto = getDropList();
        model.addAttribute("dropList", addCarDropDownListDto);
        Car attributeValue = new Car();

        model.addAttribute("addedCar", attributeValue);
        return "addCar";
    }

    @GetMapping("/{id}")
    public String showCarDetails(Model model, @PathVariable("id") Long carID) {
        Car car = carDataService.loadCarById(carID);
        if (car == null) {
                return "redirect:/";
        }
        model.addAttribute("car", car);
        return "carDetails";
    }

    @PostMapping
    public String saveVehicle(
            @Valid @ModelAttribute("addedCar") Car carToSave,
            BindingResult bindingResult,
            Model model) {

        AddCarDropDownListDto dropList = getDropList();

        if (bindingResult.hasErrors()) {
            model.addAttribute("dropList", dropList);
            model.addAttribute("addedCar", carToSave);
            return "addCar";
        }
        if (carDataService.loadCarByVIN(carToSave.getVin()) != null) {
            bindingResult.rejectValue("vin", "vin", "Car cannot be sold Twice");
            model.addAttribute("dropList", dropList);
            model.addAttribute("addedCar", carToSave);
            return "addCar";
        }
        carDataService.addCar(carToSave);

        return "redirect:/cars";
    }

    private AddCarDropDownListDto getDropList() {
        AddCarDropDownListDto addCarDropDownListDto=AddCarDropDownListDto.getInstance();
        addCarDropDownListDto.setBrands(dictionaryService.getBrands());
        addCarDropDownListDto.setCarModels(dictionaryService.getCarModels());
        addCarDropDownListDto.setCarTypes(dictionaryService.getCarTypes());
        addCarDropDownListDto.setFuels(dictionaryService.getFuels());
        addCarDropDownListDto.setProductionYears(dictionaryService.getProductionYear());
        addCarDropDownListDto.setTransmissions(dictionaryService.getTransmission());
        return addCarDropDownListDto;
    }


}
