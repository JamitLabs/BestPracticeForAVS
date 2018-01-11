package com.jamitlabs.alexaconnect.libraries.alexa.interfaces.playbackcontrol;

import com.jamitlabs.alexaconnect.libraries.alexa.data.Directive;
import com.jamitlabs.alexaconnect.libraries.alexa.interfaces.AvsItem;

/**
 * Directive to stop device playback
 *
 * {@link Directive} response item type parsed so we can properly
 * deal with the incoming commands from the Alexa server.
 *
 * @author will on 5/21/2016.
 */
public class AvsStopItem extends AvsItem {
    public AvsStopItem(String token) {
        super(token);
    }
}
