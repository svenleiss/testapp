package com.mhp.insideApp.webapp.httphelpers;

import org.springframework.web.servlet.view.RedirectView;

/**
 * Makes sure we're sending HTTP Code 303 instead of 302, since we expect clients to be HTTP 1.1 compatible.
 */
public class SeeOtherRedirectView extends RedirectView {

    public SeeOtherRedirectView(String url) {
        super(url);
        this.setHttp10Compatible(false);
    }
}
