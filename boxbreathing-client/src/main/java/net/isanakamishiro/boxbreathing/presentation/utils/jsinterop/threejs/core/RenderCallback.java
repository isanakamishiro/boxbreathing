/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.cameras.Camera;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.materials.Material;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects.Group;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.renderers.WebGLRenderer;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.scenes.Scene;

/**
 *
 * @author isana
 */
@JsType
@FunctionalInterface
public interface RenderCallback {

    public void call(WebGLRenderer renderer, Scene scene, Camera camera, Geometry geometry, Material material, Group group);
}
