/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation;

import com.google.gwt.core.shared.GWT;
import gwt.material.design.client.constants.Color;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 *
 * @author isana
 */
public class StyleConfigurator {

    private BehaviorSubject<Color> bgColorSubject = BehaviorSubject.create();

    public void setBackgroundColorStyle(Color color) {
        bgColorSubject.onNext(color);
    }

    public Observable<Color> backgroundColorStyle() {
        return bgColorSubject;
    }
}
