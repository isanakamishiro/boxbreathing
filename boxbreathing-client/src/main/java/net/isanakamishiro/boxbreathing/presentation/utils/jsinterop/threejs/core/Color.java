/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;
import lombok.Builder;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(isNative = true, namespace = PackageInfo.THREEJS_PACKAGE_NAME)
public class Color {

    public boolean isColor;
    public double r;
    public double g;
    public double b;

    public Color() {
    }

    public Color(long color) {
    }

    public Color(double r, double g, double b) {
    }

    public native void add(Color color);

    public native void addColors(Color color1, Color color2);

    public native void copy(Color color);

    @JsOverlay
    public static Color valueOf(long color) {
        return new Color(color);
    }
}
