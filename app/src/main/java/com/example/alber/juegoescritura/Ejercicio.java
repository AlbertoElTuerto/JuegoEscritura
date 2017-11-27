package com.example.alber.juegoescritura;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.widget.Button;

import java.util.Random;

/**
 * Created by alber on 11/5/2017.
 */

public class Ejercicio {
    Button[][] matriz = new Button[6][6]; //Atributo de una matriz de botones utilizada para el manejo de la interacción los botones (en matriz)en el activity_main.xml (XML/Layout).
    String alfabeto = "aábcdeéfghiíjklmnñoópqrstuúvwxyz"; //Alfabeto utilizado para fijar, aleatoriamente, el texto de la matriz de botones "matriz".
    Random rdm = new Random(); //Objeto utilizado para escoger una posición, dirección y sentido específicos para situar la palabra (por caracter) en la matriz de botones "matriz".
    int renPos; //Se utiliza para definir aleatoriamente la posición en y (renglones) en donde se empezará la palabra dentro de la matríz de botones.
    int colPos; //Se utiliza para definir aleatoriamente la posición en x (columnas) en donde se empezará la palabra dentro de la matríz de botones.
    int direccion; //Variable que defina en qué dirección y sentido aleatorio se escribe la palabra (horizontal y a la derecha o vertical y hacia abajo).

    //Maneja el color del botón btn.
    public void setButtonColor(Button btn, int buttonCase){
        if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial (a NARANJA).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
        } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado (a MORADO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
        } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado (a AMARILLO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
        } else if (buttonCase == 3) { //Caso en el que se cambia el color (a VERDE) del botón al final (cuando se encontró la palabra).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra.
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }

    //Maneja el color y habilitado del botón btn.
    public void setButtonState(Button btn, Boolean enabling, int buttonCase) {
        btn.setEnabled(enabling); //Habilita o deshabilita (utilizando el valor de "enabling") el botón recibido por el método.
        if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial (a NARANJA).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
        } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado (a MORADO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
        } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado (a AMARILLO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
        } else if (buttonCase == 3) { //Caso en el que se cambia el color (a VERDE) del botón al final (cuando se encontró la palabra).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra (a ROJO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }

    //Maneja el color y habilitado del botón btn mediante su posición en la matriz de botones "matriz".
    public void setButtonStateById(int buttonId, Boolean enabling, int buttonCase) {
        for (int i = 0; i < matriz.length; i++) { //Recorre la matriz por renglones...
            for (int j = 0; j < matriz[0].length; j++) { //Recorre la matriz por columnas...
                if (matriz[i][j].getId() == buttonId) { //Si el id del botón en la posición (i, j) de la matriz "matriz" es igual al id "buttonId" entregado al método...
                    matriz[i][j].setEnabled(enabling); //Habilita o deshabilita (utilizando el valor de "enabling") el botón recibido por el método.
                    if (buttonCase == 0) { //Caso en el que se cambia el color del botón al original/inicial (a NARANJA).
                        ViewCompat.setBackgroundTintList(matriz[i][j], ColorStateList.valueOf(Color.rgb(255, 152, 35))); //Cambio de color a NARANJA
                    } else if (buttonCase == 1) { //Caso en el que se cambia el color del botón presionado (a MORADO).
                        ViewCompat.setBackgroundTintList(matriz[i][j], ColorStateList.valueOf(Color.rgb(179, 64, 250))); //Cambio de color a MORADO
                    } else if (buttonCase == 2) { //Caso en el que se cambia el color los botones alrededor del botón presionado (a AMARILLO).
                        ViewCompat.setBackgroundTintList(matriz[i][j], ColorStateList.valueOf(Color.rgb(255, 215, 56))); //Cambio de color a AMARILLO
                    } else if (buttonCase == 3) { //Caso en el que se cambia el color (a VERDE) del botón al final (cuando se encontró la palabra).
                        ViewCompat.setBackgroundTintList(matriz[i][j], ColorStateList.valueOf(Color.rgb(124, 205, 2))); //Cambio de color a VERDE
                    } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra (a ROJO).
                        ViewCompat.setBackgroundTintList(matriz[i][j], ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
                    }
                }
            }
        }
    }

    //Maneja el color, habilitado y texto del botón btn.
    public void setButton(Button btn, Boolean enabling, String letter, int buttonCase) {
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
        } else if (buttonCase == 4) { //Caso en el que se cambia el color del botón presionado y este no contribuye a la construcción de la palabra (a ROJO).
            ViewCompat.setBackgroundTintList(btn, ColorStateList.valueOf(Color.rgb(255, 69, 69))); //Cambio de color a ROJO
        }
    }

