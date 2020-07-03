
package org.nguyen.orderjava.models;

import java.util.Date;
import java.util.List;

public class OrderUpdateData {
    private List<OrderContentData> beanAdditions;
    private List<OrderContentData> beanDeletions;
    private List<OrderContentData> beanUpdates;
    private Date changeDate;

    public List<OrderContentData> getBeanAdditions() {
        return beanAdditions;
    }

    public void setBeanAdditions(List<OrderContentData> beanAdditions) {
        this.beanAdditions = beanAdditions;
    }

    public List<OrderContentData> getBeanDeletions() {
        return beanDeletions;
    }

    public void setBeanDeletions(List<OrderContentData> beanDeletions) {
        this.beanDeletions = beanDeletions;
    }

    public List<OrderContentData> getBeanUpdates() {
        return beanUpdates;
    }

    public void setBeanUpdates(List<OrderContentData> beanUpdates) {
        this.beanUpdates = beanUpdates;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
