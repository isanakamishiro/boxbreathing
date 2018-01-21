/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.scenes;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Color;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Object3D;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.materials.Material;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.textures.Texture;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Scene extends Object3D {

//    public Object background;
    public Fog fog;
    public Material overrideMaterial;
    public boolean autoUpdate;

    @JsProperty(name = "background")
    public native void setBackgroundColor(Color color);

    @JsProperty(name = "background")
    public native void setBackgroundTexture(Texture texture);

}
