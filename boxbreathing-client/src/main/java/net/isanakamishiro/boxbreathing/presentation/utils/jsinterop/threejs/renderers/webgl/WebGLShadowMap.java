/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.webgl;

import io.reactivex.Observable;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class WebGLShadowMap {

    public static enum Type {
        BasicShadowMap(0),
        PCFShadowMap(1),
        PCFSoftShadowMap(2);

        private final int typeIndex;

        private Type(int typeIndex) {
            this.typeIndex = typeIndex;
        }

        public int getTypeIndex() {
            return typeIndex;
        }

    }

    /**
     * If set, use shadow maps in the scene. Default is false.
     */
    public boolean enabled;

    /**
     * Enables automatic updates to the shadows in the scene. Default is true.
     * If you do not require dynamic lighting / shadows, you may set this to
     * false when the renderer is instantiated.
     */
    public boolean autoUpdate;

    /**
     * When set to true, shadow maps in the scene will be updated in the next
     * render call. Default is false. If you have disabled automatic updates to
     * shadow maps (shadowMap.autoUpdate = false), you will need to set this to
     * true and then make a render call to update the shadows in your scene.
     */
    public boolean needsUpdate;

    /**
     * Whether to render the opposite side as specified by the material into the
     * shadow map. When disabled, an appropriate shadow.bias must be set on the
     * light source for surfaces that can both cast and receive shadows at the
     * same time to render correctly. Default is true.
     */
    public boolean renderReverseSided;

    /**
     * Whether to treat materials specified as double-sided as if they were
     * specified as front-sided when rendering the shadow map. When disabled, an
     * appropriate shadow.bias must be set on the light source for surfaces that
     * can both cast and receive shadows at the same time to render correctly.
     * Default is true.
     */
    public boolean renderSingleSided;

//    @JsProperty
    int type;

    /**
     * Defines shadow map type (unfiltered, percentage close filtering,
     * percentage close filtering with bilinear filtering in shader) Options are
     * THREE.BasicShadowMap, THREE.PCFShadowMap (default),
     * THREE.PCFSoftShadowMap. See WebGLRenderer constants for details.
     *
     * @param type
     */
    @JsOverlay
    public final void setShadowType(Type type) {
        this.type = type.getTypeIndex();
    }

}
