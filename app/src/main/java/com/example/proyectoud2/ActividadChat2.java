package com.example.proyectoud2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.SpannableStringBuilder;
import java.util.ArrayList;

public class ActividadChat2 extends AppCompatActivity {
    // Creo un ArrayList para guardar el historial que se va acumulando en el textView
    private ArrayList<String> historialConversacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_chat2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        // Indico cuál es el textView del que quiero obtener la información
        TextView textView2 = findViewById(R.id.tv_act2Grande);
        //Declaro el intento
        Intent intento= getIntent();
        // Guardo en el ArrayList creado el historial traido por el intento
        historialConversacion = intento.getStringArrayListExtra("historial");

        // Si el arrayList no esta inicializado (es null) se crea uno nuevo
        if (historialConversacion == null) {
            historialConversacion = new ArrayList<>();
        }

        //Muestro todo el historial en el textView llamando al metodo actualizarConversacion
        actualizarConversacion(textView2);
    }


    // Método para actualizar el TextView con la conversación actualizada


    private void actualizarConversacion(TextView textView) {
        // Crear un SpannableStringBuilder para manejar los textos con colores
        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();

        // Creo un bucle que recorre el arrayList donde se guarda la conversación
        for (String mensaje : historialConversacion) {

            // Crear SpannableString para aplicar colores
            SpannableString spannableString = new SpannableString(mensaje);
       if(mensaje.startsWith("User A:")){
           ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLUE);
           spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           // Aplicar estilo de negrita a "User A:"
           spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User A:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       }
       else if (mensaje.startsWith("User B:")) {
               ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GREEN);
               spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           // Aplicar estilo de negrita a "User B:"
           spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User A:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       }

            // Se añade mensaje al stringBuilder, uno al final del otro con salto de línea
            spannableBuilder.append(spannableString).append("\n");
        }
        // Se muestra el texto en el textView
        textView.setText(spannableBuilder);
    }

    // Creación del metodo onClick del botón
    public void enviar(View view) {

        EditText textoEdit = findViewById(R.id.idEditText2);
        // Obtener el texto
        String texto = textoEdit.getText().toString();  // Obtener el texto
        if (!texto.isEmpty()) {

            // Añadir el mensaje a la lista debajo de User B
            historialConversacion.add("User B:\n" + texto);

            // Creamos el intento que llevará la informacion a la MainActivity
            Intent intento = new Intent(this, MainActivity.class);

            // Pasamos historial
            intento.putStringArrayListExtra("historial", historialConversacion); // Pasar el historial

            startActivity(intento);
        }
        else{
            //No se realiza acción.
        }
    }
}