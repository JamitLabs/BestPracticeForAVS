package com.jamitlabs.alexaconnect.libraries.alexa.interfaces.system;

import com.jamitlabs.alexaconnect.libraries.alexa.interfaces.AvsItem;

/**
 * Created by will on 4/8/2017.
 */

public class AvsSetEndpointItem extends AvsItem {
    String endpoint;
    public AvsSetEndpointItem(String token, String endpoint) {
        super(token);
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
