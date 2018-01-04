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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gwt.material.design.client.pwa.PwaManager;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.concurrent.TimeUnit;
import net.isanakamishiro.boxbreathing.presentation.ApplicationPresenter;
import net.isanakamishiro.boxbreathing.presentation.NameTokens;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

    interface MyView extends View {

//        public static enum Step {
//            INHALE,
//            HOLD_FROM_INHALE,
//            EXHALE,
//            HOLD_FROM_EXHALE;
//        }
        void showInhaleStep();

        void showHoldFromInhaleStep();

        void showExhaleStep();

        void showHoldFromExhaleStep();

        void setCounting(int count);

//        void changeStep(Step step);
//        Observable<Step> onStepFinished();
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    CompositeDisposable disposer = new CompositeDisposable();

    PwaManager manager = PwaManager.getInstance();

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();

//        Observable<MyView.Step> event = getView().onStepFinished();
//        timer.filter(i -> (i % 4) == 0).doOnNext(p -> getView().showHoldFromInhaleStep()).subscribe(p -> GWT.log(p.toString()));
//        timer.filter(i -> (i % 4) == 1).doOnNext(p -> getView().showExhaleStep()).subscribe(p -> GWT.log(p.toString()));
//        timer.filter(i -> (i % 4) == 2).doOnNext(p -> getView().showHoldFromExhaleStep()).subscribe(p -> GWT.log(p.toString()));
//        timer.filter(i -> (i % 4) == 3).doOnNext(p -> getView().showInhaleStep()).subscribe(p -> GWT.log(p.toString()));
//        Observable.merge(
//                event.filter(step -> step == INHALE).doOnNext(p -> getView().showHoldFromInhaleStep()),
//                event.filter(step -> step == HOLD_FROM_INHALE).doOnNext(p -> getView().showExhaleStep()),
//                event.filter(step -> step == EXHALE).doOnNext(p -> getView().showHoldFromExhaleStep()),
//                event.filter(step -> step == HOLD_FROM_EXHALE).doOnNext(p -> getView().showInhaleStep())
////        ).subscribe(p -> GWT.log(p.toString()));
//        ).subscribe();
    }

    @Override
    protected void onReveal() {

        disposer.clear();

        Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);
        disposer.add(
                Observable.merge(
                        timer.filter(i -> (i % 16) == 0).doOnNext(p -> getView().showInhaleStep()),
                        timer.filter(i -> (i % 16) == 4).doOnNext(p -> getView().showHoldFromInhaleStep()),
                        timer.filter(i -> (i % 16) == 8).doOnNext(p -> getView().showExhaleStep()),
                        timer.filter(i -> (i % 16) == 12).doOnNext(p -> getView().showHoldFromExhaleStep())
//                ).subscribe(p -> GWT.log(p.toString()))
                ).subscribe()
        );
        disposer.add(
                timer.map(v -> v % 4 + 1).subscribe(v -> getView().setCounting(v.intValue()))
        );
    }

    @Override
    protected void onHide() {
        disposer.clear();
    }

}
