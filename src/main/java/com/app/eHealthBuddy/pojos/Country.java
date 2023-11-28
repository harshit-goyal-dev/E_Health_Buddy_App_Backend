package com.app.eHealthBuddy.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String name;
    private String code;
    private Long population;
    private LatestData latestData;
    private List<TimeLine> timeline;

}