    public void setMatrix(String word){ //Método que se usará para "reorganizar" la matriz de botones (del XML activity_main.xml, desde el MainActivity.java) con la palabra "word" escondida, letras aleatorias y un color definido.
        //Se llena la matríz de valores (letras) aleatorios (del String "alfabeto").
        for(int i = 0; i < matriz.length; i++){ //Se recorre la matriz "matris" por renglón...
            for(int j = 0; j < matriz[0].length; j++){ //Se recorre la matriz "matriz" por columna...
                setButton(matriz[i][j], true, String.valueOf(alfabeto.charAt(rdm.nextInt(alfabeto.length()))),0); //Cambia el texto del botón matriz[i][j] (en la posición (i, j) de la matriz "matriz") con un valor aleatorio de obtenido del String "alfabeto".
            }
        }
        renPos = rdm.nextInt(matriz.length); //Se definide aleatoriamente la posición (en renglón/en y) inical de dónde se empezará a "escribir" o situar la palabra dentro de la matriz "matriz".
        colPos = rdm.nextInt(matriz[0].length); //Se definide aleatoriamente la posición (en columnas/en x) inical de dónde se empezará a "escribir" o situar la palabra dentro de la matriz "matriz".
        direccion = rdm.nextInt(2); //Se escoge una dirección y sentido aleatorios para escribir la palabra dentro de la matriz de botones (el valor de 0 es para vertical y hacia abajo; y el valor de 1, para horizontal y hacia la derecha).
        if(direccion == 0){ //Si se escoge escribir la palabra verticalmente (direccion = 0) en la matriz...
            while(word.length() > matriz.length - renPos){ //Mientras que la palabra no quepa en la matriz debido a la posición en y (renglones) que se escogió...
                renPos = rdm.nextInt(matriz.length); //...se vuelve a escoger otra posición (en renglones) aleatoriamente.
            }
            for(int i = 0; i < word.length(); i++){ //Se cambia el texto de los botones en base a la dirección, sentido y botón inicial sobre la cual se situará la palabra.
                matriz[renPos + i][colPos].setText(String.valueOf(word.charAt(i))); //Se "escribe" la palabra en sentido vertical (de arriba hacia abajo) cambiando el texto de los botones, comenzando por el botón en la posición inicial (renPos, colPos) de la matriz "matriz".
            }
        } else { //Si se selecciona escribir la palabra en la matriz horizontalmente (direccion = 1) en la matriz...
            while(word.length() > matriz[0].length - colPos){ //Mientras que la palabra no quepa en la matriz debido a la posición en x (columnas) que se escogió...
                colPos = rdm.nextInt(matriz[0].length); //...se vuelce a escoger otra posición (en columnas) aleatoriamente.
            }
            for(int i = 0; i < word.length(); i++){ //Se cambia el texto de los botones en base a la dirección, sentido y botón inicial sobre la cual se situará la palabra.
                matriz[renPos][colPos + i].setText(String.valueOf(word.charAt(i))); //Se "escribe" la palabra en sentido horizontal (de izquierda a derecha) cambiando el texto de los botones, comenzando por el botón en la posición inicial (renPos, colPos) de la matriz "matriz".
            }
        }
    }
}
