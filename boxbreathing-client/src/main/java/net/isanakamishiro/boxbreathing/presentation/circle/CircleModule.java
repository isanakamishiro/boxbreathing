package net.isanakamishiro.boxbreathing.presentation.circle;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CircleModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(CirclePresenter.class, CirclePresenter.MyView.class, CircleView.class,
                CirclePresenter.MyProxy.class);
    }
}
