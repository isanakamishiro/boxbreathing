package net.isanakamishiro.boxbreathing.presentation.kizuna;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class KizunaModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(KizunaPresenter.class, KizunaPresenter.MyView.class, KizunaView.class,
                KizunaPresenter.MyProxy.class);
    }
}
