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

    @DataProvider(name = "dpLoginTestUsernameInvalid")
    public static Object[][] dpLoginTestUsernameInvalid() {
        return new Object[][]{
                {"nekimail", "123456"},
                {"nekimail@", "Password"},
                {"@com", "Password"},
        };
    }

    @DataProvider(name = "dpLoginTestPasswordInvalid")
    public static Object[][] dpLoginTestPasswordInvalid() {
        return new Object[][]{
                {"nekimail@gmail.com", "1"},
                {"mail@mail.com", "@/"},
        };
    }
}