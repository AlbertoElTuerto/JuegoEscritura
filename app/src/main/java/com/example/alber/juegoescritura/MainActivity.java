package com.example.alber.juegoescritura;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numEjercicios = 28; //Numero de ejercicios (28).
    int currentExercise = 0; //Contador utilizado para determinar la palabra, audio del ejercicio stepSize (suma de puntos por letra acertada) para el puntaje.
    String[] palabras = new String[numEjercicios]; //Arreglo de Strings/palabras (de cada ejercicio) del juego "Sopa de Letras".
    float[] puntaje = new float[numEjercicios]; //Arreglo de floats utilizado para guardar el punaje más alto en cada ejercicio.
    float puntosAcum; //Puntos acumulados por letra acertada de la palabra al momento de hacer el ejercicio número "currentExcercise".
    int[] audios = new int[numEjercicios]; //Arreglo de integers (IDs de audios o grabaciones) de las palabras de cada ejercicio (en el respectivo orden de las palabras en el arreglo "palabras").
    Ejercicio ejercicio = new Ejercicio(); //Este objeto tiene un atributo de una matriz (llamado "matriz") de botones (Button[6][6]).
    String buttonName; //String que guarda el nombre del botón en el activity_main.xml para luego obtener su ID y asignar este a un objeto tipo botón en el MainActivity.java
    int matrixSize = ejercicio.matriz.length; //Variable para conocer el tamaño de renglón (igual al de las columnas) de la matriz "matriz"(6x6).
    int[] idClick = new int[matrixSize]; //Arreglo que guarda los IDs de los botones presionados (matrixSize = 6 es el tamaño máximo debido a que no se pueden preionar más de 6 botones en la matriz de botones por ejercicio (en caso de querer meter palabras de más de 6 letras, es necesario agregar más botones al activity_main.xml y cambiar el valor de las variables correspondientes dentro del MainActivity.java y Ejercicio.java)).
    int currentButton = -1; //Contador utilizado para indicar el número de botones presionados (si currentButton == -1, no se tiene ningún botón presionado | si currentButton == 0, se ha presionado un solo botón).
    String wordInput = ""; //String que almacena lo que el usuario ha tecleado por medio de la matriz de botones en el activity_main.xml.
    SpannableStringBuilder coloredText; //Objeto utilizado para colorear los caracteres de la palabra (del TextView "textViewWord") del ejercicio a modo que se presionan los botones de la matriz del activity_main.xml
    ForegroundColorSpan naranja = new ForegroundColorSpan(Color.rgb(255, 152, 35)); //(SIN UTILIZAR) Objeto utilizado para cambiar el color de una letra a NARANJA.
    ForegroundColorSpan morado = new ForegroundColorSpan(Color.rgb(179, 64, 250)); //Objeto utilizado para cambiar el color de una letra a MORADO.
    ForegroundColorSpan amarillo = new ForegroundColorSpan(Color.rgb(255, 215, 56)); //(SIN UTILIZAR) Objeto utilizado para cambiar el color de una letra a AMARILLO.
    ForegroundColorSpan verde = new ForegroundColorSpan(Color.rgb(124, 205, 2)); //Objeto utilizado para cambiar el color de una letra a VERDE.
    ForegroundColorSpan rojo = new ForegroundColorSpan(Color.rgb(255, 69, 69)); //Objeto utilizado para cambiar el color de una letra a ROJO.
    ForegroundColorSpan blanco = new ForegroundColorSpan(Color.rgb(255, 255, 255)); //(SIN UTILIZAR) Objeto utilizado para cambiar el color de una letra a BLANCO.
    int direccion; //Variable que especifica (definida por la interacción del usuario con la aplicación) el sentido (y dirección) en la que el usuario está "encontrando" la palabra dentro de la matriz de botones del activity_main.xml (si es 0, el sentido es vertical | si es 1, el sentido es horizontal).
    int a; //Índice al que se le asigna la posición en renglones del botón presionado en el activity_main.xml.
    int b; //Índice al que se le asigna la posición en columnas del botón presionado en el activity_main.xml.
    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView textViewWord = (TextView) findViewById(R.id.textViewWord); //Asignación del "TextViewWord" (TextView) a un objeto en el MainActivity.java llamado "textViewWord".
        final RatingBar estrellas = (RatingBar) findViewById(R.id.pointsBar); //Asignación del "pointsBar" (RatingBar) a un objeto en el MainActivity.java llamado "estrellas".
        final TextView pointProof = (TextView) findViewById(R.id.pointProof); //(BORRAR) Utilizado para mostrar que se esté guardadno la puntuación de manera correcta.
        pointProof.setVisibility(View.GONE); //

        //---------- PALABRAS ---------- (Definición de las palabras a utilizar en los ejercicios)//

        palabras[0] = "río";
        palabras[1] = "mar";
        palabras[2] = "sol"; //
        palabras[3] = "pez"; //
        palabras[4] = "eco"; //
        palabras[5] = "pie"; //
        palabras[6] = "oro"; //
        palabras[7] = "pino";
        palabras[8] = "pozo";
        palabras[9] = "lago";
        palabras[10] = "pato";
        palabras[11] = "león";
        palabras[12] = "bote";
        palabras[13] = "agua"; //
        palabras[14] = "perro";
        palabras[15] = "pasto";
        palabras[16] = "oveja";
        palabras[17] = "cielo";
        palabras[18] = "avión";
        palabras[19] = "cueva"; //
        palabras[20] = "fruta";
        palabras[21] = "piedra";
        palabras[22] = "conejo";
        palabras[23] = "planta";
        palabras[24] = "trueno";
        palabras[25] = "granja";
        palabras[26] = "volcán";
        palabras[27] = "jirafa";

        //---------- AUDIOS ---------- (Grabaciones de las palabras correspondientes para los ejercicios)

        audios[0] = R.raw.rio;
        audios[1] = R.raw.mar;
        audios[2] = R.raw.sol;
        audios[3] = R.raw.pez;
        audios[4] = R.raw.eco;
        audios[5] = R.raw.pie;
        audios[6] = R.raw.oro;
        audios[7] = R.raw.pino;
        audios[8] = R.raw.pozo;
        audios[9] = R.raw.lago;
        audios[10] = R.raw.pato;
        audios[11] = R.raw.leon;
        audios[12] = R.raw.bote;
        audios[13] = R.raw.agua;
        audios[14] = R.raw.perro;
        audios[15] = R.raw.pasto;
        audios[16] = R.raw.oveja;
        audios[17] = R.raw.cielo;
        audios[18] = R.raw.avion;
        audios[19] = R.raw.cueva;
        audios[20] = R.raw.fruta;
        audios[21] = R.raw.piedra;
        audios[22] = R.raw.conejo;
        audios[23] = R.raw.planta;
        audios[24] = R.raw.trueno;
        audios[25] = R.raw.granja;
        audios[26] = R.raw.volcan;
        audios[27] = R.raw.jirafa;

        //---------- PALABRA DEL EJERCICIO ----------//

        textViewWord.setText(palabras[currentExercise]); //Se fija el texto del TextView "textViewWord" con la primera palabra del ejercicio con currentExcercise = 0.
        coloredText = new SpannableStringBuilder(palabras[currentExercise]); // Se crea un objeto utilizado para el cambio de colores con la palabra (String) del ejercicio actual (inicialmente se empieza con la palabra del currentExcercise = 0).

