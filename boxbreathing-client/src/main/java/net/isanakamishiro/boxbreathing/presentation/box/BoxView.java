package net.isanakamishiro.boxbreathing.presentation.box;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.resources.AppResources;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class BoxView extends ViewImpl implements BoxPresenter.MyView {

    interface Binder extends UiBinder<Widget, BoxView> {

    }

    private static final int LABEL_DURATION = 200;

    @UiField
    MaterialRow mainPanel;

    @UiField
    MaterialIcon breathingImage;

    @UiField
    MaterialLabel countLabel;

    @UiField
    MaterialLabel stepLabel;

    @UiField(provided = true)
    AppMessages messages;

    @UiField(provided = true)
    AppResources.AnimatorStyle animator;

    private StyleConfigurator styleConfigurator;

    @Inject
    BoxView(Binder uiBinder,
            AppMessages messages,
            AppResources resources,
            StyleConfigurator styleConfigurator) {

        this.messages = messages;
        this.animator = resources.animator();
        this.styleConfigurator = styleConfigurator;

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void initStyles() {
        styleConfigurator.setBackgroundColorStyle(mainPanel.getBackgroundColor());

        breathingImage.setOpacity(0.0);
        countLabel.setText("");
        stepLabel.setText(messages.starting());

    }

    @Override
    public void showInhaleStep() {
        removeBoxStyles(breathingImage);
        breathingImage.addStyleName(animator.box_top_left());
        breathingImage.setOpacity(1.0);

        setStepLabel(messages.inhaleString());
    }

    @Override
    public void showHoldFromInhaleStep() {
        removeBoxStyles(breathingImage);
        breathingImage.addStyleName(animator.box_top_right());
        breathingImage.setOpacity(1.0);

        setStepLabel(messages.holdFromInhaleString());
    }

    @Override
    public void showExhaleStep() {
        removeBoxStyles(breathingImage);
        breathingImage.addStyleName(animator.box_bottom_right());
        breathingImage.setOpacity(1.0);

        setStepLabel(messages.exhaleString());

    }

    @Override
    public void showHoldFromExhaleStep() {
        removeBoxStyles(breathingImage);
        breathingImage.addStyleName(animator.box_bottom_left());
        breathingImage.setOpacity(1.0);

        setStepLabel(messages.holdFromExhaleString());
    }

    @Override
    public void setCounting(int count) {
        MaterialAnimation animation = new MaterialAnimation();

        animation.setDelay(0);
        animation.setDuration(800);
        animation.setInfinite(false);
        animation.setTransition(Transition.BOUNCEIN);

        countLabel.setText(String.valueOf(count));
        animation.animate(countLabel);

    }

    private void removeBoxStyles(Widget widget) {
        widget.removeStyleName(animator.box_bottom_left());
        widget.removeStyleName(animator.box_bottom_right());
        widget.removeStyleName(animator.box_top_left());
        widget.removeStyleName(animator.box_top_right());
    }

    void setStepLabel(String label) {
        MaterialAnimation animation = new MaterialAnimation();

        animation.setDelay(0);
        animation.setDuration(LABEL_DURATION);
        animation.setInfinite(false);
        animation.setTransition(Transition.FADEOUT);
        animation.animate(stepLabel, () -> {
            MaterialAnimation animation2 = new MaterialAnimation();

            animation2.setDelay(0);
            animation2.setDuration(LABEL_DURATION);
            animation2.setInfinite(false);

            animation2.setTransition(Transition.FADEIN);
            stepLabel.setText(label);
            animation2.animate(stepLabel);
        });
    }

}
