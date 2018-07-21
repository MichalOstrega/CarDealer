package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
import pl.sdacademy.cardealer.dto.CarDto;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.CustomerService;
import pl.sdacademy.cardealer.services.DictionaryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarDataController {

    private CarDataService carDataService;
    private DictionaryService dictionaryService;
    private CustomerService customerService;

    public CarDataController(CarDataService carDataService, DictionaryService dictionaryService) {
        this.carDataService = carDataService;
        this.dictionaryService = dictionaryService;
    }



    @GetMapping("/all")
    public String showAllCars(Model model) {
        List<Car> cars = carDataService.loadAllCars();
        model.addAttribute("headerMsg","All Cars");
        model.addAttribute("cars", cars);
        return "vehicles";
    }

    @GetMapping
    public String showAvailableCars(Model model) {
        List<Car> cars = carDataService.loadAllAvailableCars();
        model.addAttribute("headerMsg","Car For Sale");
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


    @GetMapping("/new")
    public String addCarForm(Model model,
                             @RequestParam(value = "transaction", required = false) boolean reqTransaction,
                             @RequestParam(value = "customer", required = false) Long customerId) {
        CarDto carDto = new CarDto();
        carDto.setTransactionRequest(reqTransaction);
        carDto.setCustomerId(customerId);
        carDto.setDropList(getDropList());
        carDto.setCar( new Car());

        model.addAttribute("carDto", carDto);
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
            @Valid @ModelAttribute("carDto") CarDto carDto,
            BindingResult bindingResult,
            Model model) {

        carDto.setDropList(getDropList());

        Car carToSave = carDto.getCar();
        if (bindingResult.hasErrors()) {
            bindingResult.setNestedPath("car");
            model.addAttribute(carDto);
            bindingResult.setNestedPath("");
            return "addCar";
        }
        if (carDataService.loadCarByVIN(carToSave.getVin()) != null) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car cannot be sold Twice");
            model.addAttribute(carDto);
            bindingResult.setNestedPath("");
            return "addCar";
        }

        if(carDto.isTransactionRequest() == true){
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setCar(carToSave);
            if (carDto.getCustomerId() != null) {
                transactionDto.setCustomer(customerService.findById(carDto.getCustomerId()));
                transactionDto.setCustomerExist(true);
            }
            transactionDto.setCarExist(true);
            model.addAttribute("transactionDto", transactionDto);
            return "addTransaction";
        }

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
