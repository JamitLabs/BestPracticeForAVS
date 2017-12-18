package com.jamitlabs.alexavoiceserviceandroid.libraries.alexa.interfaces.errors;

import com.jamitlabs.alexavoiceserviceandroid.libraries.alexa.data.Directive;
import com.jamitlabs.alexavoiceserviceandroid.libraries.alexa.interfaces.AvsItem;

/**
 * Created by will on 6/26/2016.
 */

public class AvsResponseException extends AvsItem {
    Directive directive;
    public AvsResponseException(Directive directive) {
        super(null);
        this.directive = directive;
    }

    public Directive getDirective() {
        return directive;
    }
}
