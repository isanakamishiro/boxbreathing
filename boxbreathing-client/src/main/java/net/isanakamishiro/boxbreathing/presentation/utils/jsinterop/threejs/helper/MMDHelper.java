/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.helper;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.Mesh;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME,  isNative = true)
public class MMDHelper {

    public native void add(Mesh mesh);

    public native void setAnimation(Mesh mesh);

    public native void setPhysics(Mesh mesh);

    public native void unifyAnimationDuration(AnimationParam param);

    public native void animate(double delta);

    @JsType
    public static class AnimationParam {

        public double afterglow;

        public AnimationParam(double afterglow) {
            this.afterglow = afterglow;
        }

    }
}
