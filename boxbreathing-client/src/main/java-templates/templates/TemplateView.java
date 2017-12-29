
package ${project.groupId}.application.viewpresenter.${templating.package};

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;

public class ${templating.name}View extends ViewImpl implements ${templating.name}Presenter.MyView {

    interface Binder extends UiBinder<Widget, ${templating.name}View> {

    }

    @Inject
    ${templating.name}View(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }

}
