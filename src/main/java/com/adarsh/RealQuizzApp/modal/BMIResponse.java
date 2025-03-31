package com.adarsh.RealQuizzApp.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;

@Entity
public class BMIResponse {

    @Id
    private Integer Id;
    private Integer UsageCount;

    public BMIResponse(Integer id, Integer usageCount) {
        Id = id;
        UsageCount = usageCount;
    }

    public BMIResponse() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUsageCount() {
        return UsageCount;
    }

    public void setUsageCount(Integer usageCount) {
        UsageCount = usageCount;
    }

    @Override
    public String toString() {
        return "BMIResponse{" +
                "Id=" + Id +
                ", UsageCount=" + UsageCount +
                '}';
    }
//    @Transactional
//    public void sjj(){}

}
