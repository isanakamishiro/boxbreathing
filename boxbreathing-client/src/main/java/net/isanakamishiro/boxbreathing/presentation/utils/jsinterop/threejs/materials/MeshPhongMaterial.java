/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.materials;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class MeshPhongMaterial extends Material {

    public MeshPhongMaterial() {
    }

    public MeshPhongMaterial(InitializeParameter p) {
    }

    @JsOverlay
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        InitializeParameter p;

        public Builder() {
            p = new InitializeParameter();
        }

        public MeshPhongMaterial build() {
            return new MeshPhongMaterial(p);
        }

        public Builder color(Color color) {
            p.color = color;
            return this;
        }
    }

    @JsType(namespace = JsPackage.GLOBAL, isNative = true, name = "Object")
    static class InitializeParameter {

        Color color;
    }

}