//----------Asignación de botones en el XML al JAVA----------

        //----------Arreglo de botones----------//

        //Nota: el procedimietno que sigue podría resultar costoso en términos de eficiencia (dado el caso: asignar los botones de manera exhaustiva).
        for (int i = 0; i < matrixSize; i++) { //Asignar el valor del arreglo de botones en el activity_main.xml a los botones del atributo "matriz" de la clase Ejercicio.
            for (int j = 0; j < matrixSize; j++) { //Para lo anterior se recorre la matriz de botones "matriz" para dicha asignación.
                buttonName = "btn" + i + j; //Se prepara el nombre del botón en el activity_main.xml para luego utilizarlo...
                final int idButtonName = getResources().getIdentifier(buttonName, "id", getPackageName()); //...y generar el id a partir del nombre creado anteriormente.
                ejercicio.matriz[i][j] = (Button) findViewById(idButtonName); //Asignación de los botones al arreglo de botones "matriz" de la clase "Ejercicio".

                ejercicio.matriz[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                //--------------------Interacción con los bototnes--------------------

                        //----------Definición de "a" (posición en renglones) y de "b" (posición en columnas) del botón presionado----------
                                //(a y b son utilizados para el manejo del comportamiento de botones por posición en la matriz)
                        for (int i = 0; i <= 5; i++) {
                            for (int j = 0; j <= 5; j++) {
                                if (ejercicio.matriz[i][j] == v) {
                                    a = i;
                                    b = j;
                                }
                            }
                        }

//----------Caso 1: No hay ningún botón presionado en la matriz----------

                        if ((currentButton == -1) && (idClick[currentButton + 1] == 0)) { //Si currentButton (contador) es cero y idClick[currentButton] (registro de botones presionados) es cero, quiere decir que no hay nada presionado en la matriz de botones.
                            currentButton++; //"currentButton" ahora es igual a 0 (quiere decir que se tiene un botón presionado en la matriz).
                            idClick[currentButton] = v.getId(); //Se guarda el ID del primer botón presionado en el arreglo idClick.
                            wordInput = wordInput + ejercicio.matriz[a][b].getText().toString(); //Se concatena lo que hay en wordInput (acutalmente vacío) más la letra del botón presionado...
                            for (int i = 0; i < matrixSize; i++) { //Con estos ciclos de for's anidados se deshabilitan todos los botones del arreglo (actualmente de color NARANJA).
                                for (int j = 0; j < matrixSize; j++) {
                                    ejercicio.matriz[i][j].setEnabled(false); //Deshabilita el botón en la posición (i, j) de la matriz "matriz" de "ejercicio".
                                }
                            }
                            if((a ==  matrixSize - 1) && (b == matrixSize - 1) && (wordInput.charAt(0) != (palabras[currentExercise].charAt(currentButton)))){ //Si el botón presionado es el encontrado en la esquina inferior izquierda y no contribuye a la construcción de la palabra.
                                ejercicio.setButtonState(ejercicio.matriz[a][b], true, 4); //Hablilitar y cambiar color del botón presionado a ROJO.
                                coloredText.clearSpans();               //Se quita cualquier alteración de color al texto que se le asigna al TextView "textViewWord"...
                                coloredText.setSpan(rojo, 0, 1, 0);     //...se cambia de color la primera letra de la palabra en el texto a ROJO...
                                textViewWord.setText(coloredText);      //...y se le asigna el texto "coloredText" (tipo SpannableStringBuilder) al TextView "textViewWord".
                            } else {
                                if((a ==  matrixSize - 1) && (b == matrixSize - 1) && (wordInput.charAt(0) == (palabras[currentExercise].charAt(currentButton)))){ //Si el botón presionado es el encontrado en la esquina inferior izquierda y contribuye a la construcción de la palabra.
                                    ejercicio.setButtonState(ejercicio.matriz[a][b], true, 4); // Hablilitar y cambiar color del botón presionado a ROJO.
                                    coloredText.clearSpans();               //
                                    coloredText.setSpan(morado, 0, 1, 0);   //Cambio de color de la primera letra de la palabra (en el TextView "textViewWord") a MORADO.
                                    textViewWord.setText(coloredText);      //
                                } else {
                                    if (ejercicio.matriz[a][b].getText().charAt(0) != palabras[currentExercise].charAt(currentButton)) { //El botón presionado (en cualquier parte de la matriz) no contribuye a la construcción de la palabra.
                                        ejercicio.setButtonState(ejercicio.matriz[a][b], true, 4);
                                        coloredText.clearSpans();               //
                                        coloredText.setSpan(rojo, 0, 1, 0);     //Cambio de color de la primera letra de la palabra (en el TextView "textViewWord") a ROJO.
                                        textViewWord.setText(coloredText);      //
                                    } else {
                                        if (wordInput.equals(palabras[currentExercise])) { //Caso en el que la palabra completa solo tiene una letra y, por lo tanto, el ejercicio se resolvió correctamente ().
                                            ejercicio.setButtonState(ejercicio.matriz[a][b], false, 3); //Deshablilitar y cambiar color del botón presionado a VERDE.
                                            coloredText.clearSpans();               //
                                            coloredText.setSpan(verde, 0, 1, 0);    //Cambio de color de la primera letra de la palabra (en el TextView "textViewWord") a VERDE.
                                            textViewWord.setText(coloredText);      //
                                        } else {
                                            //El botón presionado contribuye a la construcción de la palabra pero sigue sin construirse por completo.
                                            ejercicio.setButtonState(ejercicio.matriz[a][b], true, 1); //Hablilitar y cambiar color del botón presionado a MORADO.
                                            coloredText.clearSpans();               //
                                            coloredText.setSpan(morado, 0, 1, 0);   //Cambio de color de la primera letra de la palabra (en el TextView "textViewWord") a MORADO.
                                            textViewWord.setText(coloredText);      //

                                            //----------Hablilitar y cambiar color de los botones alrededor del presionado a AMARILLO.
                                            if (b + 1 < matrixSize) { //Caso en el que se trata el botón a la derecha del presionado.
                                                ejercicio.setButtonState(ejercicio.matriz[a][b + 1], true, 2);
                                            }
                                            if (a + 1 < matrixSize) { //Caso en el que se trata el botón de abajo del presionado.
                                                ejercicio.setButtonState(ejercicio.matriz[a + 1][b], true, 2);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {

//----------Caso 2: Se presiona otra vez el primer botón presionado previamente----------//

                            if ((currentButton == 0) && (v.getId() == idClick[currentButton])) { //Si currentButton (botón presionado recientemente) es 0 y el id del botón presionado anteriormente es el mismo que el presionado actualmente...
                                for (int i = 0; i < matrixSize; i++) {              //
                                    for (int j = 0; j < matrixSize; j++) {          //
                                        ejercicio.matriz[i][j].setEnabled(true);    //Se habilitan todos los botones de la matriz.
                                    }                                               //
                                }                                                   //
                                ejercicio.setButtonColor(ejercicio.matriz[a][b], 0); //El botón presionado se cambia de color a NARANJA.
                                if((a < matrixSize - 1) && (b < matrixSize -1)){ //Si el botón presionado se en cualquier parte de la matriz excepto en el lado inferior, el lado derecho y la esquina inferior derecha...
                                    ejercicio.setButtonColor(ejercicio.matriz[a + 1][b], 0); //El botón justo abajo del presionado se cambia de color a NARANJA.
                                    ejercicio.setButtonColor(ejercicio.matriz[a][b + 1], 0); //El botón justo a la derecha del presionado se cambia de color a NARANJA.
                                } else{
                                    if(a != b) { //Si el botón presionado se encuentra o en el lado inferior o el lado derecho de la matriz (a excepción del botón en la esquina inferior derecha).
                                        if (a == matrixSize - 1) { //Si el botón se encuentra en el lado inferior de la matriz...
                                            ejercicio.setButtonColor(ejercicio.matriz[a][b + 1], 0); //El botón justo a la derecha del presionado se cambia de color a NARANJA.
                                        } else {
                                            if (b == matrixSize - 1) { //Si el botón se encuentra en el lado derecho de la matriz...
                                                ejercicio.setButtonColor(ejercicio.matriz[a + 1][b], 0); //El botón justo abajo del presionado se cambia de color a NARANJA.
                                            }
                                        }
                                    }
                                }
                                idClick[currentButton] = 0; //Se borra el ID del botón presionado al inicio del ejercicio del registro (arreglo) de botones presionados (idClick).
                                wordInput = ""; //Se fija el texto wordInput a cadena vacía para luego.
                                coloredText.clearSpans();               //Se quita cualquier alteración de color al texto que se le asigna al TextView "textViewWord"...
                                textViewWord.setText(coloredText);      //se le asigna el texto "coloredText" (tipo SpannableStringBuilder) al TextView "textViewWord" (en otras palabras, el texto del TextView se pone de color blanco o sin color).
                                currentButton--; //El contador se reinicia a "currentButton" = -1.
                            } else {

//----------Caso 3: Se presiona un botón cualquiera que no sea el mismo al primero presionado----------

                                if ((currentButton >= 0) && (v.getId() != idClick[currentButton])) { //Si se presiona un botón de la matriz cualquira y no es el primero en presionarse, ni el mismo presionado al inicio.
                                    currentButton++; //El contador crece por 1 (ahora solo puede darse que currentButon > 0).
                                    idClick[currentButton] = v.getId(); //Se guarda el ID del botón presionado en el arreglo idClick.
                                    wordInput = wordInput + ejercicio.matriz[a][b].getText().toString(); //Se concatena la letra del botón presionado a lo que hay en el String "wordInput".

                                    //----------Se DEFINE el valor de "direccion" y se deshabilitan y cambian de color a naranja los botones que ya no están disponibles para interactuar.

                                        //Definición de la variable "direccion" que especifica el sentido en el que el usuario está "encontrando" la palabra.
                                    if(currentButton == 1){ //Si el siguiente botón presionado (después del primero, cuando currentButton = 0)...
                                        if(a == 0){ //...se encuentra en el primer renglón de la matriz de botones...
                                            if(ejercicio.matriz[a][b - 1].getId() == idClick[currentButton - 1]){ //...y el botón a la izquierda del presionado es igual al presionado con anterioridad...
                                                direccion = 1; //...el sentido con el que es usuario está "encontrando" la palabra es horizontal.
                                            } else{ //...y el botón que está arriba del presionado es igual al presionado con anterioridad...
                                                direccion = 0; //...el sentido con el que es usuario está "encontrando" la palabra es vertical.
                                            }
                                        } else { //...se encuentra en cualquier parte de la matriz de botones que no sea en el primer renglón...
                                            if (ejercicio.matriz[a - 1][b].getId() == idClick[currentButton - 1]) { //...y el botón que está arriba del presionado es igual al presionado con anterioridad...
                                                direccion = 0; //...el sentido con el que es usuario está "encontrando" la palabra es vertical.
                                            } else { //...y el botón a la izquierda del presionado es igual al presionado con anterioridad...
                                                direccion = 1; //...el sentido con el que es usuario está "encontrando" la palabra es horizontal.
                                            }
                                        }

                                        //Se cambian de color a NARANJA y se deshabilitan los botones que ya no pueden ser presionados.
                                        if(direccion == 0){ //Si el sentido con el que es usuario está "encontrando" la palabra es vertical...
                                            if (b < matrixSize - 1) { //Si el botón presionado se encuentra en cualquier lugar que no sea sobre el lado derecho de la matriz de botones...
                                                ejercicio.setButtonState(ejercicio.matriz[a - 1][b + 1], false, 0); //...se deshabilita y se cambia de color (a NARANJA) el botón justo arriba y a la derecha del presionado.
                                            }
                                        } else{ //Si el sentido con el que es usuario está "encontrando" la palabra es horizontal...
                                            if (a < matrixSize - 1) { //Si el botón presionado se encuentra en cualquier lugar que no sea sobre el lado inferior de la matriz de botones...
                                                ejercicio.setButtonState(ejercicio.matriz[a + 1][b - 1], false, 0); //...se deshabilita y se cambia de color (a NARANJA) el botón justo abajo y a la izquierda del presionado.
                                            }
                                        }
                                    }

                                    // EL EJERCICIO SE RESOLVIÓ CORRECTAMENTE!!!
                                    if (wordInput.equals(palabras[currentExercise])) { //Si el botón presionado contribuye a la construcción de la palabra y se construyó toda la palabra de forma correcta...
                                        for (int i = 0; i <= currentButton; i++) {
                                            ejercicio.setButtonStateById(idClick[i], false, 3); //...se deshabilitan y cambian el color de los botones a VERDES (por ID)...
                                            coloredText.clearSpans();                                               //
                                            coloredText.setSpan(verde, 0, palabras[currentExercise].length(), 0);   //...y se cambia de color (a VERDE) las letras de toda la palabra (dentro del TextView "textViewWord").
                                            textViewWord.setText(coloredText);                                      //
                                            audio.release();
                                            audio = MediaPlayer.create(MainActivity.this, audios[currentExercise]);
                                            audio.start();
                                            //POSIBLE interacción para pasar al siguiente ejercicio...
                                        }
                                    } else { //Si la palabra sigue sin construirse por completo...
                                        for (int i = 0; i < currentButton; i++) {
                                            ejercicio.setButtonStateById(idClick[i], false, 1); //...se deshabilitan y se cambian de color todos los botones previamente (excepto el actualmente presionado) oprimidos a MORADO (proceso hecho por medio de IDs).
                                        }
                                        if (ejercicio.matriz[a][b].getText().charAt(0) != palabras[currentExercise].charAt(currentButton)) { //Si el botón presionado no contribuye a la construcción de la palabra...
                                            ejercicio.setButtonState(ejercicio.matriz[a][b], true, 4); //...el botón se habilita y cambia de color a ROJO.
                                            coloredText.clearSpans();                                       //
                                            coloredText.setSpan(morado, 0, currentButton, 0);               //Cambio de todas las letras (previa y correctamente encontradas) de la palabra (en el TextView "textViewWord") a MORADO.
                                            coloredText.setSpan(rojo, currentButton, currentButton + 1, 0); //Cambio de color de la letra (reciente e incorrectamente encontrada) de la palabra (en el TextView "textViewWord") a ROJO.
                                            textViewWord.setText(coloredText);                              //
                                        } else {
                                            if((ejercicio.matriz[a][b].getText().charAt(0) == palabras[currentExercise].charAt(currentButton)) && (((a == matrixSize - 1) && (direccion == 0)) || ((b == matrixSize - 1) && (direccion == 1)))){ //Si la palabra se está construyendo correctamente, pero se llega a los límites de la matriz de botones (lado inferior o derecho)...
                                                ejercicio.setButtonState(ejercicio.matriz[a][b], true, 4); //...el botón se habilita y cambia de color a ROJO.
                                                coloredText.clearSpans();                               //
                                                coloredText.setSpan(morado, 0, currentButton + 1, 0);   //Cambio de todas las letras de la palabra que coincidan con los botones presionados (en el TextView "textViewWord") a MORADO.
                                                textViewWord.setText(coloredText);                      //
                                            } else{ //Si no se da ninguno de los casos anteriores, quiere decir que la interacción con los botones sucede en cualquier parte de la matriz (que no sea en los lados derecho y/o inferior) y la palabra se está constuyendo de manera correcta.
                                                ejercicio.setButtonState(ejercicio.matriz[a][b], true, 1); //El botón se habilita y se cambia de color a MORADO.
                                                coloredText.clearSpans();                               //
                                                coloredText.setSpan(morado, 0, currentButton + 1, 0);   //Cambio de todas las letras de la palabra que coincidan con los botones presionados (en el TextView "textViewWord") a MORADO.
                                                textViewWord.setText(coloredText);                      //

                                                //----------Hablilitar y cambiar color de los botones alrededor del presionado a AMARILLO.
                                                if (direccion == 0) { //Si el sentido con el que es usuario está "encontrando" la palabra es vertical...
                                                    if (a + 1 < matrixSize) { //Si el botón presionado se encuentra en cualquier lugar que no sea sobre el lado inferior de la matriz de botones...
                                                        ejercicio.setButtonState(ejercicio.matriz[a + 1][b], true, 2); //...se habilita y cambia el color (a AMARILLO) del botón de abajo del presionado.
                                                    }
                                                } else { //Si el sentido con el que es usuario está "encontrando" la palabra es horizontal...
                                                    if (b + 1 < matrixSize) { //Si el botón presionado se encuentra en cualquier lugar que no sea sobre el lado derecho de la matriz de botones...
                                                        ejercicio.setButtonState(ejercicio.matriz[a][b + 1], true, 2); //...se habilita y cambia el color (a AMARILLO) del botón de la derecha del presionado.
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else{

//----------Caso 4: Se presiona el mismo botón presionado con anterioridad (diferente del primero)----------

                                    if ((currentButton >= 1) && (v.getId() == idClick[currentButton])) { //Si se presiona el mismo botón presionado con anterioridad y este no es el primero que se presionó (Caso 2).
                                        ejercicio.setButtonColor(ejercicio.matriz[a][b], 2); //El botón presionado cambia de color a amarillo (y se encuentra habilitado).
                                        if(currentButton == 1){ //Si en la matriz de  botones hay exactamente 2 presionados...
                                            if(direccion == 0){ //Si la direccion en la que se encontraba "buscando" la palabra es vertical...
                                                ejercicio.matriz[a - 1][b].setEnabled(true); //Se habilita el botón presionado anteriormente (que es color MORADO).
                                                if(b < matrixSize - 1){ //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde derecho de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a - 1][b + 1], true, 2); //Se habilita y se cambia de color a amarillo el botón un espacio hacia arriba y uno a la derecha del presionado.
                                                }
                                                if(a < matrixSize - 1){ //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde inferior de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a + 1][b], false, 0); //Se deshabilita y cambia de color (a naranja) el botón que sigue en horizontal del preionado.
                                                }
                                            } else{ //Si la direccion en la que se encontraba "buscando" la palabra es horizontal...
                                                ejercicio.matriz[a][b - 1].setEnabled(true); //Se habilita el botón presionado anteriormente (color morado).
                                                if(a < matrixSize - 1){ //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde inferior de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a + 1][b - 1], true, 2); //Se habilita y se cambia de color a amarillo el botón un espacio hacia abajo y uno a la izquierda del presionado.
                                                }
                                                if(b < matrixSize - 1) { //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde derecho de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a][b + 1], false, 0); //Se deshabilita y cambia de color (a naranja) el botón que sigue en vertical del preionado.
                                                }
                                            }
                                        } else{ //Si en la matriz de botones hay más de 2 presionados...
                                            if(direccion == 0){ //Si la direccion en la que se encontraba "buscando" la palabra es vertical...
                                                ejercicio.matriz[a - 1][b].setEnabled(true); //Se habilita el botón presionado anteriormente (color morado).
                                                if(a < matrixSize - 1){ //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde inferior de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a + 1][b], false, 0); //Se deshabilita y cambia de color (a naranja) el botón que sigue en vertical del preionado.
                                                }
                                            } else{ //Si la direccion en la que se encontraba "buscando" la palabra es horizontal...
                                                ejercicio.matriz[a][b - 1].setEnabled(true); //Se habilita el botón presionado anteriormente (color morado).
                                                if(b < matrixSize - 1){ //Si el botón presionado se en encuentra en cualquier lugar que no sea el borde derecho de la matriz de botones...
                                                    ejercicio.setButtonState(ejercicio.matriz[a][b + 1], false, 0); //Se deshabilita y cambia de color (a naranja) el botón que sigue en horizontal del preionado.
                                                }
                                            }
                                        }
                                        coloredText.clearSpans();                           //
                                        coloredText.setSpan(morado, 0, currentButton, 0);   //Cambio de todas las letras de la palabra que coincidan con los botones (a excepción del más reciente oprimido) presionados con anterioridad (en el TextView "textViewWord") a MORADO.
                                        textViewWord.setText(coloredText);                  //
                                        idClick[currentButton] = 0; //Se borra el ID del del botón recientemente presionado del arreglo "IdClick".
                                        wordInput = wordInput.substring(0, currentButton); //Se cambia el valor de wordInput asignandonle una subcadena (menos un caracter) de su propio valor.
                                        currentButton--; //Se le resta 1 al contador (regreso al estado anterior).
                                    } //Fin de if del Caso 4
                                } //Fin del else correspondiente al if del Caso 3
                            } //Fin del else correspondiente al if del Caso 2
                        } //Fin del else correspondiente al if del Caso 1

                        //----------Actualización del estado del RatingBar (puntaje) llamado "estrellas"----------

                        puntosAcum = 0f;
                        for(int i = 0; i < wordInput.length(); i++){
                            if(wordInput.charAt(i) == palabras[currentExercise].charAt(i)){
                                puntosAcum = puntosAcum + 3f/palabras[currentExercise].length();
                            }
                        }
                        estrellas.setStepSize(3f/palabras[currentExercise].length());
                        estrellas.setRating(puntosAcum);
                    } //Fin del onClick
                }); //Fin del .setonClickListener
            } //Fin del segundo for (principal) en el que se comienza asignando los botones de la matriz en el activity_main.xml a la matriz de botones "matriz" del objeto "ejercicio" (de la clase "Ejercicio") en el MainActivity.java.
        } //Fin del primer for (principal) en el que se comienza asignando los botones de la matriz en el activity_main.xml a la matriz de botones "matriz" del objeto "ejercicio" (de la clase "Ejercicio") en el MainActivity.java.

        //----------Primer ejercicio creado aleatoriamente (con el contador del ejercicio "currentExcercise" = 0)----------//

        ejercicio.setMatrix(palabras[currentExercise]);
        audio = MediaPlayer.create(MainActivity.this, audios[currentExercise]);
        audio.start();

        //---------- Botones NEXT, PREV, RETRY y AUDIO ----------//
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final Button btnPrev= (Button) findViewById(R.id.btnPrev);
        Button btnRetry = (Button) findViewById(R.id.btnRetry);
        Button btnAudio = (Button) findViewById(R.id.btnAudio);

        //----------Caso en el que se llega al primer o último ejercicio----------//
                  //-----("Desaparecen" los botones de NEXT o PREV)-----//
        if(currentExercise == 0){
            btnPrev.setEnabled(false);
            btnPrev.setVisibility(View.GONE);
        } else{
            if(currentExercise == numEjercicios){
                btnNext.setEnabled(false);
                btnNext.setVisibility(View.GONE);
            }
        }

        //----------NEXT----------(Interacción)//
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puntaje[currentExercise] < puntosAcum){
                    puntaje[currentExercise] = puntosAcum;
                } else {
                    puntaje[currentExercise] = puntaje[currentExercise];
                }
                puntosAcum = 0f;
                currentExercise++;
                if(currentExercise == palabras.length - 1){
                    btnNext.setEnabled(false);
                    btnNext.setVisibility(View.GONE);
                }
                wordInput = "";
                for(int i = 0; i <= currentButton; i++){
                    idClick[i] = 0;
                }
                currentButton = -1;
                coloredText.clear();
                coloredText.append(palabras[currentExercise]);
                textViewWord.setText(coloredText);
                audio.release();
                audio = MediaPlayer.create(MainActivity.this, audios[currentExercise]);
                audio.start();
                ejercicio.setMatrix(palabras[currentExercise]);
                btnPrev.setEnabled(true);
                btnPrev.setVisibility(View.VISIBLE);
                textViewWord.setText(palabras[currentExercise]);
                estrellas.setRating(0f);
            }
        });

        //----------PREV----------(Interacción)//
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(puntaje[currentExercise] < puntosAcum){
                    puntaje[currentExercise] = puntosAcum;
                } else {
                    puntaje[currentExercise] = puntaje[currentExercise];
                }
                puntosAcum = 0f;
                currentExercise--;
                if(currentExercise == 0){
                    btnPrev.setEnabled(false);
                    btnPrev.setVisibility(View.GONE);
                }
                wordInput = "";
                for(int i = 0; i <= currentButton; i++){
                    idClick[i] = 0;
                }
                currentButton = -1;
                coloredText.clear();
                coloredText.append(palabras[currentExercise]);
                textViewWord.setText(coloredText);
                audio.release();
                audio = MediaPlayer.create(MainActivity.this, audios[currentExercise]);
                audio.start();
                ejercicio.setMatrix(palabras[currentExercise]);
                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                textViewWord.setText(palabras[currentExercise]);
                estrellas.setRating(0f);
            }
        });

        //----------RETRY----------(Interacción)//
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntosAcum = 0f;
                wordInput = "";
                for(int i = 0; i <= currentButton; i++){
                    idClick[i] = 0;
                }
                currentButton = -1;
                coloredText.clearSpans();
                textViewWord.setText(coloredText);
                ejercicio.setMatrix(palabras[currentExercise]);
                estrellas.setRating(0f);
            }
        });

        //----------Audio----------(Interacción)//

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio.release();
                audio = MediaPlayer.create(MainActivity.this, audios[currentExercise]);
                audio.start();
            }
        });

        //--------------------------

        //----------Prueba del guardado de puntos por ejercicio (BORRAR)----------//

        /*pointProof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointProof.setText(Float.toString(puntaje[currentExercise]));
            }
        });*/

    } //Fin del onCreate
} //Fin del MainActivity
