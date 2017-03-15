package com.crazypeople.common.dagger;


import com.crazypeople.common.base.basePresenter.BasePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MVPframeModules.class)
public interface MVPframeComponent {

    void inject(BasePresenter presenter);

    final class MVPframeInitialize {

        public static MVPframeComponent init() {
            return DaggerMVPframeComponent.builder().build();
        }
    }
}
