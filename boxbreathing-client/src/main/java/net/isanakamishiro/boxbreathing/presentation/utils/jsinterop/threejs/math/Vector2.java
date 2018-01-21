/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(isNative=true, namespace=PackageInfo.THREEJS_PACKAGE_NAME)
public class Vector2
{
	public double x;
	public double y;

	public Vector2() {}

	public Vector2( double x ) {}
	public Vector2( double x, double y ) {}

	public native void set( double x, double y);

}