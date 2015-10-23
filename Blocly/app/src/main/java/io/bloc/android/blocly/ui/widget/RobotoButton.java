package io.bloc.android.blocly.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Kari on 10/22/2015.
 * RobotoButton extends Button but is able to use the extractFont capabilities from
 * RobotoTextView - did not want to rewrite that code
 *
 */
public class RobotoButton extends Button {
    RobotoTextView rbTextview = new RobotoTextView(getContext());

    public RobotoButton(Context context) {
        super(context);
    }

    public RobotoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        rbTextview.extractFont(attrs, this);
    }

    public RobotoButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rbTextview.extractFont(attrs, this);
    }
}