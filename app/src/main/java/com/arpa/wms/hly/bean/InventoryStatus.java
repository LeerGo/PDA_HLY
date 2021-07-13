package com.arpa.wms.hly.bean;

public class InventoryStatus {
    private String code;
    private String name;
    private String defaultLocationCode;
    private String defaultLocationName;

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

    public String getDefaultLocationCode() {
        return defaultLocationCode;
    }

    public void setDefaultLocationCode(String defaultLocationCode) {
        this.defaultLocationCode = defaultLocationCode;
    }

    public String getDefaultLocationName() {
        return defaultLocationName;
    }

    public void setDefaultLocationName(String defaultLocationName) {
        this.defaultLocationName = defaultLocationName;
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