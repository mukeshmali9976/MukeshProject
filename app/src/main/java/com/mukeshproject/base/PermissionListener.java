package com.mukeshproject.base;



public interface PermissionListener {

    void permissionGranted(String permission, String tag);

    void permissionDenied(String permission);

    void permissionForeverDenied(String permission);
}
