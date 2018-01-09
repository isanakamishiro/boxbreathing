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

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.presentation.mirai.ui.MMDStage;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class MiraiView extends ViewImpl implements MiraiPresenter.MyView {

    interface Binder extends UiBinder<Widget, MiraiView> {

    }

    private static final String MODEL_FILE = "models/mirai/MiraiAkari_v1.0.pmx";
    private static final String[] VMD_FILES = {"models/vmds/megumegu_fire.vmd"};

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

    private void initMMDStage() {
        stage = new MMDStage(canvasPanel.clientWidth, canvasPanel.clientHeight);
        stage.load(MODEL_FILE, VMD_FILES)
                .map(v -> {
                    NumberFormat fmt = NumberFormat.getPercentFormat();
                    return "Model loading.. " + fmt.format(v / 100);
                })
                .subscribe(GWT::log);
        com.google.gwt.user.client.Window.addResizeHandler(event ->
                stage.resize(canvasPanel.clientWidth, canvasPanel.clientHeight));

        canvasPanel.appendChild(stage.getStatDomElement());
        canvasPanel.appendChild(stage.getCanvasElement());
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
        Scheduler.get().scheduleDeferred(() -> {
            if (stage == null) {
                initMMDStage();
            }
            stage.play();
        });
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
    public void setCounting(int count) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
