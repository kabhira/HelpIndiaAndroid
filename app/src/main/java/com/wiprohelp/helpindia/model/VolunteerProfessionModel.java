package com.wiprohelp.helpindia.model;

import com.wiprohelp.helpindia.utilities.SelectOptionAlert;

/**
 * Created by AB335009 on 1/5/2016.
 */
public class VolunteerProfessionModel extends SelectOptionAlert.SelectOptionData  {

    private String id;
    private String name;

    public VolunteerProfessionModel(String optionName){
        super(optionName);
        name = optionName;
    }
}
