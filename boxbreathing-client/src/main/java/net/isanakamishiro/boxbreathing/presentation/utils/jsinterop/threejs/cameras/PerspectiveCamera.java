/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(isNative = true, namespace = PackageInfo.THREEJS_PACKAGE_NAME)
public class PerspectiveCamera extends Camera {

    public double aspect;
    public int far;
    public int filmGauge;
    public int filmOffset;
    public int focus;
    public int fov;
    public boolean isPerspectiveCamera;
    public double near;
    public int view;
    public int zoom;

    public PerspectiveCamera(int fov, double aspect, double near, int far) {
    }

    /**
     * Removes any offset set by the .setViewOffset method.
     */
    public native void clearViewOffset();

    /**
     * Returns the current vertical field of view angle in degrees considering
     * .zoom.
     *
     * @return
     */
    public native int getEffectiveFOV();

    /**
     * Returns the height of the image on the film. If .aspect is less than or
     * equal to one (portrait format), the result equals .filmGauge.
     *
     * @return
     */
    public native int getFilmHeight();

    /**
     * Returns the width of the image on the film. If .aspect is greater than or
     * equal to one (landscape format), the result equals .filmGauge.
     *
     * @return
     */
    public native int getFilmWidth();

    /**
     * Returns the focal length of the current .fov in respect to .filmGauge.
     *
     * @return
     */
    public native float getFocalLength();

    /**
     * Sets the FOV by focal length in respect to the current .filmGauge. By
     * default, the focal length is specified for a 35mm (full frame) camera.
     *
     */
    public native void setFocalLength(float focalLength);

    /**
     * fullWidth — full width of multiview setup fullHeight — full height of
     * multiview setup x — horizontal offset of subcamera y — vertical offset of
     * subcamera width — width of subcamera height — height of subcamera Sets an
     * offset in a larger frustum. This is useful for multi-window or
     * multi-monitor/multi-machine setups. For example, if you have 3x2 monitors
     * and each monitor is 1920x1080 and the monitors are in grid like this:
     *
     * +---+---+---+ | A | B | C | +---+---+---+ | D | E | F | +---+---+---+
     */
    public native void setViewOffset(int fullWidth, int fullHeight, int x, int y, int width, int height);

    /**
     * Updates the camera projection matrix. Must be called after any change of
     * parameters.
     *
     * @return
     */
    public native void updateProjectionMatrix();

    /**
     * Return camera data in JSON format.
     *
     * @return
     */
    public native String toJSON();

}
