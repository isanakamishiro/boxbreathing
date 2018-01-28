/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.presentation.mirai.ui;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author isana
 */
@Builder
@Data
@Accessors(fluent = true)
public class Progress {

    private final String resourceName;
    private final int loaded;
    private final int total;

    public float progress() {
        return (float) loaded / (float) total * 100;
    }
}
