
package ${project.groupId}.application.viewpresenter.${templating.package};

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class ${templating.name}Presenter extends Presenter<${templating.name}Presenter.MyView, ${templating.name}Presenter.MyProxy> {

    interface MyView extends View {}

    @ProxyStandard
    @NameToken(NameTokens.${templating.place})
    interface MyProxy extends ProxyPlace<${templating.name}Presenter> {

    }

    @Inject
    ${templating.name}Presenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
    }
}
