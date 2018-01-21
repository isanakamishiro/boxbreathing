/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Object3D;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Light extends Object3D {

    /**
     * Color of the light. Defaults to a new Color set to white, if not passed
     * in the constructor.
     */
    public Color color;

    /**
     * The light's intensity, or strength. In physically correct mode, the
     * product of color * intensity is interpreted as luminous intensity
     * measured in candela. Default - 1.0.
     */
    public double intensity;

    /**
     * Used to check whether this or derived classes are lights. Default is
     * true.
     *
     * You should not change this, as it used internally for optimisation.
     */
    public boolean isLight;

    public Light() {
    }

    public Light(Color color) {
    }

    public Light(Color color, double intensity) {
    }

}
