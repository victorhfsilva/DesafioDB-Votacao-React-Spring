import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import util.DriverFactory;

import static util.DriverFactory.driver;

public class BaseTest {

    private static final String url = "http://localhost:5173/";

    @BeforeEach
    public void setup() {
        DriverFactory.setDriver(url);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
