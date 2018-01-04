/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package net.isanakamishiro.boxbreathing.presentation.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class HomeView extends ViewImpl implements HomePresenter.MyView {

    interface Binder extends UiBinder<Widget, HomeView> {

    }

    private static final int BREATHING_TIME = 4000;

    @UiField
    MaterialIcon breathingImage;

    @UiField
    MaterialLabel stepLabel;

    @UiField
    MaterialLabel countLabel;

    @UiField
    MaterialButton btnAbout;

    @UiField(provided = true)
    AppMessages messages;

    private Color defaultColor = Color.BLACK;
    private static final Color PAUSED_COLOR = Color.GREY_DARKEN_1;

//    private AppInstaller appInstaller;
//    private Subject<Step> stepSubject = PublishSubject.create();

    @Inject
    HomeView(Binder uiBinder, AppMessages messages) {

        this.messages = messages;
        initWidget(uiBinder.createAndBindUi(this));

        btnAbout.addClickHandler(e -> Window.open(btnAbout.getHref(), "_blank", ""));
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
        stepLabel.setText(label);
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

//        appInstaller = new AppInstaller(() -> overlay.open());
//        if (appInstaller.isLaunched(DisplayMode.FULLSCREEN)) {
//            install.setVisible(false);
//        }
    }

//    @UiHandler("install")
//    void onInstall(ClickEvent e) {
//        appInstaller.prompt();
//    }
//
//    @UiHandler("gotIt")
//    void onGotIt(ClickEvent e) {
//        overlay.close();
//    }
}
