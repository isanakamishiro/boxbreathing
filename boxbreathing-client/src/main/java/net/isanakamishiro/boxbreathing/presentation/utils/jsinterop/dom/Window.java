package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.dom;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The window object represents an open window in a browser.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "window")
public class Window {


    /**
     * Returns the Document object for the window
     *
     * @return The Document object for the window
     */
//    @JsProperty
//    public static native Document getDocument();

    /**
     * The innerWidth property returns the inner width of a window's content area.
     *
     * @return The inner width of a window's content area.
     */
    @JsProperty
    public static native int getInnerWidth();

    /**
     * The innerHeight property returns the inner height of a window's content area.
     *
     * @return The inner height of a window's content area.
     */
    @JsProperty
    public static native int getInnerHeight();

    /**
     * The outerWidth property returns the outer width of a window, including all interface elements (like
     * toolbars/scrollbars).
     *
     * @return The outer width of a window, including all interface elements (like toolbars/scrollbars).
     */
    @JsProperty
    public static native int getOuterWidth();

    /**
     * The outerHeight property returns the outer height of a window, including all interface elements (like
     * toolbars/scrollbars).
     *
     * @return The outer height of a window, including all interface elements (like toolbars/scrollbars).
     */
    @JsProperty
    public static native int getOuterHeight();

    /**
     * Displays an alert box with a specified message and an OK button.
     *
     * @param message The text to display in the alert box
     */
    public static native void alert(String message);

    /**
     * Decode a base-64 encoded string
     *
     * @param encoded Required. The string which has been encoded by the btoa() method
     * @return Decoded a base-64 encoded string
     */
    public static native String atob(String encoded);

    /**
     * Encodes a string in base-64.
     *
     * This method uses the "A-Z", "a-z", "0-9", "+", "/" and "=" characters to encode the string.
     *
     * @param str Required. The string to be encoded
     * @return A String, representing the base-64 encoded string
     */
    public static native String btoa(String str);

    @JsProperty
    public static native float getDevicePixelRatio();

    public static native int requestAnimationFrame( AnimationFrameCallback callback );

    @JsFunction
    @FunctionalInterface
    public interface AnimationFrameCallback
    {
    	public void execute();
    }

}
