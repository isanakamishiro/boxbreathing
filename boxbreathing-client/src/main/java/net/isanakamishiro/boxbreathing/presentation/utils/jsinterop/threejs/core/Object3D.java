/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.core;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Euler;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Matrix3;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Matrix4;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Quaternion;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.math.Vector3;

/**
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Object3D {

    /*
	 * Static Properties
     */
    /**
     * The default up direction for objects, also used as the default position
     * for DirectionalLight, HemisphereLight and Spotlight (which creates lights
     * shining from the top down). Set to (0, 1, 0) by default.
     */
    public static Vector3 DefaultUp;

    /**
     * The default setting for [page.matrixAutoUpdate matrixAutoUpdate] for
     * newly created Object3Ds.
     */
    public static boolean DefaultMatrixAutoUpdate;

    /**
     * Whether the object gets rendered into shadow map. Default is false.
     */
    public boolean castShadow;

    /**
     * Array with object's children. See Group for info on manually grouping
     * objects.
     */
    public Object3D[] children;

    /**
     * When this is set, it checks every frame if the object is in the frustum
     * of the camera before rendering the object. Otherwise the object gets
     * renderered every frame even if it isn't visible. Default is true.
     */
    public boolean frustumCulled;

    /**
     * The layer membership of the object. The object is only visible if it has
     * at least one layer in common with the Camera in use.
     */
    public Layers layers;

    /**
     * The local transform matrix.
     */
    public Matrix4 matrix;

    /**
     * When this is set, it calculates the matrix of position, (rotation or
     * quaternion) and scale every frame and also recalculates the matrixWorld
     * property. Default is Object3D.DefaultMatrixAutoUpdate (true).
     */
    public boolean matrixAutoUpdate;

    /**
     * The global transform of the object. If the Object3D has no parent, then
     * it's identical to the local transform .matrix.
     */
    public Matrix4 matrixWorld;

    /**
     * When this is set, it calculates the matrixWorld in that frame and resets
     * this property to false. Default is false.
     */
    public boolean matrixWorldNeedsUpdate;

    /**
     * This is passed to the shader and used to calculate the position of the
     * object.
     */
    public Matrix4 modelViewMatrix;

    /**
     * Optional name of the object (doesn't need to be unique). Default is an
     * empty string.
     */
    public String name;

    /**
     * This is passed to the shader and used to calculate lighting for the
     * object. It is the transpose of the inverse of the upper left 3x3
     * sub-matrix of this object's modelViewMatrix. The reason for this special
     * matrix is that simply using the modelViewMatrix could result in a
     * non-unit length of normals (on scaling) or in a non-perpendicular
     * direction (on non-uniform scaling). On the other hand the translation
     * part of the modelViewMatrix is not relevant for the calculation of
     * normals. Thus a Matrix3 is sufficient.
     */
    public Matrix3 normalMatrix3;

    /**
     * A Vector3 representing the object's local position. Default is (0, 0, 0).
     *
     */
    public Vector3 position;

    /**
     * Object's local rotation as a Quaternion.
     */
    public Quaternion quaternion;

    /**
     * Whether the material receives shadows. Default is false.
     */
    public boolean receiveShadow;

    /**
     * This value allows the default rendering order of scene graph objects to
     * be overridden although opaque and transparent objects remain sorted
     * independently. Sorting is from lowest to highest renderOrder. Default
     * value is 0.
     */
    public int renderOrder;

    /**
     * Object's local rotation (see Euler angles), in radians.
     */
    public Euler rotation;

    /**
     * The object's local .scale . Default is Vector3( 1, 1, 1 ).
     */
    public Vector3 scale;

    /**
     * This is used by the lookAt method, for example, to determine the
     * orientation of the result. Default is Object3D.DefaultUp - that is, ( 0,
     * 1, 0 ).
     */
    public Vector3 up;

    /**
     * n object that can be used to store custom data about the Object3D. It
     * should not hold references to functions as these will not be cloned.
     */
    public Object userData;

    /**
     * Object gets rendered if true. Default is true.
     */
    public boolean visible;

    public Object3D() {
    }

    @JsProperty
    public native int getId();

    @JsProperty
    public native void setOnAfterRender(RenderCallback callback);

    @JsProperty
    public native void setOnBeforeRender(RenderCallback callback);

    /**
     * Object's parent in the scene graph.
     *
     * @return
     */
    @JsProperty
    public native Object3D getParent();

    /**
     * UUID of this object instance. This gets automatically assigned, so this
     * shouldn't be edited.
     *
     * @return
     */
    @JsProperty
    public native String getUuid();

    public native void add(Object3D... objects);

