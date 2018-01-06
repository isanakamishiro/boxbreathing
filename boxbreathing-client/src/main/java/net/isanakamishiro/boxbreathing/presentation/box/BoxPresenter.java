
package net.isanakamishiro.boxbreathing.presentation.box;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import net.isanakamishiro.boxbreathing.presentation.ApplicationPresenter;
import net.isanakamishiro.boxbreathing.presentation.NameTokens;

public class BoxPresenter extends Presenter<BoxPresenter.MyView, BoxPresenter.MyProxy> {

    interface MyView extends View {
        void initStyles();
    }

    @ProxyStandard
    @NameToken(NameTokens.BOX)
    interface MyProxy extends ProxyPlace<BoxPresenter> {

    }

    @Inject
    BoxPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }

    @Override
    protected void onReveal() {
        getView().initStyles();
    }


}
