package com.arpa.and.wms.arch.base;

import android.app.IntentService;


/**
 * 继承使用了BaseIntentService或其子类，你需要参照如下方式添加@AndroidEntryPoint注解
 */
public abstract class BaseIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name
     *         Used to name the worker thread, important only for debugging.
     */
    public BaseIntentService(String name) {
        super(name);
    }

}
