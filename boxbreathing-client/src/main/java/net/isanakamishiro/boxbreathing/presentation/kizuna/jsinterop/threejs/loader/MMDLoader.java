/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.kizuna.jsinterop.threejs.loader;

import de.pesse.gwt.jsinterop.threeJs.ThreeJsStatics;
import de.pesse.gwt.jsinterop.threeJs.loaders.OnErrorCallback;
import de.pesse.gwt.jsinterop.threeJs.loaders.OnProgressCallback;
import de.pesse.gwt.jsinterop.threeJs.objects.Mesh;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;

/**
 *
 * @author isana
 */
@JsType(namespace = ThreeJsStatics.PACKAGE_NAME, isNative = true)
public class MMDLoader {

    public native void load(String modelFile, String[] vmdFiles,
            OnModelLoadCallback callback,
            OnProgressCallback onProgressCallback,
            OnErrorCallback onErrorCallback);

    @JsFunction
    @FunctionalInterface
    public static interface OnModelLoadCallback {

        void onLoad(Mesh mesh);
    }
}
