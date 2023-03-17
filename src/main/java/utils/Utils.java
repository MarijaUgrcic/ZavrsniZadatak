package utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.annotations.DataProvider;

public class Utils {
    public static void waitForSeconds(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Dotenv dotEnv() {
        return Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }

    @DataProvider(name = "dpLoginTestNegativeCases")
    public static Object[][] dpLoginTestNegativeCases() {
        return new Object[][]{
                {"", ""},
                {"", "Password"},
                {"mail@mail.com", ""},
                {"mail@mail.com", "P"},
                {"nekimail", "Password"},
                {"mail", "P"},
                {"", "P"},
                {"nekimail", ""},
        };
    }

}