package com.arpa.wms.hly.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-02-07 08:28<br/>
 *
 * <p>
 * 弱引用的 Handler，防止内存泄露
 * </p>
 */
public class WeakHandler <T extends WeakHandler.MessageListener> extends Handler {
    private final WeakReference<T> mHandlerReference;

    public WeakHandler(T owner) {
        mHandlerReference = new WeakReference<>(owner);
    }

    @Override
    public void handleMessage(Message msg) {
        final T owner = getOwner();
        if (owner != null) {
            owner.handleMessage(msg);
        }
    }

    public T getOwner() {
        return this.mHandlerReference.get();
    }

    public void clear() {
        mHandlerReference.clear();
    }

    public interface MessageListener {
        void handleMessage(Message msg);
    }
}