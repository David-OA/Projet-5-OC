package com.oconte.david.mynews.utils;

public class ConfigureText {

    /**
     * It's for configure the text the subsection is null.
     */

    public static String convertSectionNameForDisplay(String name){
        if (name != null){
            return name.replaceAll(";", " >");
        } else {
            return "";
        }
    }
}
