package comons;

import java.util.Random;

public class BaseTest {
    protected int randomEmail() {
        return new Random().nextInt(9999);
    }
}
