package net.isanakamishiro.boxbreathing.presentation.circle;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class CircleView extends ViewImpl implements CirclePresenter.MyView {

    interface Binder extends UiBinder<Widget, CircleView> {

    }

    private static final int BREATHING_TIME = 4000;
    private static final int LABEL_DURATION = 200;

    @UiField
    MaterialRow mainPanel;

    @UiField
    MaterialIcon breathingImage;

    @UiField
    MaterialLabel stepLabel;

    @UiField
    MaterialLabel countLabel;

    @UiField(provided = true)
    AppMessages messages;

    private Color defaultColor = Color.BLACK;
    private static final Color PAUSED_COLOR = Color.GREY_DARKEN_1;
    private StyleConfigurator styleConfigurator;

    @Inject
    CircleView(CircleView.Binder uiBinder,
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
    public void showInhaleStep() {
        setStepLabel(messages.inhaleString());
        changeImageOpacity(1.0);
        changeImageColor(defaultColor);
        showStepIconAnimation(Transition.ZOOMIN);
    }

    @Override
    public void showHoldFromInhaleStep() {
        setStepLabel(messages.holdFromInhaleString());
        changeImageOpacity(1.0);
        changeImageColor(PAUSED_COLOR);
    }

    @Override
    public void showExhaleStep() {
        setStepLabel(messages.exhaleString());
        changeImageOpacity(1.0);
        changeImageColor(defaultColor);
        showStepIconAnimation(Transition.ZOOMOUT);
    }

    @Override
    public void showHoldFromExhaleStep() {
        setStepLabel(messages.holdFromExhaleString());
        changeImageOpacity(0.0);
        changeImageColor(PAUSED_COLOR);
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

    void showStepIconAnimation(Transition transition) {
        MaterialAnimation animation = new MaterialAnimation();

        animation.setDelay(0);
        animation.setDuration(BREATHING_TIME);
        animation.setInfinite(false);
        animation.setTransition(transition);
        animation.animate(breathingImage);
    }

    void changeImageOpacity(double opacity) {
        breathingImage.setOpacity(opacity);
    }

    void changeImageColor(Color color) {
        breathingImage.setTextColor(color);
        countLabel.setTextColor(color);
        stepLabel.setTextColor(color);
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

    @Override
    protected void onAttach() {
        super.onAttach();

        this.defaultColor = breathingImage.getTextColor();
    }

}
