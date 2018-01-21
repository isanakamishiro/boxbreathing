/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Object3D;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Matrix4;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Vector3;

/**
 *
 * @author isana
 */
@JsType(isNative = true, namespace = PackageInfo.THREEJS_PACKAGE_NAME)
public class Camera extends Object3D {

    /**
     * This is the inverse of matrixWorld. MatrixWorld contains the Matrix which
     * has the world transform of the Camera.
     */
    public Matrix4 matrixWorldInverse;

    /**
     * This is the matrix which contains the projection.
     */
    public Matrix4 projectionMatrix;

    /**
     * Returns a Vector3 representing the world space direction in which the
     * camera is looking.
     *
     * Note: This is not the camera’s positive, but its negative z-axis, in
     * contrast to getWorldDirection of the base class (Object3D).
     *
     * @return
     */
    public native Vector3 getWorldDirection();

    /**
     * Returns a Vector3 representing the world space direction in which the
     * camera is looking.
     *
     * Note: This is not the camera’s positive, but its negative z-axis, in
     * contrast to getWorldDirection of the base class (Object3D).
     *
     * If an optionalTarget vector is specified, the result will be copied into
     * this vector (which can be reused in this way), otherwise a new vector
     * will be created.
     *
     * @return
     */
    public native Vector3 getWorldDirection(Vector3 optionalTarget);

}
