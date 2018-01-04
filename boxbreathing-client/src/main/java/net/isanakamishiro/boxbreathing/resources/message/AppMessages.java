/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.isanakamishiro.boxbreathing.resources.message;

import com.google.gwt.i18n.client.Messages;

/**
 *
 * @author isana
 */
public interface AppMessages extends Messages {

    @DefaultMessage("Inhale for 4 seconds")
    String inhaleString();

    @DefaultMessage("Hold your lungs full for 4 seconds")
    String holdFromInhaleString();

    @DefaultMessage("Exhale for 4 seconds")
    String exhaleString();

    @DefaultMessage("Hold your lungs empty for 4 seconds")
    String holdFromExhaleString();

    @DefaultMessage("Start Box Breathing")
    String startBoxBreathing();

}
