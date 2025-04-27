package _base;

import constants.PATH;
import core.ApiResponse;
import core.Authorization;
import core.RestClient;

import java.io.File;

public class ApiAction {

    protected Authorization auth;

    protected ApiAction(Authorization auth) {
        this.auth = auth;
    }

    protected <T extends RestClient> T getRestClient(Class<T> restClientClass) {
        return auth.getClient(restClientClass);
    }
}