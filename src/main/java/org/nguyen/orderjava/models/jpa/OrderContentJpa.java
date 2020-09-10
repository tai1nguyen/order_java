package org.nguyen.orderjava.models.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ORDER_CONTENT")
public class OrderContentJpa {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Column(name = "BEAN_TYPE")
    private String beanType;

    @Column(name = "QUANTITY")
    private String quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderContentJpa) {
            OrderContentJpa suspect = (OrderContentJpa) o;
            return isEveryPropertyEqual(suspect);
        }
        else {
            return false;
        }
    }

    private boolean isEveryPropertyEqual(OrderContentJpa suspect) {
        return isEqual(this.id, suspect.getId()) &&
            isEqual(this.beanType, suspect.getBeanType()) &&
            isEqual(this.quantity, suspect.getQuantity());
    }

    private boolean isEqual(Object expected, Object suspect) {
        if (expected != null) {
            return expected.equals(suspect);
        }
        else {
            return expected == suspect;
        }
    }
}
