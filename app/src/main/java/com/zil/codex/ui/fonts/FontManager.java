package com.zil.codex.ui.fonts;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {
    public static final String ROOT = "font/",
    FONTAWESOME = ROOT + "keyrune.ttf";
    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }    
}
