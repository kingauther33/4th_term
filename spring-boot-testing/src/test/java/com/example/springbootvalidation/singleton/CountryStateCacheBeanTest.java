package com.example.springbootvalidation.singleton;

import org.junit.Before;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryStateCacheBeanTest {
    private EJBContainer ejbContainer = null;

    private Context context = null;

    @Before
    public void init() {
        ejbContainer = EJBContainer.createEJBContainer();
        context = ejbContainer.getContext();
    }

    @Test
    public void whenCallGetStatesFromContainerManagedBean_ReturnsStatesForCountry() throws Exception {
        String[] expectedStates = {"Texas", "Alabama", "Alaska", "Arizona", "Arkansas"};

        CountryState countryStateBean = (CountryState) context
                .lookup("java:com/example/springbootvalidation/singleton/CountryStateContainerManagedBean");
        List<String> actualStates = countryStateBean.getStates("UnitedStates");

        assertNotNull(actualStates);
        assertArrayEquals(expectedStates, actualStates.toArray());
    }
}
