package ${project.groupId}.${project.groupId}.application.viewpresenter.${templating.package};

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ${templating.name}Module extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(${templating.name}Presenter.class, ${templating.name}Presenter.MyView.class, ${templating.name}View.class,
                ${templating.name}Presenter.MyProxy.class);
    }
}
