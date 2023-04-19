package com.arpa.wms.hly.logic.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.arpa.and.libs.update.config.UpdateConfiguration;
import com.arpa.and.libs.update.manager.DownloadManager;
import com.arpa.wms.hly.R;
import com.arpa.wms.hly.base.WrapBaseActivity;
import com.arpa.wms.hly.bean.res.ResVersion;
import com.arpa.wms.hly.databinding.ActivityHomeBinding;
import com.arpa.wms.hly.ui.decoration.GridItemDecoration;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * author: 李一方(<a href="mailto:a94118@gmail.com">a94118@gmail.com</a>)<br/>
 * version: 1.0.0<br/>
 * since: 2021-04-23 03:03<br/>
 *
 * <p>
 * 页面：首页
 * </p>
 */
@AndroidEntryPoint
public class HomeActivity extends WrapBaseActivity<VMHome, ActivityHomeBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        viewBind.setVmHome(viewModel);
        viewBind.rvMenu.addItemDecoration(new GridItemDecoration(10));

        viewModel.version.observe(this, version -> {
            DownloadManager manager = DownloadManager.getInstance(this);
            manager.setApkName("WMS_v" + version.getVersionName() + ".apk")
                    .setApkUrl(version.getUrl())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setConfiguration(configUpdate(version))
                    .setApkVersionCode(version.getVersionCode())
                    .setApkVersionName(version.getVersionName())
                    .setApkDescription(version.getUpdateContent())
                    .download();
        });
    }

    /**
     * 版本更新框架的一些参数配置
     */
    private UpdateConfiguration configUpdate(ResVersion version) {
        return new UpdateConfiguration()
                .setEnableLog(true)
                .setJumpInstallPage(true)
                .setShowBgdToast(false)
                .setShowNotification(true)
                .setForcedUpgrade(version.isForceUpdate());
    }
}
