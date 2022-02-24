package com.example.springbootvalidation.singleton;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CountryStateContainerManagedBean implements CountryState {

    private final HashMap<String, List<String>> countryStatesMap = new HashMap<>();

    @PostConstruct
    public void initialize() {
        List<String> states = new ArrayList<String>();
        states.add("Texas");
        states.add("Alabama");
        states.add("Alaska");
        states.add("Arizona");
        states.add("Arkansas");

        countryStatesMap.put("UnitedStates", states);
    }

    @Override
    @Lock(LockType.READ)
    public List<String> getStates(String country) {
        return countryStatesMap.get(country);
    }

    @Override
    @Lock(LockType.WRITE)
    public void setStates(String country, List<String> states) {
        countryStatesMap.put(country, states);
    }
}
