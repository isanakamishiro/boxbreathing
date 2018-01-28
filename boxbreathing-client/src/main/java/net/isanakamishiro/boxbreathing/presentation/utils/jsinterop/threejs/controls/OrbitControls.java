/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.controls;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.Camera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Vector3;

/**
 * Orbit controls allow the camera to orbit around a target. To use this, as
 * with all files in the /examples directory, you will have to include the file
 * seperately in your HTML.
 *
 * <pre>
 * var renderer = new THREE.WebGLRenderer();
 * renderer.setSize( window.innerWidth, window.innerHeight );
 * document.body.appendChild( renderer.domElement );
 *
 * var scene = new THREE.Scene();
 *
 * var  camera = new THREE.PerspectiveCamera( 45, window.innerWidth / window.innerHeight, 1, 10000 );
 *
 * var controls = new THREE.OrbitControls( camera );
 *
 * //controls.update() must be called after any manual changes to the camera's transform
 * camera.position.set( 0, 20, 100 );
 * controls.update();
 *
 * function animate() {
 *
 * requestAnimationFrame( animate );
 *
 * // required if controls.enableDamping or controls.autoRotate are set to true
 * controls.update();
 *
 * renderer.render( scene, camera );
 *
 * }
 * </pre>
 *
 * @author isana
 */
@JsType(isNative = true, namespace = PackageInfo.THREEJS_PACKAGE_NAME)
public class OrbitControls {

    /**
     * Set to true to automatically rotate around the target. Note that if this
     * is enabled, you must call .update () in your animation loop.
     */
    public boolean autoRotate;

    /**
     * How fast to rotate around the target if # .autoRotate is true. Default is
     * 2.0, which equates to 30 seconds per rotation at 60fps. Note that if #
     * .autoRotate is enabled, you must call .update () in your animation loop.
     */
    public double autoRotateSpeed;

    /**
     * The damping inertia used if # .enableDamping is set to true. Note that
     * for this to work, you must call .update () in your animation loop.
     */
    public boolean dampingFactor;

    /**
     * The HTMLDOMElement used to listen for mouse / touch events. This must be
     * passed in the constructor; changing it here will not set up new event
     * listeners. Default is the whole document.
     */
    public HTMLElement domElement;

    /**
     * Whether or not the controls are enabled.
     */
    public boolean enabled;

    /**
     * Set to true to enable damping (inertia), which can be used to give a
     * sense of weight to the controls. Default is false. Note that if this is
     * enabled, you must call .update () in your animation loop.
     */
    public boolean enableDamping;

    /**
     * Enable or disable the use of keyboard controls.
     */
    public boolean enableKeys;

    /**
     * Enable or disable camera panning. Default is true.
     */
    public boolean enablePan;

    /**
     * Enable or disable horizontal and vertical rotation of the camera. Default
     * is true. Note that it is possible to disable a single axis by setting the
     * min and max of the polar angle or azimuth angle to the same value, which
     * will cause the vertical or horizontal rotation to be fixed at that value.
     */
    public boolean enableRotate;

    /**
     * Enable or disable zooming (dollying) of the camera.
     */
    public boolean enableZoom;

    /**
     * How fast to pan the camera when the keyboard is used. Default is 7.0
     * pixels per keypress.
     */
    public double keyPanSpeed;

    public double maxAzimuthAngle;

    public double maxDistance;

    public double maxPolarAngle;

    public double maxZoom;

    public double minAzimuthAngle;

    public double minDistance;

    public double minPolarAngle;

    public double minZoom;

    public Vector3 position0;

    public double rotateSpeed;

    public Vector3 target0;

    public double zoom0;

    public double zoomSpeed;

    public OrbitControls(Camera camera) {
    }

    public OrbitControls(Camera camera, HTMLElement domElement) {
    }

    public native void reset();

    public native void saveSate();

    public native void update();

}
