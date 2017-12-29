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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import gwt.material.design.jquery.client.api.Functions.Func;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

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

    AppMessages messages;

//    private AppInstaller appInstaller;
//    private Subject<Step> stepSubject = PublishSubject.create();

    @Inject
    HomeView(Binder uiBinder, AppMessages messages) {
        initWidget(uiBinder.createAndBindUi(this));

        this.messages = messages;

        btnAbout.addClickHandler(e -> Window.open(btnAbout.getHref(), "_blank", ""));
    }

    @Override
    public void showInhaleStep() {
        setStepLabel(messages.inhaleString());
        breathingImage.setOpacity(1.0);
        showStepIconAnimation(Transition.ZOOMIN);

//        showStepIconAnimation(Transition.ZOOMIN, () -> stepSubject.onNext(Step.INHALE));
    }

    @Override
    public void showHoldFromInhaleStep() {
        setStepLabel(messages.holdFromInhaleString());
        breathingImage.setOpacity(1.0);
    }

    @Override
    public void showExhaleStep() {
        setStepLabel(messages.exhaleString());
        breathingImage.setOpacity(1.0);
        showStepIconAnimation(Transition.ZOOMOUT);
    }

    @Override
    public void showHoldFromExhaleStep() {
        setStepLabel(messages.holdFromExhaleString());
        breathingImage.setOpacity(0.0);
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

    @Override
    public void setCounting(int count) {
        MaterialAnimation animation = new MaterialAnimation();

        animation.setDelay(0);
        animation.setDuration(500);
        animation.setInfinite(false);
        animation.setTransition(Transition.FLIPINX);

        countLabel.setText(String.valueOf(count));
        animation.animate(countLabel);
    }

    @Override
    protected void onAttach() {
        super.onAttach();

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
