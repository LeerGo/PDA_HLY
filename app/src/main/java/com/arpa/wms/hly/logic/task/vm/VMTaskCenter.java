package com.arpa.wms.hly.logic.task.vm;

import android.app.Application;

import com.arpa.and.wms.arch.base.BaseModel;
import com.arpa.wms.hly.base.viewmodel.WrapDataViewModel;
import com.arpa.wms.hly.logic.task.TaskAssignFragment;
import com.arpa.wms.hly.utils.Const.ASSIGN_WORK;

import java.util.Arrays;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class VMTaskCenter extends WrapDataViewModel {
    private final ObservableList<Fragment> fragments = new ObservableArrayList<>();
    private final ObservableList<String> titles = new ObservableArrayList<>();

    @Inject
    public VMTaskCenter(@NonNull Application application, BaseModel model) {
        super(application, model);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        fragments.add(TaskAssignFragment.newInstance(ASSIGN_WORK.ASSIGN_NOT));
        fragments.add(TaskAssignFragment.newInstance(ASSIGN_WORK.ASSIGN_YET));
        titles.addAll(Arrays.asList("待指派", "已指派"));
    }

    public ObservableList<Fragment> getFragments() {
        return fragments;
    }

    public ObservableList<String> getTitles() {
        return titles;
    }
}