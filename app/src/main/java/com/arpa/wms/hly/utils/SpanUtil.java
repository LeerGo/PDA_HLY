package com.arpa.wms.hly.utils;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

public class SpanUtil {

    public static SpannableStringBuilder highlight(String text, String... target) {
        int star, end;
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        for (String t : target) {
            if (TextUtils.isEmpty(t)) {break;}
            star = text.indexOf(t);
            if (star != -1) {
                end = star+t.length();
                if (end<=text.length()) {
                    spannable.setSpan(new ForegroundColorSpan(Color.RED), star, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spannable;
    }

}
