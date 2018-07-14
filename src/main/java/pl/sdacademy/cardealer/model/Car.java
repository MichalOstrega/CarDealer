package pl.sdacademy.cardealer.model;


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



}
