package core;

import constants.PATH;
import utils.PropHelper;

public class BaseClient extends RestClient {
    private static final String HOST = PropHelper.loadPropertiesByFilePath(PATH.RESOURCES + "/env/env.properties").getProperty("HOST_API");
    protected ApiHeader headers;

    public BaseClient() {
        super(HOST);
        headers = new ApiHeader();
    }

    public BaseClient(String token) {
        super(HOST);
        headers = new ApiHeader();
        headers.add("Authorization", "Bearer " + token);
    }
    public ApiResponse POST(String endPoint, Object payLoad) {
        return post(headers, endPoint, payLoad);
    }
}
