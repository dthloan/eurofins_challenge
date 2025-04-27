package core;

import java.util.HashMap;
import java.util.Map;

public class Authorization {
    private Map<String, RestClient> clientMap;

    public Authorization() {
        this.clientMap = new HashMap<>();
        this.clientMap.put(BaseClient.class.getCanonicalName(), new BaseClient());
    }

    public <T extends RestClient> T getClient(Class<T> clientClass) {
        return (T) clientMap.get(clientClass.getCanonicalName());
    }

}
