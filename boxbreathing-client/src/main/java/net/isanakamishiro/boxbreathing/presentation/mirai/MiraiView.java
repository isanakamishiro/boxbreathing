package net.isanakamishiro.boxbreathing.presentation.mirai;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import elemental2.dom.HTMLDivElement;
import gwt.material.design.client.ui.MaterialLoader;
import gwt.material.design.client.ui.MaterialPanel;
import io.reactivex.Completable;
import io.reactivex.Observable;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.presentation.mirai.ui.MMDStage;
import net.isanakamishiro.boxbreathing.presentation.mirai.ui.Stage;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader.MMDLoader;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class MiraiView extends ViewImpl implements MiraiPresenter.MyView {

    interface Binder extends UiBinder<Widget, MiraiView> {

    }

    private static final String MODEL_FILE = "models/mirai/MiraiAkari_v1.0.pmx";
//    private static final String FLOOR_FILE = "models/stage/grid.pmx";
    private static final String FLOOR_FILE = "models/stage/tenmangu/tenmangu_shadow.pmx";

    private static final String DOME_FILE = "models/stage/SkydomeK2/Dome_K201.pmx";

//    private static final String[] POSE_FILES = {"models/vmds/wavefile_v2.vmd"};
    private static final String[] POSE_FILES = {"models/vmds/nekomimi_mikuv2.vmd"};
//    private static final String[] POSE_FILES = {"models/vmds/nosho/miku.vmd"};
//    private static final String CAMERA_FILE = "models/vmds/wavefile_camera.vmd";
    private static final String CAMERA_FILE = "models/vmds/nekomimi_camera.vmd";

    @UiField
    MaterialPanel mainPanel;

    @UiField
    HTMLDivElement canvasPanel;

    @UiField(provided = true)
    AppMessages messages;

    private MMDStage stage;

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
            showLoadingPane();

            stage = new MMDStage(new MMDLoader());

            com.google.gwt.user.client.Window.addResizeHandler(e -> resizeCanvas());

            Observable.concatArray(
                    stage.loadDome(DOME_FILE),
                    //                    stage.loadCharacter(MODEL_FILE),
                    Observable.concat(stage.loadCharacter(MODEL_FILE), stage.loadPose(POSE_FILES)),
                    stage.loadFloor(FLOOR_FILE),
                    stage.loadCamera(CAMERA_FILE)
            ).map(v -> v.toString()).subscribe(GWT::log, this::logError,
                    () -> {
                        getStage().adjustCharacterPosition(0, 0.8, 0);
                        resizeCanvas();
                        hideLoadingPane();
                        showCanvas();

                        emitter.onComplete();
                    });
        });
    }

    private Stage getStage() {
        return stage;
    }

    private void logError(Throwable e) {
        GWT.log("view error : " + e.getMessage());
    }

    private void showLoadingPane() {
        MaterialLoader.loading(true);
    }

    private void hideLoadingPane() {
        MaterialLoader.loading(false);
    }

    private void showCanvas() {
        canvasPanel.appendChild(getStage().stats());
        canvasPanel.appendChild(getStage().canvas());
    }

    private void resizeCanvas() {
        getStage().resizeCanvas(canvasPanel.clientWidth, canvasPanel.clientHeight);
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
