/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers;

import elemental2.dom.HTMLCanvasElement;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import lombok.Builder;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.webgl.WebGLShadowMap;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class WebGLRenderer {

    public WebGLShadowMap shadowMap;

    public HTMLCanvasElement domElement;

    public WebGLRenderer() {
    }

    WebGLRenderer(WebGLRendererParameters parameters) {

    }

    @JsOverlay
    public static Builder builder() {
        return new Builder();
    }

    public native void setPixelRatio(float value);

    public native void setSize(int width, int height);



    /**
     * Builder
     */
    public static class Builder {

        WebGLRendererParameters parameters;

        public Builder() {
            parameters = new WebGLRendererParameters();
        }

        public WebGLRenderer build() {
            return new WebGLRenderer(parameters);
        }

        public Builder canvas(HTMLCanvasElement canvas) {
            parameters.canvas = canvas;
            return this;
        }

        public Builder depth(boolean depth) {
            parameters.depth = depth;
            return this;
        }

        public Builder premultipliedAlpha(boolean premultipliedAlpha) {
            parameters.premultipliedAlpha = premultipliedAlpha;
            return this;
        }

        public Builder loagrithmicDepthBuffer(boolean loagrithmicDepthBuffer) {
            parameters.loagrithmicDepthBuffer = loagrithmicDepthBuffer;
            return this;
        }

        public Builder antialias(boolean antialias) {
            parameters.antialias = antialias;
            return this;
        }

        public Builder preserveDrawingBuffer(boolean preserveDrawingBuffer) {
            parameters.preserveDrawingBuffer = preserveDrawingBuffer;
            return this;
        }

        public Builder precision(String precision) {
            parameters.precision = precision;
            return this;
        }

        public Builder alpha(boolean alpha) {
            parameters.alpha = alpha;
            return this;
        }

        public Builder context(Object context) {
            parameters.context = context;
            return this;
        }

        public Builder stencil(boolean stencil) {
            parameters.stencil = stencil;
            return this;
        }

    }

    @JsType(namespace = JsPackage.GLOBAL, isNative = true, name = "Object")
    public static class WebGLRendererParameters {

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

        WebGLRendererParameters() {
        }
    }
}
