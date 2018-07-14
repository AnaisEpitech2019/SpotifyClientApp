package com.spotifyclientapp.anais.spotifyclientapp.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {
    public static final String ROOT = "fonts/";
    public static final String FONTAWESOME = ROOT + "Font Awesome5-Solid-900.otf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
