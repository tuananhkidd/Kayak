package com.kidd.projectbase.injection;

import com.kidd.projectbase.view.impl.TestFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = TestViewModule.class)
public interface TestViewComponent {
    void inject(TestFragment fragment);
}