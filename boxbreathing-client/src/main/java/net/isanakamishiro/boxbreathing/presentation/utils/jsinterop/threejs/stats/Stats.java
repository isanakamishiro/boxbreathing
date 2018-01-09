/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.stats;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author isana
 */
@JsType(namespace = JsPackage.GLOBAL, isNative = true)
public class Stats {

    public HTMLElement domElement;

    public native void showPanel(int id);

    public native void update();

    public native void begin();

    public native void end();

}
