package com.kidd.projectbase.injection;

import com.kidd.projectbase.view.impl.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeFragment fragment);
}