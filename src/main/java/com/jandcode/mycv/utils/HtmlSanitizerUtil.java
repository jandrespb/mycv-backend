package com.jandcode.mycv.utils;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class HtmlSanitizerUtil {
    //    // Policy to allow only a limited set of HTML tags and attributes
//    private static final PolicyFactory POLICY = new HtmlPolicyBuilder()
//            .allowElements("b", "i", "u", "p", "br") // only basic formatting tags
//            .allowUrlProtocols("http", "https")
//            .allowAttributes("href").onElements("a") // let's allow links
//            .toFactory();

    // Policy to disallow all HTML tags and attributes
    private static final PolicyFactory POLICY = new HtmlPolicyBuilder()
            .toFactory();

    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }
        return POLICY.sanitize(input).trim();
    }
}
