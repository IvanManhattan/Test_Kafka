package com.test.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyRateHistory {
    @JsonProperty("Cur_ID")
    private int id;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Cur_OfficialRate")
    private String currOfficialRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrOfficialRate() {
        return currOfficialRate;
    }

    public void setCurrOfficialRate(String currOfficialRate) {
        this.currOfficialRate = currOfficialRate;
    }

    @Override
    public String toString() {
        return "CurrencyRateHistory{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", currOfficialRate='" + currOfficialRate + '\'' +
                '}';
    }
}
