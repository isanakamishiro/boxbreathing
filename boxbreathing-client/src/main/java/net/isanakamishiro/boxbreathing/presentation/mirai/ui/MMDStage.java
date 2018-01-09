/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.mirai.ui;

import de.pesse.gwt.jsinterop.threeJs.cameras.PerspectiveCamera;
import de.pesse.gwt.jsinterop.threeJs.core.Clock;
import de.pesse.gwt.jsinterop.threeJs.geometries.PlaneBufferGeometry;
import de.pesse.gwt.jsinterop.threeJs.lights.AmbientLight;
import de.pesse.gwt.jsinterop.threeJs.lights.DirectionalLight;
import de.pesse.gwt.jsinterop.threeJs.materials.MeshPhongMaterial;
import de.pesse.gwt.jsinterop.threeJs.objects.Mesh;
import de.pesse.gwt.jsinterop.threeJs.renderers.WebGLRenderer;
import de.pesse.gwt.jsinterop.threeJs.renderers.WebGLRendererParameters;
import de.pesse.gwt.jsinterop.threeJs.scenes.Scene;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLElement;
import io.reactivex.Observable;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.dom.Window;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.effects.OutlineEffect;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.helper.MMDHelper;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader.MMDLoader;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.stats.Stats;

/**
 *
 * @author isana
 */
public class MMDStage implements Animation {

    private int canvasWidth;
    private int canvasHeight;

    private PerspectiveCamera camera;
    private WebGLRenderer renderer;
    private OutlineEffect effect;
    private Mesh mmdModel;
    private Mesh floor;
    private Scene scene;
    private Stats stats;
    private Clock clock;
    private MMDHelper helper;
    private Window.AnimationFrameCallback callback;

    private HTMLCanvasElement canvasElement;

    private boolean pause = false;

    public MMDStage(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        init();
    }

    public HTMLCanvasElement getCanvasElement() {
        return canvasElement;
    }

    public HTMLElement getStatDomElement() {
        return stats.domElement;
    }

    private void init() {
        // Canvas creation
        canvasElement = (HTMLCanvasElement) DomGlobal.document.createElement("canvas");

        // Scene
        scene = new Scene();
        scene.background = new Color();

        // Stats
        stats = new Stats();
        stats.showPanel(0);
        stats.domElement.style.position = "absolute";
        stats.domElement.style.top = "";

        // Lighting
        AmbientLight ambientLight = new AmbientLight(0x666666);
        DirectionalLight light1 = new DirectionalLight(0x887766, 0.5);
        light1.position.set(-50, 15, 30);
        DirectionalLight light2 = new DirectionalLight(0x887766, 0.5);
        light2.position.set(50, 15, 30);

        scene.add(ambientLight, light1, light2);

        // Camera
        int width = this.canvasWidth;
        int height = this.canvasHeight;

        camera = new PerspectiveCamera(45, (float) width / (float) height, 1, 2000);
        camera.position.z = 40;

        // Stage floor
        PlaneBufferGeometry floorGeometry = new PlaneBufferGeometry(100, 100);
//        MeshLambertMaterialParameters params = new MeshLambertMaterialParameters();
        MeshPhongMaterial floorMaterial = new MeshPhongMaterial();
        floor = new Mesh(floorGeometry, floorMaterial);
        floor.position.set(0, -10, 0);
        floor.rotation.x = -90 * Math.PI / 180;
        floor.receiveShadow = true;
//        scene.add(floor);

        // Renderer
        renderer = new WebGLRenderer(new WebGLRendererParameters.Builder()
                .canvas(canvasElement)
                .build());

        renderer.setPixelRatio(Window.getDevicePixelRatio());
        renderer.setSize(width, height);

        // shadow
//        light1.castShadow = true;

        // Helper
        helper = new MMDHelper();

        // Effect
        effect = new OutlineEffect(renderer);

        // Clock
        clock = new Clock();
    }

    public Observable<Float> load(String modelFile, String[] vmdFiles) {
        return Observable.create(observer -> {

            MMDLoader loader = new MMDLoader();

            MMDLoader.OnProgressCallback onProgress = xhr -> {
                observer.onNext((float) xhr.loaded / (float) xhr.total * 100);
            };

            MMDLoader.OnErrorCallback onError = error -> {
                observer.onError(new RuntimeException(error.message));
            };

            MMDLoader.OnModelLoadCallback onLoaded = obj -> {
                mmdModel = obj;
                mmdModel.position.y = -10;
                mmdModel.castShadow = true;
                scene.add(mmdModel);

                helper.add(mmdModel);
                helper.setAnimation(mmdModel);
                helper.setPhysics(mmdModel);

                MMDHelper.AnimationParam param = new MMDHelper.AnimationParam(2.0);
                helper.unifyAnimationDuration(param);

                observer.onComplete();
            };

            loader.load(modelFile, vmdFiles, onLoaded, onProgress, onError);
        });
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
        helper.animate(clock.getDelta());

        effect.render(scene, camera);
    }

    public void resize(int width, int height) {
        this.canvasWidth = width;
        this.canvasHeight = height;

        camera.aspect = (float) width / (float) height;
        camera.updateProjectionMatrix();

        renderer.setSize(width, height);
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void play() {
        pause = false;
        animate();
    }

}
