package com.app.eHealthBuddy.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeLine {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Long  deaths;
    private Long  confirmed;
    private Long  recovered;
    private Long  new_confirmed;
    private Long  new_recovered;
    private Long  new_deaths;
}
