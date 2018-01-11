package com.jamitlabs.alexaconnect.libraries.alexa.interfaces.playbackcontrol;

import com.jamitlabs.alexaconnect.libraries.alexa.data.Directive;
import com.jamitlabs.alexaconnect.libraries.alexa.interfaces.AvsItem;

/**
 * {@link Directive} to send a previous command to any app playing media
 *
 * This directive doesn't seem applicable to mobile applications
 *
 * @author will on 5/31/2016.
 */

public class AvsMediaNextCommandItem extends AvsItem {
    public AvsMediaNextCommandItem(String token) {
        super(token);
    }
}
