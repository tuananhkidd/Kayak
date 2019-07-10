package com.kidd.projectbase.injection;

import com.kidd.projectbase.view.impl.Test2Fragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = Test2ViewModule.class)
public interface Test2ViewComponent {
    void inject(Test2Fragment fragment);
}