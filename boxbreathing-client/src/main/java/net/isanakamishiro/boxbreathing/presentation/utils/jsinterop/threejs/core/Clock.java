/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Clock {

    public boolean autoStart;

    public Clock() {
    }

    public Clock(boolean autoStart) {
    }

    /**
     * Holds the time at which the clock's start method was last called.
     *
     * @return
     */
    @JsProperty
    public native double getStartTime();

    /**
     * Holds the time at which the clock's start, getElapsedTime or getDelta
     * methods were last called.
     *
     * @return
     */
    @JsProperty
    public native double getOldTime();

    /**
     * Keeps track of the total time that the clock has been running.
     *
     * @return
     */
    public native double getElapsedTime();

    /**
     * Whether the clock is running or not.
     */
    @JsProperty
    public native boolean isRunning();

    /**
     * Starts clock. Also sets the startTime and oldTime to the current time,
     * sets elapsedTime to 0 and running to true.
     */
    public native void start();

    /**
     * Stops clock and sets oldTime to the current time.
     */
    public native void stop();

    /**
     * et the seconds passed since the time oldTime was set and sets oldTime to
     * the current time. If autoStart is true and the clock is not running, also
     * starts the clock.
     *
     * @return
     */
    public native double getDelta();
}
