package pl.sdacademy.cardealer.model;


import org.springframework.ui.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Car extends BaseModelVersion {


    private String VIN;

    @ManyToOne
    @JoinColumn(name = "production_year_id")
    private ProductionYear productionYear;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model;

    @Column(name = "OC_number")
    private String OcNumber;

    @Column(name = "register_number")
    private String RegisterNumber;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;

    private int Mileage;

    private String engine;

    private String power;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    private String descritpion;

    private int test_drives;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType carType;

    public Car() {
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public ProductionYear getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(ProductionYear productionYear) {
        this.productionYear = productionYear;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getOcNumber() {
        return OcNumber;
    }

    public void setOcNumber(String ocNumber) {
        OcNumber = ocNumber;
    }

    public String getRegisterNumber() {
        return RegisterNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        RegisterNumber = registerNumber;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public int getMileage() {
        return Mileage;
    }

    public void setMileage(int mileage) {
        Mileage = mileage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public int getTest_drives() {
        return test_drives;
    }

    public void setTest_drives(int test_drives) {
        this.test_drives = test_drives;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
