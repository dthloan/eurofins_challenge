package core;

import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.List;

public class ApiHeader {
    protected List<Header> headers;

    public ApiHeader() {
        headers = new ArrayList<>();
        headers.add(new Header("Accept", "*/*"));
        headers.add(new Header("Content-Type", "application/json"));
    }

    public void add(String name, String value) {
        headers.add(new Header(name, value));
    }
}
