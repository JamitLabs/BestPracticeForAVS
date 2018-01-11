package com.jamitlabs.alexaconnect.libraries.alexa.interfaces.playbackcontrol;

import com.jamitlabs.alexaconnect.libraries.alexa.data.Directive;
import com.jamitlabs.alexaconnect.libraries.alexa.interfaces.AvsItem;

/**
 * {@link Directive} to send a play command to any app playing media
 *
 * This directive doesn't seem applicable to mobile applications
 *
 * @author will on 5/31/2016.
 */

public class AvsMediaPlayCommandItem extends AvsItem {
    public AvsMediaPlayCommandItem(String token) {
        super(token);
    }
}
