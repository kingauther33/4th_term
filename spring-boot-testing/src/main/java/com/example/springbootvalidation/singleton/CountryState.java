package com.example.springbootvalidation.singleton;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CountryState {
    List<String> getStates(String country);
    public void setStates(String country, List<String> states);
}
