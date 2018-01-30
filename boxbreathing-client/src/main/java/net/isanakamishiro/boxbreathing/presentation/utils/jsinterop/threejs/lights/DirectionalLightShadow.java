/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.OrthographicCamera;

/**
 * This is used internally by DirectionalLights for calculating shadows.
 *
 * Unlike the other shadow classes, this uses an OrthographicCamera to calculate
 * the shadows, rather than a PerspectiveCamera. This is because light rays from
 * a DirectionalLight are parallel.
 *
 * <b>Example</b>
 * <pre>
 * //Create a WebGLRenderer and turn on shadows in the renderer
 * var renderer = new THREE.WebGLRenderer();
 * renderer.shadowMap.enabled = true;
 * renderer.shadowMap.type = THREE.PCFSoftShadowMap; // default THREE.PCFShadowMap
 *
 * //Create a DirectionalLight and turn on shadows for the light
 * var light = new THREE.DirectionalLight( 0xffffff, 1, 100 );
 * light.position.set( 0, 1, 0 ); 			//default; light shining from top
 * light.castShadow = true;            // default false
 * scene.add( light );
 *
 * //Set up shadow properties for the light
 * light.shadow.mapSize.width = 512;  // default
 * light.shadow.mapSize.height = 512; // default
 * light.shadow.camera.near = 0.5;    // default
 * light.shadow.camera.far = 500;     // default
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
 * </pre>
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class DirectionalLightShadow extends LightShadow {

    public DirectionalLightShadow() {
    }

    /**
     * The light's view of the world. This is used to generate a depth map of
     * the scene; objects behind other objects from the light's perspective will
     * be in shadow.
     *
     * The default is an OrthographicCamera with left and bottom set to -5,
     * right and top set to 5, the near clipping plane at 0.5 and the far
     * clipping plane at 500.
     * @return
     */
    @JsProperty(name = "camera")
    public native OrthographicCamera camera();
}
