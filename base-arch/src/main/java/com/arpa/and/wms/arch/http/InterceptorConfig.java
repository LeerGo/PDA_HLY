package com.arpa.and.wms.arch.http;

public class InterceptorConfig {
    private boolean isAddLog;
    private boolean isAddGsonConverterFactory;

    private InterceptorConfig(Builder builder) {
        isAddLog = builder.isAddLog;
        isAddGsonConverterFactory = builder.isAddGsonConverterFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 是否添加 支持打印 Http 相关日志
     */
    public boolean isAddLog() {
        return isAddLog;
    }

    /**
     * 是否添加 支持 Gson 转换工厂
     */
    public boolean isAddGsonConverterFactory() {
        return isAddGsonConverterFactory;
    }

    public static final class Builder {
        private boolean isAddLog = true;
        private boolean isAddGsonConverterFactory = true;

        private Builder() {
        }

        /**
         * 是否添加 支持打印 Http 相关日志
         */
        public Builder addLog(boolean addLog) {
            this.isAddLog = addLog;
            return this;
        }

        /**
         * 是否添加 支持 Gson 转换工厂
         */
        public Builder addGsonConverterFactory(boolean addGsonConverterFactory) {
            isAddGsonConverterFactory = addGsonConverterFactory;
            return this;
        }

        public InterceptorConfig build() {
            return new InterceptorConfig(this);
        }
    }
}
