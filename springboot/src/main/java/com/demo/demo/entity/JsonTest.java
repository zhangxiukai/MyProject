package com.demo.demo.entity;

import java.io.Serializable;

public class JsonTest implements Serializable {

    private String id;
    private String json;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
