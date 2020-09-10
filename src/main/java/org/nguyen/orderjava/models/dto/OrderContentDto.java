package org.nguyen.orderjava.models.dto;

import org.nguyen.orderjava.models.BeanTypeEnum;

public class OrderContentDto {
    private BeanTypeEnum beanType;
    private Integer quantity;

    public BeanTypeEnum getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanTypeEnum beanType) {
        this.beanType = beanType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderContentDto) {
            OrderContentDto suspect = (OrderContentDto) o;
            return isEveryPropertyEqual(suspect);
        }
        else {
            return false;
        }
    }

    private boolean isEveryPropertyEqual(OrderContentDto suspect) {
        return isEqual(this.beanType, suspect.getBeanType()) &&
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