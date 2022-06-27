package com.arpa.wms.hly.utils;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanUtil {

    public static SpannableStringBuilder highlight(String text, String ... target) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        for (String t : target) {
            if (TextUtils.isEmpty(t)) break;
            Matcher m = Pattern.compile(t).matcher(text);
            while (m.find()) {
                spannable.setSpan(new ForegroundColorSpan(Color.RED), m.start(), m.end(), SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannable;
    }

}
