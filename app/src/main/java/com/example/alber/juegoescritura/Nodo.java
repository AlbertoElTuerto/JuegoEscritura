package com.example.alber.juegoescritura;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.widget.Button;

/**
 * Created by alber on 11/5/2017.
 */

public class Nodo {
    Button btn;

    //Maneja el texto del botón btn.
    public void setButtonText(String letra){
        btn.setText(letra);
    }

    //Maneja el habilitado del botón btn.
    public void setButtonEnabling(Boolean enabling) {
        btn.setEnabled(enabling);
    }

    //Maneja el color del botón btn.
    public void setButtonColor(int buttonCase){
        if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
        } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
        } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
        } else if (buttonCase == 3) { //Caso en el que se cambia el color de botón al final (cuando se encontró la palabra).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }

    //Maneja el color y habilitado del botón btn.
    public void setButtonState(Boolean enabling, String letter, int buttonCase) {
        //btn.setText(letter);
        btn.setEnabled(enabling); //Habilita o deshabilita el botón recibido por el método.
        if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
        } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
        } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
        } else if (buttonCase == 3) { //Caso en el que se cambia el color de botón al final (cuando se encontró la palabra).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }

    //Maneja el color, habilitado y texto del botón btn.
    public void setButton(Boolean enabling, String letter, int buttonCase) {
        btn.setText(letter);
        btn.setEnabled(enabling); //Habilita o deshabilita el botón recibido por el método.
        if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
        } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
        } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
        } else if (buttonCase == 3) { //Caso en el que se cambia el color de botón al final (cuando se encontró la palabra).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }
}
