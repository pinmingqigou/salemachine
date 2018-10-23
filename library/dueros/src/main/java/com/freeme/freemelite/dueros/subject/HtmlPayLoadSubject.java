package com.freeme.freemelite.dueros.subject;

import com.freeme.freemelite.dueros.callbacks.HtmlPayLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class HtmlPayLoadSubject {
    private static List<HtmlPayLoadCallback> callbacks = new ArrayList<>();

    public void register(HtmlPayLoadCallback callback) {
        if (callback != null) {
            callbacks.add(callback);
        }
    }

    public void unregister(HtmlPayLoadCallback callback) {
        if (callback != null) {
            callbacks.remove(callback);
        }
    }

    public void handleHtmlPayload(String url,String token) {
        for (HtmlPayLoadCallback callback : callbacks) {
            if (callback != null) {
                callback.onHtmlPayload(url,token);
            }
        }
    }
}
