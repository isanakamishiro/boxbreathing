package net.isanakamishiro.boxbreathing.presentation.kizuna;

import com.google.gwt.core.client.GWT;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.dom.Window;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import de.pesse.gwt.jsinterop.threeJs.cameras.PerspectiveCamera;
import de.pesse.gwt.jsinterop.threeJs.core.Clock;
import de.pesse.gwt.jsinterop.threeJs.lights.AmbientLight;
import de.pesse.gwt.jsinterop.threeJs.lights.DirectionalLight;
import de.pesse.gwt.jsinterop.threeJs.objects.Mesh;
import de.pesse.gwt.jsinterop.threeJs.renderers.WebGLRenderer;
import de.pesse.gwt.jsinterop.threeJs.renderers.WebGLRendererParameters;
import de.pesse.gwt.jsinterop.threeJs.scenes.Scene;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLDivElement;
import gwt.material.design.client.ui.MaterialPanel;

import javax.inject.Inject;
import net.isanakamishiro.boxbreathing.presentation.StyleConfigurator;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.effects.OutlineEffect;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.helper.MMDHelper;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader.MMDLoader;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.stats.Stats;
import net.isanakamishiro.boxbreathing.resources.message.AppMessages;

public class KizunaView extends ViewImpl implements KizunaPresenter.MyView {

    interface Binder extends UiBinder<Widget, KizunaView> {

    }

    private static final String MODEL_FILE = "models/kizuna/kizunaai.pmx";
    private static final String[] VMD_FILES = {"models/vmds/wavefile_v2.vmd", "models/vmds/wavefile_camera.vmd"};

    @UiField
    MaterialPanel mainPanel;

    @UiField
    HTMLDivElement canvasPanel;

    HTMLCanvasElement canvas;

    private PerspectiveCamera cam;
    private WebGLRenderer renderer;
    private OutlineEffect effect;
    private Mesh mesh;
    private Scene scene;
    private Stats stats;
    private Clock clock;
    private MMDHelper helper;
    private Window.AnimationFrameCallback callback;

    private boolean init = false;
    private boolean pause = false;

    @UiField(provided = true)
    AppMessages messages;

    private final StyleConfigurator styleConfigurator;

//    private AnimationFrameCallback callback;
    @Inject
    KizunaView(Binder uiBinder,
            AppMessages messages,
            StyleConfigurator styleConfigurator) {

        this.messages = messages;
        this.styleConfigurator = styleConfigurator;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }

    private void initWegGL() {

        // Scene
        scene = new Scene();
        scene.background = new Color();

        // Stats
        stats = new Stats();
        stats.showPanel(0);

        stats.domElement.style.position = "absolute";
        stats.domElement.style.top = "";
//        canvasPanel.appendChild(stats.domElement);

        // Canvas
        canvas = (HTMLCanvasElement) DomGlobal.document.createElement("canvas");
        canvasPanel.appendChild(canvas);

        // Lighting
        AmbientLight ambientLight = new AmbientLight(0x666666);
        DirectionalLight light1 = new DirectionalLight(0x887766, 0.5);
        light1.position.set(-50, 15, 30);
        DirectionalLight light2 = new DirectionalLight(0x887766, 0.5);
        light2.position.set(50, 15, 30);

        scene.add(ambientLight, light1, light2);
//        scene.add(ambientLight);

        // Camera
        int w = canvasPanel.clientWidth;
        int h = canvasPanel.clientHeight;

        cam = new PerspectiveCamera(45, (float) w / (float) h, 1, 2000);
//        cam.position.set(0, 10, 35);
        cam.position.z = 30;

        // MMD Model
        helper = new MMDHelper();
        MMDLoader loader = new MMDLoader();

        MMDLoader.OnProgressCallback onProgress = xhr -> {
        };

        MMDLoader.OnErrorCallback onError = error -> {
        };

        loader.load(MODEL_FILE, VMD_FILES, obj -> {
            GWT.log("Model loaded.");

            mesh = obj;
            mesh.position.y = -10;
            mesh.castShadow = true;
            scene.add(mesh);

            helper.add(mesh);
            helper.setAnimation(mesh);
            helper.setPhysics(mesh);

            MMDHelper.AnimationParam param = new MMDHelper.AnimationParam(2.0);
            helper.unifyAnimationDuration(param);

        }, onProgress, onError);

        // Renderer
        renderer = new WebGLRenderer(new WebGLRendererParameters.Builder()
                .canvas(canvas)
                .build());

        renderer.setPixelRatio(Window.getDevicePixelRatio());
        renderer.setSize(w, h);

        // Effect
        effect = new OutlineEffect(renderer);

        // Clock
        clock = new Clock();

        com.google.gwt.user.client.Window.addResizeHandler(event -> {
            int w1 = canvasPanel.clientWidth;
            int h1 = canvasPanel.clientHeight;

            cam.aspect = (float) w1 / (float) h1;
            cam.updateProjectionMatrix();

            renderer.setSize(w1, h1);
        });

    }

    void animate() {
        callback = () -> {
            if (pause) {
                return;
            }
            Window.requestAnimationFrame(callback);

            stats.begin();
            render();
            stats.end();
        };

        Window.requestAnimationFrame(callback);
    }

    void render() {
        helper.animate(clock.getDelta());

        effect.render(scene, cam);
    }

    @Override
    public void initStyles() {
        styleConfigurator.setBackgroundColorStyle(mainPanel.getBackgroundColor());

    }

    @Override
    public void pause() {
        this.pause = true;
    }

    @Override
    public void replay() {
        this.pause = false;
        Scheduler.get().scheduleDeferred(() -> {
            if (!init) {
                initWegGL();
                init = true;
            }
            animate();
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
