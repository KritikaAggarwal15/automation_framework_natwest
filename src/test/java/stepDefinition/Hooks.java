package stepDefinition;

import com.natWest.webService.customListeners.CustomListener;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends CustomListener {

    @Before
    public void init() {

    }

    @After
    public void cleanData() {
        actualResponse = null;


    }


}
