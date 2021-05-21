package com.arpa.wms.hly.bean;

public class InventoryStatus {
    private String code;
    private String name;

    public InventoryStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryStatus)) return false;

        InventoryStatus that = (InventoryStatus) o;

        return code.equals(that.code);
    }
}