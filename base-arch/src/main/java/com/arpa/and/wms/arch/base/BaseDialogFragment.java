package com.arpa.and.wms.arch.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.arpa.and.wms.arch.R;
import com.arpa.and.wms.arch.base.livedata.MessageEvent;
import com.arpa.and.wms.arch.base.livedata.StatusEvent;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


/**
 * 继承使用了BaseDialogFragment或其子类，你需要参照如下方式添加@AndroidEntryPoint注解
 */
public abstract class BaseDialogFragment <VM extends BaseViewModel, VDB extends ViewDataBinding> extends DialogFragment implements IView<VM>, BaseNavigator {

    protected static final float DEFAULT_WIDTH_RATIO = 0.85f;
    /**
     * 请通过 {@link #getRootView()} ()}获取，后续版本 {@link #rootView}可能会私有化
     */
    public View rootView;
    /**
     * 请通过 {@link #getViewModel()}获取，后续版本 {@link #mViewModel}可能会私有化
     */
    private VM mViewModel;
    /**
     * 请通过 {@link #getViewDataBinding()}获取，后续版本 {@link #mBinding}可能会私有化
     */
    private VDB mBinding;
    private Dialog mProgressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWindow(getDialog().getWindow());
    }

    protected void initWindow(Window window) {
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getAttributes().windowAnimations = R.style.ArpaDialogAnimation;
        setWindow(window, DEFAULT_WIDTH_RATIO);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDialog(getDialog());
        rootView = createRootView(inflater, container, savedInstanceState);
        if (isBinding()) {
            mBinding = DataBindingUtil.bind(rootView);
            initViewModel();
        }
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mViewModel != null) {
            getLifecycle().removeObserver(mViewModel);
        }
        mViewModel = null;

        if (mBinding != null) {
            mBinding.unbind();
        }
    }

    protected void initDialog(Dialog dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 创建 {@link #rootView}
     */
    protected View createRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    /**
     * 初始化 {@link #mViewModel}
     */
    private void initViewModel() {
        mViewModel = createViewModel();
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
            registerLoadingEvent();
        }
    }

    /**
     * 注册状态监听
     */
    protected void registerLoadingEvent() {
        mViewModel.getLoadingEvent().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isLoading) {
                if (isLoading) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
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
     * 是否使用DataBinding
     *
     * @return 默认为true 表示使用。如果为false，则不会初始化 {@link #mBinding}。
     */
    @Override
    public boolean isBinding() {
        return true;
    }

    /**
     * 创建ViewModel
     *
     * @return 默认为null，为null时，{@link #mViewModel}会默认根据当前Activity泛型 {@link VM}获得ViewModel
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

    public <T extends View> T findViewById(@IdRes int id) {
        return getRootView().findViewById(id);
    }

    /**
     * 获取rootView
     *
     * @return {@link #rootView}
     */
    protected View getRootView() {
        return rootView;
    }

    /**
     * 注册消息事件
     */
    protected void registerMessageEvent(@NonNull MessageEvent.MessageObserver observer) {
        mViewModel.getMessageEvent().observe(getViewLifecycleOwner(), observer);
    }

    /**
     * 注册单个消息事件，消息对象:{@link Message}
     */
    protected void registerSingleLiveEvent(@NonNull Observer<Message> observer) {
        mViewModel.getSingleLiveEvent().observe(getViewLifecycleOwner(), observer);
    }

    /**
     * 注册状态事件
     */
    protected void registerStatusEvent(@NonNull StatusEvent.StatusObserver observer) {
        mViewModel.getStatusEvent().observe(getViewLifecycleOwner(), observer);
    }

    /**
     * 获取 ViewModel
     *
     * @return {@link #mViewModel}
     */
    public VM getViewModel() {
        return mViewModel;
    }

    /**
     * 获取 ViewDataBinding
     *
     * @return {@link #mBinding}
     */
    public VDB getViewDataBinding() {
        return mBinding;
    }

    /**
     * @deprecated 请使用 {@link #obtainViewModel(Class)}
     */
    @Deprecated
    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return obtainViewModel(modelClass);
    }

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

    protected void startActivity(Class<?> cls, int flags, @Nullable ActivityOptionsCompat optionsCompat) {
        startActivity(newIntent(cls, flags), optionsCompat);
    }

    protected Intent newIntent(Class<?> cls, int flags) {
        Intent intent = newIntent(cls);
        intent.addFlags(flags);
        return intent;
    }

    protected Intent newIntent(Class<?> cls) {
        return new Intent(getContext(), cls);
    }

    protected void startActivity(Intent intent, @Nullable ActivityOptionsCompat optionsCompat) {
        if (optionsCompat != null) {
            startActivity(intent, optionsCompat.toBundle());
        } else {
            startActivity(intent);
        }
    }

    protected void startActivityFinish(Class<?> cls) {
        startActivity(cls);
        finish();
    }

    //---------------------------------------
    protected void finish() {
        getActivity().finish();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(newIntent(cls));
    }

    protected void startActivityFinish(Class<?> cls, int flags) {
        startActivity(cls, flags);
        finish();
    }

    protected void startActivity(Class<?> cls, int flags) {
        startActivity(newIntent(cls, flags));
    }

    protected void startActivityFinish(Class<?> cls, @Nullable ActivityOptionsCompat optionsCompat) {
        startActivity(cls, optionsCompat);
        finish();
    }

    //---------------------------------------

    protected void startActivity(Class<?> cls, @Nullable ActivityOptionsCompat optionsCompat) {
        startActivity(newIntent(cls), optionsCompat);
    }

    protected void startActivityFinish(Class<?> cls, int flags, @Nullable ActivityOptionsCompat optionsCompat) {
        startActivity(newIntent(cls, flags), optionsCompat);
    }

    protected void startActivityFinish(Intent intent, @Nullable ActivityOptionsCompat optionsCompat) {
        startActivity(intent, optionsCompat);
    }

    //---------------------------------------

    protected void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(newIntent(cls), requestCode);
    }

    protected void startActivityForResult(Class<?> cls, int requestCode, @Nullable ActivityOptionsCompat optionsCompat) {
        Intent intent = newIntent(cls);
        if (optionsCompat != null) {
            startActivityForResult(intent, requestCode, optionsCompat.toBundle());
        } else {
            startActivityForResult(intent, requestCode);
        }
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(getContext()).inflate(id, root, attachToRoot);
    }

    protected void showDialogFragment(DialogFragment dialogFragment) {
        String tag = dialogFragment.getTag() != null ? dialogFragment.getTag() : dialogFragment.getClass().getSimpleName();
        showDialogFragment(dialogFragment, tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getFragmentManager(), tag);
    }

    protected void showDialogFragment(DialogFragment dialogFragment, FragmentManager fragmentManager, String tag) {
        dialogFragment.show(fragmentManager, tag);
    }

    protected Dialog getProgressDialog() {
        return this.mProgressDialog;
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

    //---------------------------------------

    protected void showProgressDialog(View v) {
        showProgressDialog(v, false);
    }

    protected void setDialogWindow(Dialog dialog, float widthRatio) {
        setWindow(dialog.getWindow(), widthRatio);
    }

    protected int getHeightPixels() {
        return getDisplayMetrics().heightPixels;
    }

}
