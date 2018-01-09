package net.isanakamishiro.boxbreathing.presentation.mirai;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import io.reactivex.disposables.CompositeDisposable;
import net.isanakamishiro.boxbreathing.presentation.ApplicationPresenter;
import net.isanakamishiro.boxbreathing.presentation.NameTokens;

public class MiraiPresenter extends Presenter<MiraiPresenter.MyView, MiraiPresenter.MyProxy> {

    interface MyView extends View {

        void initStyles();

        void pause();

        void replay();

        void showInhaleStep();

        void showHoldFromInhaleStep();

        void showExhaleStep();

        void showHoldFromExhaleStep();

        void setCounting(int count);
    }

    @ProxyStandard
    @NameToken(NameTokens.MIRAI)
    interface MyProxy extends ProxyPlace<MiraiPresenter> {
    }

    private CompositeDisposable disposer = new CompositeDisposable();

    @Inject
    MiraiPresenter(
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
        getView().replay();

//        disposer.clear();
//
//        Flowable<Long> timer = Flowable.interval(1, TimeUnit.SECONDS).onBackpressureLatest();
//        disposer.add(
//                Flowable.merge(
//                        timer.filter(i -> (i % 16) == 0).doOnNext(p -> getView().showInhaleStep()),
//                        timer.filter(i -> (i % 16) == 4).doOnNext(p -> getView().showHoldFromInhaleStep()),
//                        timer.filter(i -> (i % 16) == 8).doOnNext(p -> getView().showExhaleStep()),
//                        timer.filter(i -> (i % 16) == 12).doOnNext(p -> getView().showHoldFromExhaleStep())
////                ).subscribe(p -> GWT.log(p.toString()))
//                ).subscribe()
//        );
//
//        disposer.add(
//                timer.map(v -> v % 4 + 1).subscribe(v -> getView().setCounting(v.intValue()))
//        );
    }

    @Override
    protected void onHide() {
        disposer.clear();

        getView().pause();
    }

}
