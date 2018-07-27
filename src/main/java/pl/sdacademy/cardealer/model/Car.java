package pl.sdacademy.cardealer.model;




import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Car extends BaseModelVersion {


    @NotNull
    @Size(min=17, max=17, message = "Input valid VIN - 17 characters")
    @Column(unique = true)
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
    @Size(min=1, max=50, message = "Input OC Number")
    @Column(name = "OC_number")
    private String ocNumber;


    @NotNull
    @Size(min=1, max=50, message = "Input Register Number")
    @Column(name = "register_number")
    private String registerNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;


    @NotNull
    @Min(1)
    @Max(1000000)
    private int mileage;


    @NotNull
    @Size(min=1, max=50, message = "Input Engine Type")
    private String engine;

    @NotNull
    @Size(min=1, max=50, message = "Input Power")
    private String power;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    @NotNull
    @Size(min=1, max=1000, message = "Input description")
    private String description;

    @Column(name = "test_drives")
    private int testDrives;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType carType;


    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_history_id")
    private PriceHistory priceHistory;


    private boolean sold;


    private boolean visible;



    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer customer;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private List<Photo> photos;

    public Car() {
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public PriceHistory getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(PriceHistory priceHistory) {
        this.priceHistory = priceHistory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public int getTestDrives() {
        return testDrives;
    }

    public void setTestDrives(int testDrives) {
        this.testDrives = testDrives;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