//Methods
//EventDispatcher methods are available on this class.
//#
//.add ( object, ... )
//Adds object as child of this object. An arbitrary number of objects may be added.
//
//See Group for info on manually grouping objects.
//#
//.applyMatrix ( matrix )
//Applies the matrix transform to the object and updates the object's position, rotation and scale.
//#
//.applyQuaternion ( quaternion )
//Applies the rotation represented by the quaternion to the object.
//#
//.clone ( recursive )
//recursive -- if true, descendants of the object are also cloned. Default is true.
//
//Returns a clone of this object and optionally all descendants.
//#
//.copy ( object, recursive )
//recursive -- if true, descendants of the object are also copied. Default is true.
//
//Copy the given object into this object.
//#
//.getObjectById ( id )
//id -- Unique number of the object instance
//
//Searches through the object's children and returns the first with a matching id.
//Note that ids are assigned in chronological order: 1, 2, 3, ..., incrementing by one for each new object.
//#
//.getObjectByName ( name )
//name -- String to match to the children's Object3D.name property.
//
//Searches through the object's children and returns the first with a matching name.
//Note that for most objects the
//#
//.name is an empty string by default. You will have to set it manually to make use of this method.
//#
//.getObjectByProperty ( name, value )
//name -- the property name to search for.
//value -- value of the given property.
//
//Searches through the object's children and returns the first with a property that matches the value given.
//#
//.getWorldPosition ( optionalTarget )
//optionalTarget — (optional) target to set the result. Otherwise, a new Vector3 is instantiated.
//
//Returns a vector representing the position of the object in world space.
//#
//.getWorldQuaternion ( optionalTarget )
//optionalTarget — (optional) if specified, the result will be copied into this Quaternion, otherwise a new Quaternion will be created.
//
//Returns a quaternion representing the rotation of the object in world space.
//#
//.getWorldRotation ( optionalTarget )
//optionalTarget — (optional) if specified, the result will be copied into this Euler, otherwise a new Euler will be created.
//
//Returns the euler angles representing the rotation of the object in world space.
//#
//.getWorldScale ( optionalTarget )
//optionalTarget — (optional) if specified, the result will be copied into this Vector3, otherwise a new Vector3 will be created.
//
//Returns a vector of the scaling factors applied to the object for each axis in world space.
//#
//.getWorldDirection ( optionalTarget )
//optionalTarget — (optional) if specified, the result will be copied into this Vector3, otherwise a new Vector3 will be created.
//
//Returns a vector representing the direction of object's positive z-axis in world space.
//#
//.localToWorld ( vector )
//vector - A vector representing a position in local (object) space.
//
//Converts the vector from local space to world space.
//#
    /**
     * Rotates the object to face a point in world space.
     *
     * This method does not support objects with rotated and/or translated
     * parent(s).
     *
     * @param vector
     */
    public native void lookAt(Vector3 vector);

    /**
     * Rotates the object to face a point in world space.
     *
     * This method does not support objects with rotated and/or translated
     * parent(s).
     *
     * @param x
     * @param y
     * @param z
     */
    public native void lookAt(double x, double y, double z);

//#
//.raycast ( raycaster, intersects )
//Abstract (empty) method to get intersections between a casted ray and this object. Subclasses such as Mesh, Line, and Points implement this method in order to use raycasting.
//#
//.remove ( object, ... )
//Removes object as child of this object. An arbitrary number of objects may be removed.
//#
//.rotateOnAxis ( axis, angle )
//axis -- A normalized vector in object space.
//angle -- The angle in radians.
//
//Rotate an object along an axis in object space. The axis is assumed to be normalized.
//#
//.rotateOnWorldAxis ( axis, angle )
//axis -- A normalized vector in world space.
//angle -- The angle in radians.
//
//Rotate an object along an axis in world space. The axis is assumed to be normalized. Method Assumes no rotated parent.
//#
//.rotateX ( rad )
//rad - the angle to rotate in radians.
//
//Rotates the object around x axis in local space.
//#
//.rotateY ( rad )
//rad - the angle to rotate in radians.
//
//Rotates the object around y axis in local space.
//#
//.rotateZ ( rad )
//rad - the angle to rotate in radians.
//
//Rotates the object around z axis in local space.
//#
//.setRotationFromAxisAngle ( axis, angle )
//axis -- A normalized vector in object space.
//angle -- angle in radians
//
//Calls setFromAxisAngle( axis, angle ) on the .quaternion.
//#
//.setRotationFromEuler ( euler )
//euler -- Euler angle specifying rotation amount.
//Calls setRotationFromEuler( euler) on the .quaternion.
//#
//.setRotationFromMatrix ( m )
//m -- rotate the quaternion by the rotation component of the matrix.
//Calls setFromRotationMatrix( m) on the .quaternion.
//
//Note that this assumes that the upper 3x3 of m is a pure rotation matrix (i.e, unscaled).
//#
//.setRotationFromQuaternion ( q )
//q -- normalized Quaternion.
//
//Copy the given quaternion into .quaternion.
//#
//.toJSON ( q )
//Convert the object to JSON format.
//#
//.translateOnAxis ( axis, distance )
//axis -- A normalized vector in object space.
//distance -- The distance to translate.
//
//Translate an object by distance along an axis in object space. The axis is assumed to be normalized.
//#
//.translateX ( distance )
//Translates object along x axis by distance units.
//#
//.translateY ( distance )
//Translates object along y axis by distance units.
//#
//.translateZ ( distance )
//Translates object along z axis by distance units.
//#
//.traverse ( callback )
//callback - A function with as first argument an object3D object.
//
//Executes the callback on this object and all descendants.
//#
//.traverseVisible ( callback )
//callback - A function with as first argument an object3D object.
//
//Like traverse, but the callback will only be executed for visible objects. Descendants of invisible objects are not traversed.
//#
//.traverseAncestors ( callback )
//callback - A function with as first argument an object3D object.
//
//Executes the callback on all ancestors.
//#
//.updateMatrix ()
//Update the local transform.
//#
//.updateMatrixWorld ( force )
//Update the global transform of the object and its children.
//#
//.worldToLocal ( vector )
//vector - A world vector.
//
//Updates the vector from world space to local space.
//Source
//src/core/Object3D.js
}
