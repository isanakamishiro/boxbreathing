package net.isanakamishiro.boxbreathing.presentation.box;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import java.util.concurrent.TimeUnit;
import net.isanakamishiro.boxbreathing.presentation.ApplicationPresenter;
import net.isanakamishiro.boxbreathing.presentation.NameTokens;

public class BoxPresenter extends Presenter<BoxPresenter.MyView, BoxPresenter.MyProxy> {

    interface MyView extends View {

        void initStyles();

        void showInhaleStep();

        void showHoldFromInhaleStep();

        void showExhaleStep();

        void showHoldFromExhaleStep();

        void setCounting(int count);
    }

    @ProxyStandard
    @NameToken(NameTokens.BOX)
    interface MyProxy extends ProxyPlace<BoxPresenter> {
    }

    private CompositeDisposable disposer = new CompositeDisposable();

    @Inject
    BoxPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onReveal() {
        getView().initStyles();

        disposer.clear();

        Flowable<Long> timer = Flowable.interval(1, TimeUnit.SECONDS).onBackpressureLatest();
        disposer.add(
                Flowable.merge(
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
