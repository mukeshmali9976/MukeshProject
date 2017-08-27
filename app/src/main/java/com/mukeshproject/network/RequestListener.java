package com.mukeshproject.network;

public interface RequestListener {
    public void onSuccess(int id, String response);
    public void onError(int id, String message);
}
