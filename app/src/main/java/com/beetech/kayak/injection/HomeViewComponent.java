package com.beetech.kayak.injection;

import com.beetech.kayak.view.impl.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = HomeViewModule.class)
public interface HomeViewComponent {
    void inject(HomeFragment fragment);
}