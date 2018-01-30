/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.Camera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Matrix4;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Vector2;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.WebGLRenderTarget;

/**
 * his is used internally by PointLights for calculating shadows, and also
 * serves as a base class for the other shadow classes.
 *
 * <b>Example</b>
 *
 * <pre>
 *
 * //Create a WebGLRenderer and turn on shadows in the renderer
 * var renderer = new THREE.WebGLRenderer();
 * renderer.shadowMap.enabled = true;
 * renderer.shadowMap.type = THREE.PCFSoftShadowMap; // default THREE.PCFShadowMap
 *
 * //Create a PointLight and turn on shadows for the light
 * var light = new THREE.PointLight( 0xffffff, 1, 100 );
 * light.position.set( 0, 10, 0 );
 * light.castShadow = true;            // default false
 * scene.add( light );
 *
 * //Set up shadow properties for the light
 * light.shadow.mapSize.width = 512;  // default
 * light.shadow.mapSize.height = 512; // default
 * light.shadow.camera.near = 0.5;       // default
 * light.shadow.camera.far = 500      // default
 *
 * //Create a sphere that cast shadows (but does not receive them)
 * var sphereGeometry = new THREE.SphereBufferGeometry( 5, 32, 32 );
 * var sphereMaterial = new THREE.MeshStandardMaterial( { color: 0xff0000 } );
 * var sphere = new THREE.Mesh( sphereGeometry, sphereMaterial );
 * sphere.castShadow = true; //default is false
 * sphere.receiveShadow = false; //default
 * scene.add( sphere );
 *
 * //Create a plane that receives shadows (but does not cast them)
 * var planeGeometry = new THREE.PlaneBufferGeometry( 20, 20, 32, 32 );
 * var planeMaterial = new THREE.MeshStandardMaterial( { color: 0x00ff00 } )
 * var plane = new THREE.Mesh( planeGeometry, planeMaterial );
 * plane.receiveShadow = true;
 * scene.add( plane );
 *
 * //Create a helper for the shadow camera (optional)
 * var helper = new THREE.CameraHelper( light.shadow.camera );
 * scene.add( helper );
 *
 * </pre>
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class LightShadow {

    /**
     * Shadow map bias, how much to add or subtract from the normalized depth
     * when deciding whether a surface is in shadow. The default is 0. Very tiny
     * adjustments here (in the order of 0.0001) may help reduce artefacts in
     * shadows
     */
    public double bias;

    /**
     * The depth map generated using the internal camera; a location beyond a
     * pixel's depth is in shadow. Computed internally during rendering.
     */
    public WebGLRenderTarget map;

    /**
     * A Vector2 defining the width and height of the shadow map.
     *
     * Higher values give better quality shadows at the cost of computation
     * time. Values must be powers of 2, up to the
     * WebGLRenderer.capabilities.maxTextureSize for a given device, although
     * the width and height don't have to be the same (so, for example, (512,
     * 1024) is valid). The default is ( 512, 512 ).
     */
    public Vector2 mapSize;

    /**
     * Model to shadow camera space, to compute location and depth in shadow
     * map. Stored in a Matrix4. This is computed internally during rendering.
     */
    public Matrix4 matrix;

    /**
     * Setting this to values greater than 1 will blur the edges of the shadow.
     * High values will cause unwanted banding effects in the shadows - a
     * greater mapSize will allow for a higher value to be used here before
     * these effects become visible.
     *
     * Note that this has no effect if the WebGLRenderer.shadowMap.type is set
     * to BasicShadowMap.
     */
    public double radius;

    public LightShadow() {
    }

    /**
     * Create a new LightShadow. This is not intended to be called directly - it
     * is called internally by PointLight or used as a base class by other light
     * shadows.
     *
     * @param camera
     */
    public LightShadow(Camera camera) {
    }

    /**
     * The light's view of the world. This is used to generate a depth map of
     * the scene; objects behind other objects from the light's perspective will
     * be in shadow.
     */
//    @JsProperty(name = "camera")
//    public native Camera getCamera();

}
