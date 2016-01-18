package com.shipdream.lib.android.mvc.controller.internal;

import android.os.Handler;
import android.os.Looper;

import com.shipdream.lib.android.mvc.event.BaseEventV;
import com.shipdream.lib.android.mvc.event.bus.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidPosterImpl implements BaseControllerImpl.AndroidPoster {
    private static Handler handler;
    private static Logger logger = LoggerFactory.getLogger(AndroidPosterImpl.class);

    /**
     * Internal use. Don't call me from your app.
     */
    public static void init() {
        BaseControllerImpl.androidPoster = new AndroidPosterImpl();
    }

    @Override
    public void post(final EventBus eventBusV, final BaseEventV eventV) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            doPost(eventBusV, eventV);
        } else {
            //Android handler is presented, posting to the main thread on Android.
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    doPost(eventBusV, eventV);
                }
            });
        }
    }

    private static void doPost(EventBus eventBusV, BaseEventV event) {
        if (eventBusV != null) {
            eventBusV.post(event);
        } else {
            logger.warn("Trying to post event but EventBusV is null");
        }
    }
}
