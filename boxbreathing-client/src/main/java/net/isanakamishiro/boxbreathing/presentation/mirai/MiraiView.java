package net.isanakamishiro.boxbreathing.presentation.mirai;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import elemental2.dom.HTMLDivElement;
import gwt.material.design.client.ui.MaterialPanel;
import io.reactivex.Completable;
import io.reactivex.Observable;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.presentation.mirai.ui.MMDStage;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader.MMDLoader;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class MiraiView extends ViewImpl implements MiraiPresenter.MyView {

    interface Binder extends UiBinder<Widget, MiraiView> {

    }

    private static final String MODEL_FILE = "models/mirai/MiraiAkari_v1.0.pmx";
    private static final String FLOOR_FILE = "models/stage/grid.pmx";
    private static final String DOME_FILE = "models/stage/dome01.pmx";

    private static final String[] POSE_FILES = {"models/vmds/nekomimi_mikuv2.vmd"};
    private static final String CAMERA_FILE = "models/vmds/nekomimi_camera.vmd";

    @UiField
    MaterialPanel mainPanel;

    @UiField
    HTMLDivElement canvasPanel;

    MMDStage stage;

    @UiField(provided = true)
    AppMessages messages;

    private final StyleConfigurator styleConfigurator;

    @Inject
    MiraiView(Binder uiBinder,
            AppMessages messages,
            StyleConfigurator styleConfigurator) {

        this.messages = messages;
        this.styleConfigurator = styleConfigurator;
        initWidget(uiBinder.createAndBindUi(this));
    }

    private Completable initMMDStage() {
        return Completable.create(emitter -> {
            stage = new MMDStage(new MMDLoader());

            stage.resize(canvasPanel.clientWidth, canvasPanel.clientHeight);
            com.google.gwt.user.client.Window.addResizeHandler(event
                    -> stage.resize(canvasPanel.clientWidth, canvasPanel.clientHeight));

            canvasPanel.appendChild(stage.getStatDomElement());
            canvasPanel.appendChild(stage.canvas());

            Observable.concatArray(
                    stage.loadDome(DOME_FILE),
                    Observable.concat(stage.loadCharacter(MODEL_FILE), stage.loadPose(POSE_FILES)),
                    stage.loadFloor(FLOOR_FILE),
                    stage.loadPose(POSE_FILES),
                    stage.loadCamera(CAMERA_FILE)
            ).map(v -> v.toString()).subscribe(GWT::log, this::logError, emitter::onComplete);
        });
    }

    private void logError(Throwable e) {
        GWT.log("view error : " + e.getMessage());
    }

    @Override
    public void initStyles() {
        styleConfigurator.setBackgroundColorStyle(mainPanel.getBackgroundColor());

    }

    @Override
    public void pause() {
        stage.pause();
    }

    @Override
    public void replay() {
//        Scheduler.get().scheduleDeferred(() -> {
        if (stage == null) {
            initMMDStage().subscribe(() -> stage.play());
        } else {
            stage.play();
        }
//        });
    }

    @Override
    public void showInhaleStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showHoldFromInhaleStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showExhaleStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showHoldFromExhaleStep() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCounting(int count
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
