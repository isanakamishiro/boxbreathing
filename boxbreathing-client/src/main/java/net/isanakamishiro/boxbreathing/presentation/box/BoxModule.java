package net.isanakamishiro.boxbreathing.presentation.box;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class BoxModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(BoxPresenter.class, BoxPresenter.MyView.class, BoxView.class,
                BoxPresenter.MyProxy.class);
    }
}
