package com.mukeshproject.domain.listeners;

/**
 * The interface Permission listener.
 */
public interface PermissionListener {

    /**
     * Permission granted.
     *
     * @param permission the permission
     * @param tag        the tag
     */
    void permissionGranted(String permission, String tag);

    /**
     * Permission denied.
     *
     * @param permission the permission
     */
    void permissionDenied(String permission);

    /**
     * Permission forever denied.
     *
     * @param permission the permission
     */
    void permissionForeverDenied(String permission);
}
