/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.mirai.ui;

import com.google.gwt.core.client.GWT;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLElement;
import io.reactivex.Observable;
import java.util.Arrays;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.core.XMLHttpRequest;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.dom.Window;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.PerspectiveCamera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.controls.OrbitControls;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Clock;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.effects.OutlineEffect;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.helper.MMDHelper;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights.AmbientLight;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights.DirectionalLight;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader.MMDLoader;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.Mesh;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.VmdObject;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.WebGLRenderer;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.scenes.Scene;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.stats.Stats;

/**
 *
 * @author isana
 */
public class MMDStage implements Stage {

    @Getter
    @Accessors(fluent = true)
    public static enum ModelOption {

        CHARACTER(true, true, false),
        FLOOR(false, true, true),
        DOME(false, false, true);

        private final boolean physics;
        private final boolean castShadow;
        private final boolean receiveShadow;

        private ModelOption(boolean physics, boolean castShadow, boolean receiveShadow) {
            this.physics = physics;
            this.castShadow = castShadow;
            this.receiveShadow = receiveShadow;
        }
    }

    private int canvasWidth = 1;
    private int canvasHeight = 1;

    private final MMDLoader loader;

    private PerspectiveCamera camera;
    private OrbitControls controls;
    private WebGLRenderer renderer;
    private OutlineEffect effect;

    // MMD Mesh
    private Mesh mmdCharacter;
    private Mesh mmdFloor;
    private Mesh mmdDome;
    private VmdObject vmdCamera;

    private Scene scene;
    private Stats stats;
    private Clock clock;
    private MMDHelper mmdHelper;
    private Window.AnimationFrameCallback callback;

    private HTMLCanvasElement canvasElement;

    private boolean pause = false;

    public MMDStage(MMDLoader loader) {
        this.loader = loader;

        init();
    }

    private void init() {
        GWT.log("init");

        // Canvas creation
        canvasElement = (HTMLCanvasElement) DomGlobal.document.createElement("canvas");
        canvasElement.id = "glCanvas";

        // Stats
        stats = new Stats();
        stats.showPanel(0);
        stats.domElement.style.position = "absolute";
        stats.domElement.style.top = "";

        // Scene
        scene = new Scene();
        scene.setBackgroundColor(new Color());

        // Default Camera
        int width = this.canvasWidth;
        int height = this.canvasHeight;

        camera = new PerspectiveCamera(45, (float) width / (float) height, 1, 2000);
        controls = new OrbitControls(camera);
        camera.position.y = 20;
        camera.position.z = 30;
        controls.update();

        // Renderer
        renderer = WebGLRenderer.builder()
                .canvas(canvasElement)
                .antialias(true)
                .build();

        renderer.setPixelRatio(Window.getDevicePixelRatio());
        renderer.setSize(width, height);

        // Renderer : Model specific Shadow parameters
        renderer.shadowMap.enabled = true;
        renderer.shadowMap.renderSingleSided = false;
        renderer.shadowMap.renderReverseSided = false;

        // Helper
        mmdHelper = new MMDHelper();

        // Effect
        effect = new OutlineEffect(renderer);

        // Clock
        clock = new Clock();

        // Lighting
        initLighting();
    }

    private void initLighting() {
        // Lighting
        AmbientLight ambientLight = new AmbientLight(Color.valueOf(0x666666));

        DirectionalLight light1 = new DirectionalLight(Color.valueOf(0x887766));
        light1.position.set(-15, 15, 15);
//        DirectionalLight light2 = new DirectionalLight(Color.valueOf(0x887766), 0.5);
//        light2.position.set(50, 15, 30);

        light1.castShadow = true;
        light1.shadow.mapSize.x = 1024;
        light1.shadow.mapSize.y = 1024;
        light1.shadow.camera().right = 40;
        light1.shadow.camera().top = 40;
        light1.shadow.camera().left = -40;
        light1.shadow.camera().bottom = -40;

        // Model specific Shadow parameters
        light1.shadow.bias = -0.001;

        scene.add(ambientLight, light1);

    }

