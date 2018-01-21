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
public class Vector3 
{
	public double x;
	public double y;
	public double z;

	public Vector3() {}

	public Vector3( double x ) {}
	public Vector3( double x, double y ) {}
	public Vector3( double x, double y, double z ) {}

	public native void set( double x, double y, double z );

}