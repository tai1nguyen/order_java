
package org.nguyen.orderjava.models;

import java.util.Date;
import java.util.List;

public class OrderUpdate {
    private List<Bean> beans;
    private Date changeDate;

    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