    @Override
    public Observable<Progress> loadCharacter(String modelFile) {
        return Observable.create(emitter -> {
            getLoader().loadModel(modelFile, obj -> {

                mmdCharacter = obj;
                mmdCharacter.castShadow = ModelOption.CHARACTER.castShadow();
                mmdCharacter.receiveShadow = ModelOption.CHARACTER.receiveShadow();

//                mmdCharacter.position.y = 1;
                getScene().add(mmdCharacter);
                mmdHelper.add(mmdCharacter);
                mmdHelper.setPhysics(mmdCharacter);

                emitter.onComplete();

            }, xhr -> emitter.onNext(convertXhrToProgress(modelFile, xhr)),
                    err -> emitter.onError(convertJsErrorToThrowable(err)));

        });
    }

    @Override
    public Observable<Progress> loadPose(String[] motionFiles) {
        return Observable.create(emitter -> {
            getLoader().loadVmds(motionFiles, obj -> {

                if (getCharacter() != null) {
                    getLoader().pourVmdIntoModel(getCharacter(), obj);
                    mmdHelper.setAnimation(getCharacter());
                }

                emitter.onComplete();

            }, xhr -> emitter.onNext(convertXhrToProgress(Arrays.toString(motionFiles), xhr)),
                    err -> emitter.onError(convertJsErrorToThrowable(err)));

        });
    }

    @Override
    public Observable<Progress> loadFloor(String stageFile) {
        return Observable.create(emitter -> {
            getLoader().loadModel(stageFile, obj -> {

                mmdFloor = obj;
                mmdFloor.castShadow = ModelOption.FLOOR.castShadow();
                mmdFloor.receiveShadow = ModelOption.FLOOR.receiveShadow();

                getScene().add(mmdFloor);
                mmdHelper.add(mmdFloor);

                emitter.onComplete();

            }, xhr -> emitter.onNext(convertXhrToProgress(stageFile, xhr)),
                    err -> emitter.onError(convertJsErrorToThrowable(err)));

        });
    }

    @Override
    public Observable<Progress> loadDome(String domeFile) {
        return Observable.create(emitter -> {
            getLoader().loadModel(domeFile, obj -> {

                mmdDome = obj;
                mmdDome.castShadow = ModelOption.DOME.castShadow();
                mmdDome.receiveShadow = ModelOption.DOME.receiveShadow();

                getScene().add(mmdDome);
                mmdHelper.add(mmdDome);

                emitter.onComplete();

            }, xhr -> emitter.onNext(convertXhrToProgress(domeFile, xhr)),
                    err -> emitter.onError(convertJsErrorToThrowable(err)));

        });
    }

    @Override
    public Observable<Progress> loadCamera(String cameraFile) {
        return Observable.create(emitter -> {
            getLoader().loadVmds(new String[]{cameraFile}, obj -> {

                vmdCamera = obj;
                mmdHelper.setCamera(getCamera());
                getLoader().pourVmdIntoCamera(getCamera(), vmdCamera);
                mmdHelper.setCameraAnimation(getCamera());

                emitter.onComplete();

            }, xhr -> emitter.onNext(convertXhrToProgress(cameraFile, xhr)),
                    err -> emitter.onError(convertJsErrorToThrowable(err)));

        });
    }

    @Override
    public void resizeCanvas(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;

        getCamera().aspect = (float) width / (float) height;
        getCamera().updateProjectionMatrix();

        renderer.setSize(width, height);
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void play() {
        pause = false;

        MMDHelper.AnimationParam param = new MMDHelper.AnimationParam(2.0);
        mmdHelper.unifyAnimationDuration(param);

        animate();
    }

    @Override
    public void reset() {
        clock.stop();
    }

    @Override
    public HTMLCanvasElement canvas() {
        return canvasElement;
    }

    @Override
    public HTMLElement stats() {
        return stats.domElement;
    }

    @Override
    public void adjustCharacterPosition(double x, double y, double z) {
        if (getCharacter() != null) {
            getCharacter().position.set(x, y, z);
        }
    }

    private MMDLoader getLoader() {
        return loader;
    }

    private Scene getScene() {
        return scene;
    }

    private PerspectiveCamera getCamera() {
        return camera;
    }

    private Mesh getCharacter() {
        return mmdCharacter;
    }

    private void animate() {
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

    private void render() {
        mmdHelper.animate(clock.getDelta());

        effect.render(getScene(), getCamera());
    }

    private Progress convertXhrToProgress(final String resourceName, XMLHttpRequest xhr) {
        return Progress.builder()
                .resourceName(resourceName)
                .loaded(xhr.loaded)
                .total(xhr.total)
                .build();
    }

    private Throwable convertJsErrorToThrowable(net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.core.Error error) {
        return new RuntimeException(error.message);
    }

}
