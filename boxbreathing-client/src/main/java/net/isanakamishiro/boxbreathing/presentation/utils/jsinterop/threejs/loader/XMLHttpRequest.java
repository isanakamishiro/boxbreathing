/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.loader;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author isana
 */
@JsType(namespace = JsPackage.GLOBAL, isNative = true)
public class XMLHttpRequest {

    public int loaded;
    public int total;

}
