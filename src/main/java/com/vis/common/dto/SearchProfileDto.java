package com.vis.common.dto;

import java.util.List;

public class SearchProfileDto {
    private String term;
    private List<CrimeProfileDto> crimeProfileDtoList;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<CrimeProfileDto> getCrimeProfileDtoList() {
        return crimeProfileDtoList;
    }

    public void setCrimeProfileDtoList(List<CrimeProfileDto> crimeProfileDtoList) {
        this.crimeProfileDtoList = crimeProfileDtoList;
    }
}
