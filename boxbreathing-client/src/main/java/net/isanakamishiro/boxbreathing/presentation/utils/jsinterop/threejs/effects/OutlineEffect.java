/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.effects;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.Camera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.WebGLRenderer;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.scenes.Scene;

/**
 *
 * @author isana
 */
@JsType(isNative=true, namespace=PackageInfo.THREEJS_PACKAGE_NAME)
public class OutlineEffect {

    public OutlineEffect(WebGLRenderer renderer) { }

    public native void render(Scene scene, Camera camera);
}
