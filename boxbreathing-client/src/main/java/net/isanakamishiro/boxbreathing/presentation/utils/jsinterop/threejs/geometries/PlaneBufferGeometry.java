/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.geometries;

import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.BufferGeometry;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 * This is the BufferGeometry port of PlaneGeometry.
 *
 * Example var geometry = new THREE.PlaneBufferGeometry( 5, 20, 32 ); var
 * material = new THREE.MeshBasicMaterial( {color: 0xffff00, side:
 * THREE.DoubleSide} ); var plane = new THREE.Mesh( geometry, material );
 * scene.add( plane );
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class PlaneBufferGeometry extends BufferGeometry {

    /**
     * @param width Width along the X axis. Default is 1.
     * @param height Height along the Y axis. Default is 1.
     */
    public PlaneBufferGeometry(int width, int height) {
    }

    /**
     * @param width Width along the X axis. Default is 1.
     * @param height Height along the Y axis. Default is 1.
     * @param widhtSegments Optional. Default is 1.
     * @param heightSegments Optional. Default is 1.
     */
    public PlaneBufferGeometry(int width, int height, int widhtSegments, int heightSegments) {
    }

}
