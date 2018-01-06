package net.isanakamishiro.boxbreathing.presentation.box;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialRow;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class BoxView extends ViewImpl implements BoxPresenter.MyView {

    interface Binder extends UiBinder<Widget, BoxView> {

    }

    @UiField
    MaterialRow mainPanel;

    @UiField(provided = true)
    AppMessages messages;

    private StyleConfigurator styleConfigurator;

    @Inject
    BoxView(Binder uiBinder,
            AppMessages messages,
            StyleConfigurator styleConfigurator) {

        this.messages = messages;
        this.styleConfigurator = styleConfigurator;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void initStyles() {
        styleConfigurator.setBackgroundColorStyle(mainPanel.getBackgroundColor());
    }



    @Override
    protected void onAttach() {
        super.onAttach();
    }

}
