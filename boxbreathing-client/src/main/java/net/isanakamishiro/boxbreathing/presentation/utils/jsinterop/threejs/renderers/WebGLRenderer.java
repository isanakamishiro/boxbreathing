/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers;

import elemental2.dom.HTMLCanvasElement;
import jsinterop.annotations.JsType;
import lombok.Builder;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class WebGLRenderer {

    public HTMLCanvasElement domElement;

    public WebGLRenderer() {
    }

    public WebGLRenderer(WebGLInitializeParameters parameters) {

    }

    public native void setPixelRatio(float value);

    public native void setSize(int width, int height);

    @Builder
    @JsType
    public static class WebGLInitializeParameters {

        public HTMLCanvasElement canvas;
        public boolean depth;
        public boolean premultipliedAlpha;
        public boolean loagrithmicDepthBuffer;
        public boolean antialias;
        public boolean preserveDrawingBuffer;
        public String precision;
        public boolean alpha;
        public Object context;
        public boolean stencil;
    }
}
