package com.example.alber.juegoescritura;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by alber on 11/5/2017.
 */

public class Nodo2 extends AppCompatButton{

    public Nodo2(Context context) {
        this(context, null);
    }

    public Nodo2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Nodo2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String letter){
        this.setText(letter);
    }

}
