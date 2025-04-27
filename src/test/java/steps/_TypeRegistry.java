package steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java8.En;
import supports.enums.ERROR_MESSAGES;

public class _TypeRegistry implements En {

    @ParameterType("MISSING_CUSTOMER|MISSING_ITEMS")
    public ERROR_MESSAGES ERROR_MESSAGES(String str) {
        return ERROR_MESSAGES.valueOf(str);
    }
}
