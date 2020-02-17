package org.nguyen.orderjava.models;

public class Bean {
    private Integer weight;
    private Bean.BeanType type;
    private Integer pricePerUnit;
    private Integer units;

    public enum BeanType {
        ARABICA("ARABICA"),
        ROBUSTA("ROBUSTA"),
        LIBERIAN("LIBERIAN"),
        EXCELSA("EXCELSA");

        private final String name;

        BeanType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static BeanType getType(String type) {
            for (BeanType beanType : BeanType.values()) {
                if (beanType.getName().equalsIgnoreCase(type)) {
                    return beanType;
                } 
            }

            return null;
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BeanType getType() {
        return type;
    }

    public void setType(BeanType type) {
        this.type = type;
    }

    public Integer getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Integer pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}
