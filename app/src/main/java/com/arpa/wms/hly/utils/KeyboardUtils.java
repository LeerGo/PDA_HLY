package com.arpa.wms.hly.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/08/02
 *     desc  : utils about keyboard
 * </pre>
 */
public final class KeyboardUtils {

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Show the soft input.
     */
    public static void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Show the soft input.
     *
     * @param view
     *         The view.
     */
    public static void showSoftInput(@NonNull final View view) {
        showSoftInput(view, 0);
    }

    /**
     * Show the soft input.
     *
     * @param view
     *         The view.
     * @param flags
     *         Provides additional operating flags.  Currently may be
     *         0 or have the {@link InputMethodManager#SHOW_IMPLICIT} bit set.
     */
    public static void showSoftInput(@NonNull final View view, final int flags) {
        InputMethodManager imm =
                (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, flags, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == InputMethodManager.RESULT_UNCHANGED_HIDDEN
                        || resultCode == InputMethodManager.RESULT_HIDDEN) {
                    toggleSoftInput();
                }
            }
        });
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * Toggle the soft input display or not.
     */
    public static void toggleSoftInput() {
        InputMethodManager imm =
                (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.toggleSoftInput(0, 0);
    }

    /**
     * Hide the soft input.
     *
     * @param activity
     *         The activity.
     */
    public static void hideSoftInput(@NonNull final Activity activity) {
        hideSoftInput(activity.getWindow());
    }

    /**
     * Hide the soft input.
     *
     * @param window
     *         The window.
     */
    public static void hideSoftInput(@NonNull final Window window) {
        View view = window.getCurrentFocus();
        if (view == null) {
            View decorView = window.getDecorView();
            View focusView = decorView.findViewWithTag("keyboardTagView");
            if (focusView == null) {
                view = new EditText(window.getContext());
                view.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(view, 0, 0);
            } else {
                view = focusView;
            }
            view.requestFocus();
        }
        hideSoftInput(view);
    }

    /**
     * Hide the soft input.
     *
     * @param view
     *         The view.
     */
    public static void hideSoftInput(@NonNull final View view) {
        InputMethodManager imm =
                (InputMethodManager) Utils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}