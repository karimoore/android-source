package io.bloc.android.blocly.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import io.bloc.android.blocly.R;

/**
 * Created by Kari on 10/14/2015.
 */
public class RobotoTextView extends TextView {

    private static Map<String, Typeface> sTypefaces = new HashMap<String, Typeface>();

    public RobotoTextView(Context context) {
        super(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractFont(attrs);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractFont(attrs);
    }

    void extractFont(AttributeSet attrs) {

        if (isInEditMode()) {
            return;
        }
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.Roboto, 0, 0);

        boolean isCondensed = typedArray.getBoolean(R.styleable.Roboto_condensed, false);
        int robotoStyleIndex = typedArray.getInteger(R.styleable.Roboto_robotoStyle, -1);
        typedArray.recycle();

        // get the index from the array of possible bit combinations  EX:Thin|Italic
        int[] intArray = getResources().getIntArray(R.array.bit_array);
        // given the bit combination - get the index which is mapped to the font file name
        int robotoFontIndex = java.util.Arrays.binarySearch(intArray, robotoStyleIndex);
        String[] stringArray = getResources().getStringArray(R.array.roboto_font_file_names);
        if (robotoFontIndex < 0 || robotoFontIndex >= stringArray.length) {
            return;
        }
        String robotoFont = stringArray[robotoFontIndex];
        if (isCondensed)
            robotoFont = robotoFont.replaceAll("Roboto", "RobotoCondensed");

        Typeface robotoTypeface = null;
        if (sTypefaces.containsKey(robotoFont)) {
            robotoTypeface = sTypefaces.get(robotoFont);
        } else {
            // #11
            robotoTypeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/RobotoTTF/" + robotoFont);
            sTypefaces.put(robotoFont, robotoTypeface);
        }
        // #12
        setTypeface(robotoTypeface);
    }
}
