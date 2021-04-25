package com.arpa.and.wms.arch.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.arpa.and.wms.arch.R;
import com.arpa.and.wms.arch.base.livedata.MessageEvent;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;
import com.arpa.and.wms.arch.util.Const.ParameterField;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


/**
 * 继承使用了BaseActivity或其子类，你需要参照如下方式添加@AndroidEntryPoint注解
 */
public abstract class BaseActivity <VM extends BaseViewModel, VDB extends ViewDataBinding> extends AppCompatActivity implements IView<VM>, BaseNavigator {

    protected static final float DEFAULT_WIDTH_RATIO = 0.85f;
    /**
     * 后续版本 {@link #viewModel}可能会私有化
     */
    public VM viewModel;
    /**
     * 后续版本 {@link #viewBind}可能会私有化
     */
    public VDB viewBind;
    private Dialog mDialog;
    private final View.OnClickListener mOnDialogCancelClick = v -> dismissDialog();
    private Dialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initViewModel();
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
            viewModel = null;
        }

        if (viewBind != null) {
            viewBind.unbind();
        }
    }

    /**
     * 初始化ContentView，{@link #setContentView(int)} }
     */
    protected void initContentView() {
        if (isBinding()) {
            viewBind = DataBindingUtil.setContentView(this, getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
    }

    /**
     * 是否使用DataBinding
     *
     * @return 默认为true 表示使用。如果为false，则不会初始化 {@link #viewBind}。
     */
    @Override
    public boolean isBinding() {
        return true;
    }

    /**
     * 创建ViewModel
     *
     * @return {@link #viewModel}会默认根据当前Activity泛型 {@link VM}获得ViewModel
     */
    @Override
    public VM createViewModel() {
        return obtainViewModel(getVMClass());
    }

    private Class<VM> getVMClass() {
        Class cls = getClass();
        Class<VM> vmClass = null;
        while (vmClass == null && cls != null) {
            vmClass = getVMClass(cls);
            cls = cls.getSuperclass();
        }
        if (vmClass == null) {
            vmClass = (Class<VM>) BaseViewModel.class;
        }
        return vmClass;
    }

    private Class getVMClass(Class cls) {
        Type type = cls.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type t : types) {
                if (t instanceof Class) {
                    Class vmClass = (Class) t;
                    if (BaseViewModel.class.isAssignableFrom(vmClass)) {
                        return vmClass;
                    }
                } else if (t instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) t).getRawType();
                    if (rawType instanceof Class) {
                        Class vmClass = (Class) rawType;
                        if (BaseViewModel.class.isAssignableFrom(vmClass)) {
                            return vmClass;
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * 初始化 {@link #viewModel}
     */
    private void initViewModel() {
        viewModel = createViewModel();
        if (viewModel != null) {
            getLifecycle().addObserver(viewModel);
            registerLoadingEvent();
            registerStartActivityEvent();
        }
    }

    /**
     * 注册状态加载事件
     */
    protected void registerLoadingEvent() {
        viewModel.getLoadingEvent().observe(this, (Observer<Boolean>) isLoading -> {
            if (isLoading) {
                showLoading();
            } else {
                hideLoading();
            }
        });
    }

    /**
     * 注册界面跳转事件
     */
    protected void registerStartActivityEvent() {
        viewModel.getStartActivityEvent().observe(this, (Observer<Map<String, Object>>) params -> {
            Class<?> clz = (Class<?>) params.get(ParameterField.CLASS);
            Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
            startActivity(clz, bundle);
        });
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    protected void showProgressDialog() {
        showProgressDialog(false);
    }

    protected void showProgressDialog(boolean isCancel) {
        showProgressDialog(R.layout.dialog_progress, isCancel);
    }

    /**
     * 注册消息事件
     */
    protected void registerMessageEvent(@NonNull MessageEvent.MessageObserver observer) {
        viewModel.getMessageEvent().observe(this, observer);
    }

    /**
     * 注册单个消息事件，消息对象:{@link Message}
     */
    protected void registerSingleLiveEvent(@NonNull Observer<Message> observer) {
        viewModel.getSingleLiveEvent().observe(this, observer);
    }

    /**
     * 注册状态事件
     */
    protected void registerStatusEvent(@NonNull StatusEvent.StatusObserver observer) {
        viewModel.getStatusEvent().observe(this, observer);
    }

    /**
     * @deprecated 请使用 {@link #obtainViewModel(Class)}
     */
    @Deprecated
    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return obtainViewModel(modelClass);
    }

    //---------------------------------------

    /**
     * 通过 {@link #createViewModelProvider(ViewModelStoreOwner)}获得 ViewModel
     */
    public <T extends ViewModel> T obtainViewModel(@NonNull Class<T> modelClass) {
        return createViewModelProvider(this).get(modelClass);
    }

    /**
     * 创建 {@link ViewModelProvider}
     */
    private ViewModelProvider createViewModelProvider(@NonNull ViewModelStoreOwner owner) {
        return new ViewModelProvider(owner);
    }

    /**
     * 跳转页面
     *
     * @param clz
     *         所跳转的目的Activity类
     * @param bundle
     *         跳转所携带的信息
     */
    public void startActivityFinish(Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
        finish();
    }

    /**
     * 跳转页面
     *
     * @param clz
     *         所跳转的目的Activity类
     * @param bundle
     *         跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        startActivity(newIntent(clz, bundle));
    }

    protected Intent newIntent(Class<?> cls, Bundle bundle) {
        Intent intent = newIntent(cls);
        if (null != bundle) intent.putExtras(bundle);
        return intent;
    }

    protected Intent newIntent(Class<?> cls) {
        return new Intent(getContext(), cls);
    }

    public Context getContext() {
        return this;
    }

    /**
     * 跳转页面
     *
     * @param clz
     *         所跳转的目的Activity类
     */
    public void startActivityFinish(Class<?> clz) {
        startActivity(clz);
        finish();
    }

    /**
     * 跳转页面
     *
     * @param clz
     *         所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(newIntent(clz));
    }

    protected Intent newIntent(Class<?> cls, int flags) {
        Intent intent = newIntent(cls);
        intent.addFlags(flags);
        return intent;
    }

    //---------------------------------------
    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(getContext()).inflate(id, root, attachToRoot);
    }

    protected void showDialogFragment(DialogFragment dialogFragment) {
        String tag = dialogFragment.getTag() != null ? dialogFragment.getTag() : dialogFragment.getClass().getSimpleName();
        showDialogFragment(dialogFragment, tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment, FragmentManager fragmentManager, String tag) {
        dialogFragment.show(fragmentManager, tag);
    }

    protected Dialog getDialog() {
        return this.mDialog;
    }

    protected Dialog getProgressDialog() {
        return this.mProgressDialog;
    }

    protected View.OnClickListener getDialogCancelClick() {
        return mOnDialogCancelClick;
    }

    protected void dismissPopupWindow(PopupWindow popupWindow) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    protected void showProgressDialog(@LayoutRes int resId) {
        showProgressDialog(resId, false);
    }

    protected void showProgressDialog(@LayoutRes int resId, boolean isCancel) {
        showProgressDialog(inflate(resId), isCancel);
    }

    protected View inflate(@LayoutRes int id) {
        return inflate(id, null);
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root) {
        return LayoutInflater.from(getContext()).inflate(id, root);
    }

    protected void showProgressDialog(View v, boolean isCancel) {
        dismissProgressDialog();
        mProgressDialog = BaseProgressDialog.newInstance(getContext());
        mProgressDialog.setContentView(v);
        mProgressDialog.setCanceledOnTouchOutside(isCancel);
        mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        dismissDialog(mProgressDialog);
    }

    protected void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected void showProgressDialog(View v) {
        showProgressDialog(v, false);
    }

    protected void showDialog(View contentView) {
        showDialog(contentView, DEFAULT_WIDTH_RATIO);
    }

    protected void showDialog(View contentView, float widthRatio) {
        showDialog(getContext(), contentView, widthRatio);
    }

    protected void showDialog(Context context, View contentView, float widthRatio) {
        showDialog(context, contentView, R.style.ArpaDialog, widthRatio);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio) {
        showDialog(context, contentView, resId, widthRatio, true);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio, final boolean isCancel) {
        dismissDialog();
        mDialog = new Dialog(context, resId);
        mDialog.setContentView(contentView);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (isCancel) {
                        dismissDialog();
                    }
                    return true;
                }
                return false;

            }
        });
        setDialogWindow(mDialog, widthRatio);
        mDialog.show();

    }

    protected void dismissDialog() {
        dismissDialog(mDialog);
    }

    protected void setDialogWindow(Dialog dialog, float widthRatio) {
        setWindow(dialog.getWindow(), widthRatio);
    }

    protected void setWindow(Window window, float widthRatio) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (getWidthPixels() * widthRatio);
        window.setAttributes(lp);
    }

    protected int getWidthPixels() {
        return getDisplayMetrics().widthPixels;
    }

    protected DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    //---------------------------------------

    protected void showDialog(View contentView, boolean isCancel) {
        showDialog(getContext(), contentView, R.style.ArpaDialog, DEFAULT_WIDTH_RATIO, isCancel);
    }

    protected void showDialog(View contentView, float widthRatio, boolean isCancel) {
        showDialog(getContext(), contentView, R.style.ArpaDialog, widthRatio, isCancel);
    }

    protected int getHeightPixels() {
        return getDisplayMetrics().heightPixels;
    }

}
