/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.objects;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.BufferGeometry;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Geometry;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core.Object3D;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.materials.Material;

/**
 * Class representing triangular polygon mesh based objects. Also serves as a
 * base for other classes such as SkinnedMesh.
 *
 * <pre>
 * <b>Example</b>
 * var geometry = new THREE.BoxBufferGeometry( 1, 1, 1 );
 * var material = new THREE.MeshBasicMaterial( { color: 0xffff00 } );
 * var mesh = new THREE.Mesh(geometry, material );
 * scene.add( mesh );
 * </pre>
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Mesh extends Object3D {

    public Mesh() {
    }

    public Mesh(Geometry geometry) {
    }

    public Mesh(Geometry geometry, Material material) {
    }

    public Mesh(BufferGeometry geometry) {
    }

    public Mesh(BufferGeometry geometry, Material material) {
    }

}
