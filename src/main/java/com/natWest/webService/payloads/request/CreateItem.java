package com.natWest.webService.payloads.request;

import com.natWest.webService.payloads.request.dao.Data;

import java.util.Map;

public class CreateItem {
    private String name;
    private Data data;

    public CreateItem(Map<String, String> dataMap) {
        this.name = dataMap.get("name");
        this.data = new Data(dataMap);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Item{" +
                " name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
