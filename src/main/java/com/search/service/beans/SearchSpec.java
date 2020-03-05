package com.search.service.beans;

public class SearchSpec {
    String key;
    String ops;
    String value;
    String prefix;
    String suffix;
    String isPredicate;

    public String getIsPredicate() {
        return isPredicate;
    }

    public void setIsPredicate(String isPredicate) {
        this.isPredicate = isPredicate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
