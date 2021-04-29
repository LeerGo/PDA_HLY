package com.arpa.and.wms.arch.binding.viewadapter;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

/**
 * author: 李一方(<a href="mailto:leergo@dingtalk.com">leergo@dingtalk.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-29 16:21
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class ImageViewBindingAdapter {
    @BindingAdapter(value = {"imageId"})
    public static void bindImageId(AppCompatImageView iv, Integer id) {
        if (id != null)
            iv.setImageResource(id);
    }
}
