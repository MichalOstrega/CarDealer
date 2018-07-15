package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/all")
    public String showAllCars(Model model) {
        List<Car> cars = carDataService.loadAllCars();
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping("/sold")
    public String showAllSoldCars(Model model) {
        List<Car> cars = carDataService.loadAllSoldCars();
        model.addAttribute("cars", cars);
        return "vehicles";
    }

//    @GetMapping("/{id}/sell")
//    public String sellVehicle(@PathVariable("id") Long vehId, Model model){
//
//        PurchaseFormData attributeValue = new PurchaseFormData();
//        attributeValue.setPrice(carDataService.getById(vehId).getPrice());
//        model.addAttribute("purchaseData", attributeValue);
//        model.addAttribute("vehicleId", vehId);
//        return "sellForm";
//    }

    @GetMapping("/new")
    public String addCarForm(Model model) {
        AddCarDropDownListDto addCarDropDownListDto = AddCarDropDownListDto.getInstance();
        setFields(addCarDropDownListDto);
        model.addAttribute("dropList", addCarDropDownListDto);
        Car attributeValue = new Car();

        model.addAttribute("addedCar", attributeValue);
        return "addCar";
    }

    @GetMapping("/{id}")
    public String showCarDetails(Model model, @PathVariable("id") Long carID) {
        Car car = carDataService.loadCarById(carID);
        model.addAttribute("car", car);
        return "carDetails";
    }

    @PostMapping
    public String saveVehicle(@Valid @ModelAttribute("addedCar") Car carToSave, BindingResult bindingResult, @ModelAttribute("dropList") AddCarDropDownListDto dropList) {

        dropList=AddCarDropDownListDto.getInstance();

        if (bindingResult.hasErrors()) {

            return "addCar";
        }
        if (carDataService.loadCarByVIN(carToSave.getVin()) != null) {
            bindingResult.rejectValue("vin", "vin", "Car cannot be sold Twice");
            return "addCar";
        }
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
