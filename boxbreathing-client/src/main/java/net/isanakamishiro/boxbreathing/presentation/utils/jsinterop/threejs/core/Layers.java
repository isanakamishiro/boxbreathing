/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Layers {

    /**
     * Remove membership of this layer.
     *
     * @param layer an integer from 0 to 31
     */
    public native void disable(int layer);

    /**
     * Add membership of this layer.
     *
     * @param layer an integer from 0 to 31
     */
    public native void enable(int layer);

    /**
     * et membership to layer, and remove membership all other layers.
     *
     * @param layer an integer from 0 to 31
     */
    public native void set(int layer);

    /**
     * Returns true if this and the passed layers object are members of the same set of layers.
     *
     * @param layers a Layers object
     */
    public native void test(Layers layers);

    /**
     * Toggle membership of layer.
     *
     * @param layer an integer from 0 to 31
     */
    public native void toggle(int layer);

    @JsProperty
    public native int getMask();

}
