
package org.nguyen.orderjava.models.dto;

import java.util.Date;
import java.util.List;

public class OrderUpdateDto {
    private List<OrderContentDto> beanAdditions;
    private List<OrderContentDto> beanDeletions;
    private List<OrderContentDto> beanUpdates;
    private Date changeDate;

    public List<OrderContentDto> getBeanAdditions() {
        return beanAdditions;
    }

    public void setBeanAdditions(List<OrderContentDto> beanAdditions) {
        this.beanAdditions = beanAdditions;
    }

    public List<OrderContentDto> getBeanDeletions() {
        return beanDeletions;
    }

    public void setBeanDeletions(List<OrderContentDto> beanDeletions) {
        this.beanDeletions = beanDeletions;
    }

    public List<OrderContentDto> getBeanUpdates() {
        return beanUpdates;
    }

    public void setBeanUpdates(List<OrderContentDto> beanUpdates) {
        this.beanUpdates = beanUpdates;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
