package net.isanakamishiro.boxbreathing.presentation.mirai;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class MiraiModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(MiraiPresenter.class, MiraiPresenter.MyView.class, MiraiView.class,
                MiraiPresenter.MyProxy.class);
    }
}
