/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.lights;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class DirectionalLight extends Light {

    public DirectionalLightShadow shadow;

    public DirectionalLight() {
    }

    public DirectionalLight(Color color) {
    }

    public DirectionalLight(Color color, double intensity) {
    }

}
