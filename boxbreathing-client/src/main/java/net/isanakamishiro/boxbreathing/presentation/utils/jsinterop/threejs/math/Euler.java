/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Euler {

    public String order;

    public double x;
    public double y;
    public double z;

    public Euler() {
    }

    public Euler(double x, double y, double z) {
    }

    public native void set(double x, double y, double z);

    public native void setFromVector3(Vector3 vector);

    public native Vector3 toVector3();

    public native boolean equals(Euler euler);
}
