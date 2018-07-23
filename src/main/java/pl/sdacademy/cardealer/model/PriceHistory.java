package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class PriceHistory extends BaseModelVersion {

    @ManyToOne
    @JoinColumn(name = "get_car_contract_id")
    public Contract acquireCarContract;

    @Column(name = "get_car_price")
    public Long acquireCarPrice=0l;

    @ManyToOne
    @JoinColumn(name = "sell_car_contract_id")
    public Contract sellCarContract;

    @Column(name = "sell_car_price")
    public Long sellCarPrice =0l;

    @NotNull
    @Min(value = 5000, message = "Price must be greater than 5000")
    @Column(name = "for_sale_price")
    public Long forSalePrice;

    public PriceHistory() {
    }

    public Contract getAcquireCarContract() {
        return acquireCarContract;
    }

    public void setAcquireCarContract(Contract acquireCarContract) {
        this.acquireCarContract = acquireCarContract;
    }

    public Long getAcquireCarPrice() {
        return acquireCarPrice;
    }

    public void setAcquireCarPrice(Long acquireCarPrice) {
        this.acquireCarPrice = acquireCarPrice;
    }

    public Contract getSellCarContract() {
        return sellCarContract;
    }

    public void setSellCarContract(Contract sellCarContract) {
        this.sellCarContract = sellCarContract;
    }

    public Long getSellCarPrice() {
        return sellCarPrice;
    }

    public void setSellCarPrice(Long sellCarPrice) {
        this.sellCarPrice = sellCarPrice;
    }

    public Long getForSalePrice() {
        return forSalePrice;
    }

    public void setForSalePrice(Long forSalePrice) {
        this.forSalePrice = forSalePrice;
    }
}
