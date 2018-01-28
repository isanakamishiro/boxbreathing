/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader;

import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.core.XMLHttpRequest;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.core.Error;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.Camera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.Mesh;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.VmdObject;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class MMDLoader {

    public native void loadModel(String modelFile,
            OnModelLoadCallback callback,
            OnProgressCallback onProgressCallback,
            OnErrorCallback onErrorCallback);

    public native void loadVmds(String vmdFiles[],
            OnVmdLoadCallback callback,
            OnProgressCallback onProgressCallback,
            OnErrorCallback onErrorCallback);

    public native void load(String modelFile, String[] vmdFiles,
            OnModelLoadCallback callback,
            OnProgressCallback onProgressCallback,
            OnErrorCallback onErrorCallback);

    public native void pourVmdIntoModel(Mesh mesh, VmdObject vmd);

    public native void pourVmdIntoCamera(Camera camera, VmdObject vmd);

    @JsFunction
    @FunctionalInterface
    public static interface OnModelLoadCallback {

        void onLoad(Mesh mesh);
    }

    @JsFunction
    @FunctionalInterface
    public static interface OnVmdLoadCallback {

        void onLoad(VmdObject obj);
    }

    @JsFunction
    @FunctionalInterface
    public static interface OnProgressCallback {

        void onProgress(XMLHttpRequest xhr);
    }

    @JsFunction
    @FunctionalInterface
    public static interface OnErrorCallback {

        void onError(Error error);
    }

}
