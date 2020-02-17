package org.nguyen.orderjava.models.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
public class BeanEntry {

    @Id
    @Column(name="BEAN_TYPE")
    private String beanType;

    @Column(name="WEIGHT_PER_UNIT")
    private String weightPerUnit;

    @Column(name="PRICE_PER_UNIT")
    private String pricePerUnit;

    @Column(name="QUANTITY")
    private String quantity;

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public String getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(String weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
		this.quantity = quantity;
    }
}
