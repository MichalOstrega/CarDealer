package pl.sdacademy.cardealer.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Car extends BaseModelVersion {

    @NotNull
    private String vin;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "production_year_id")
    private ProductionYear productionYear;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @NotNull
    @ManyToOne
    @JoinColumn(name="model_id")
    private CarModel model;

    @NotNull
    @Column(name = "OC_number")
    private String ocNumber;


    @NotNull
    @Column(name = "register_number")
    private String registerNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;


    @NotNull
    @Min(0)
    @Max(1000000)
    private int mileage;


    @NotNull
    private String engine;

    @NotNull
    private String power;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @NotNull
    private String description;


    private int test_drives;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType carType;

    @NotNull
    @Min(5000)
    private int price;


    private boolean sold;

    public Car() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public String getOcNumber() {
        return ocNumber;
    }

    public void setOcNumber(String ocNumber) {
        this.ocNumber = ocNumber;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
