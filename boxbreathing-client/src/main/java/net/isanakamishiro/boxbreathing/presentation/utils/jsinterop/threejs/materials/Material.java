/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.materials;

import jsinterop.annotations.JsType;
import net.isanakamishiro.boxbreathing.presentation.utils.jsinterop.threejs.PackageInfo;

/**
 * Abstract base class for materials.
 *
 * Materials describe the appearance of objects. They are defined in a (mostly)
 * renderer-independent way, so you don't have to rewrite materials if you
 * decide to use a different renderer.
 *
 * The following properties and methods are inherited by all other material
 * types (although they may have different defaults).
 *
 * @author isana
 */
@JsType(namespace = PackageInfo.THREEJS_PACKAGE_NAME, isNative = true)
public class Material {

    /**
     * Sets the alpha value to be used when running an alpha test. The material
     * will not be renderered if the opacity is lower than this value. Default
     * is 0.
     */
    public double alphaTest;

    /**
     * Blending destination. Default is OneMinusSrcAlphaFactor. See the
     * destination factors constants for all possible values. The material's #
     * .blending must be set to CustomBlending for this to have any effect.
     */
    public Object blendDst;

    /**
     * The tranparency of the .blendDst. Default is null.
     */
    public double blendDstAlpha;

//    .blendEquation
//Blending equation to use when applying blending. Default is AddEquation. See the blending equation constants for all possible values.
//The material's
//#
//.blending must be set to CustomBlending for this to have any effect.
//#
//.blendEquationAlpha
//The tranparency of the .blendEquation. Default is null.
//#
//.blending
//Which blending to use when displaying objects with this material.
//This must be set to CustomBlending to use custom
//#
//.blendSrc ,
//#
//.blendDst or
//#
//.blendEquation .
//See the blending mode constants for all possible values. Default is NormalBlending.
//#
//.blendSrc
//Blending source. Default is SrcAlphaFactor. See the source factors constants for all possible values.
//The material's
//#
//.blending must be set to CustomBlending for this to have any effect.
//#
//.blendSrcAlpha
//The tranparency of the .blendSrc. Default is null.
//#
//.clipIntersection
//Changes the behavior of clipping planes so that only their intersection is clipped, rather than their union. Default is false.
//#
//.clippingPlanes
//User-defined clipping planes specified as THREE.Plane objects in world space. These planes apply to the objects this material is attached to. Points in space whose signed distance to the plane is negative are clipped (not rendered). See the WebGL / clipping /intersection example. Default is null.
//#
//.clipShadows
//Defines whether to clip shadows according to the clipping planes specified on this material. Default is false.
//#
//.colorWrite
//Whether to render the material's color. This can be used in conjunction with a mesh's
//#
//.renderOrder property to create invisible objects that occlude other objects. Default is true.
//#
//.customDepthMaterial
//Custom depth material to be used by this material when rendering to the depth map. When shadow-casting with a DirectionalLight or SpotLight, if you are (a) modifying vertex positions in the vertex shader, (b) using a displacement map, (c) using an alpha map with alphaTest, or (d) using a transparent texture with alphaTest, you must specify a customDepthMaterial for proper shadows. Default is undefined.
//#
//.customDistanceMaterial
//Same as customDepthMaterial, but used with PointLight. Default is undefined.
//#
//.defines
//Custom defines to be injected into the shader. These are passed in form of an object literal, with key/value pairs. { MY_CUSTOM_DEFINE: '' , PI2: Math.PI * 2 }. The pairs are defined in both vertex and fragment shaders. Default is undefined.
//#
//.depthFunc
//Which depth function to use. Default is LessEqualDepth. See the depth mode constants for all possible values.
//#
//.depthTest
//Whether to have depth test enabled when rendering this material. Default is true.
//#
//.depthWrite
//Whether rendering this material has any effect on the depth buffer. Default is true.
//
//When drawing 2D overlays it can be useful to disable the depth writing in order to layer several things together without creating z-index artifacts.
//#
//.fog
//Whether the material is affected by fog. Default is true.
//#
//.id
//Unique number for this material instance.
//#
//.isMaterial
//Used to check whether this or derived classes are materials. Default is true.
//
//You should not change this, as it used internally for optimisation.
//#
//.lights
//Whether the material is affected by lights. Default is true.
//#
//.name
//Optional name of the object (doesn't need to be unique). Default is an empty string.
//#
//.needsUpdate
//Specifies that the material needs to be updated at the WebGL level. Set it to true if you made changes that need to be reflected in WebGL.
//This property is automatically set to true when instancing a new material.
//#
//.opacity
//Float in the range of 0.0 - 1.0 indicating how transparent the material is. A value of 0.0 indicates fully transparent, 1.0 is fully opaque.
//If the material's
//#
//.transparent property is not set to true, the material will remain fully opaque and this value will only affect its color.
//Default is 1.0.
//#
//.overdraw
//Amount of triangle expansion at draw time. This is a workaround for cases when gaps appear between triangles when using CanvasRenderer. 0.5 tends to give good results across browsers. Default is 0.
//#
//.polygonOffset
//Whether to use polygon offset. Default is false. This corresponds to the GL_POLYGON_OFFSET_FILL WebGL feature.
//#
//.polygonOffsetFactor
//Sets the polygon offset factor. Default is 0.
//#
//.polygonOffsetUnits
//Sets the polygon offset units. Default is 0.
//#
//.precision
//Override the renderer's default precision for this material. Can be "highp", "mediump" or "lowp". Defaults is null.
//#
//.premultipliedAlpha
//Whether to premultiply the alpha (transparency) value. See WebGL / Materials / Transparency for an example of the difference. Default is false.
//#
//.dithering
//Whether to apply dithering to the color to remove the appearance of banding. Default is false.
//#
//.flatShading
//Define whether the material is rendered with flat shading. Default is false.
//#
//.side
//Defines which side of faces will be rendered - front, back or both. Default is THREE.FrontSide. Other options are THREE.BackSide and THREE.DoubleSide.
//#
//.transparent
//Defines whether this material is transparent. This has an effect on rendering as transparent objects need special treatment and are rendered after non-transparent objects.
//When set to true, the extent to which the material is transparent is controlled by setting it's
//#
//.opacity property.
//Default is false.
//#
//.type
//Value is the string 'Material'. This shouldn't be changed, and can be used to find all objects of this type in a scene.
//#
//.uuid
//UUID of this material instance. This gets automatically assigned, so this shouldn't be edited.
//#
//.vertexColors
//Defines whether vertex coloring is used. Default is THREE.NoColors. Other options are THREE.VertexColors and THREE.FaceColors.
//#
//.visible
//Defines whether this material is visible. Default is true.
//#
//.userData
//An object that can be used to store custom data about the Material. It should not hold references to functions as these will not be cloned.
//Methods
//EventDispatcher methods are available on this class.
//#
//.clone ( )
//Return a new material with the same parameters as this material.
//#
//.copy ( material )
//Copy the parameters from the passed material into this material.
//#
//.dispose ()
//This disposes the material. Textures of a material don't get disposed. These needs to be disposed by Texture.
//#
//.setValues ( values )
//values -- a container with parameters.
//Sets the properties based on the values.
//#
//.toJSON ( meta )
//meta -- object containing metadata such as textures or images for the material.
//Convert the material to three.js JSON format.
//#
//.update ()
//Call
//#
//.dispatchEvent ( { type: 'update' }) on the material.
//Source
//src/materials/Material.js

}
