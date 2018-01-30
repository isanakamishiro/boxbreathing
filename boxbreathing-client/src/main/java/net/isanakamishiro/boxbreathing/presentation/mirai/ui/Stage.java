/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.mirai.ui;

import elemental2.dom.HTMLCanvasElement;
import elemental2.dom.HTMLElement;
import io.reactivex.Observable;

/**
 *
 * @author isana
 */
public interface Stage extends Animation {

    public Observable<Progress> loadCharacter(String modelFile);

    public Observable<Progress> loadPose(String[] motionFiles);

    public Observable<Progress> loadFloor(String stageFile);

    public Observable<Progress> loadDome(String domeFile);

    public Observable<Progress> loadCamera(String cameraFile);

    public HTMLCanvasElement canvas();

    public HTMLElement stats();


    public void adjustCharacterPosition(double x, double y, double z);

    public void resizeCanvas(int width, int height);

}
