/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.kizuna.jsinterop.threejs.effects;

import de.pesse.gwt.jsinterop.threeJs.ThreeJsStatics;
import de.pesse.gwt.jsinterop.threeJs.cameras.Camera;
import de.pesse.gwt.jsinterop.threeJs.renderers.WebGLRenderer;
import de.pesse.gwt.jsinterop.threeJs.scenes.Scene;
import jsinterop.annotations.JsType;

/**
 *
 * @author isana
 */
@JsType(isNative=true, namespace=ThreeJsStatics.PACKAGE_NAME)
public class OutlineEffect {

    public OutlineEffect(WebGLRenderer renderer) { }

    public native void render(Scene scene, Camera camera);
}
