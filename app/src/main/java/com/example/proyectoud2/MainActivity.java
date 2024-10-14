package com.example.proyectoud2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.SpannableStringBuilder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

// Creo un ArrayList para guardar el historial que se va acumulando en el textView
    private ArrayList<String> historialConversacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Indico cuál es el textView del que quiero obtener la información
        TextView textView3 = findViewById(R.id.tv_act1Grande);
        // Alinea el texto a la izquierda
        textView3.setGravity(Gravity.START);
        //Declaro el intento
        Intent intento= getIntent();

        // Guardo en el ArrayList creado el historial traido por el intento
        historialConversacion = intento.getStringArrayListExtra("historial");

        // Si el arrayList no esta inicializado (es null) se crea uno nuevo
        if (historialConversacion == null) {
            historialConversacion = new ArrayList<>();
        }

        // Muestro todo el historial en el textView llamando al metodo actualizarConversacion
        actualizarConversacion(textView3);
    }

    // Método para actualizar el TextView con la conversación actualizada
    private void actualizarConversacion(TextView textView) {

        // Uso SpannableStringBuilder para manejar textos con estilos
        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();

        // Recorro el arrayList donde se guarda la conversación
        for (String mensaje : historialConversacion) {

            SpannableString spannableString = new SpannableString(mensaje);

            // Aplicar color azul para "User A"
            if (mensaje.startsWith("User A:")) {


                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLACK);
                spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // Aplicar estilo de negrita a "User A:"
                spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User A:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            // Aplicar color verde para "User B"
            else if (mensaje.startsWith("User B:")) {
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
                spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                // Aplicar estilo de negrita a "User A:"
                spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User B:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            // Añadir el mensaje con formato de color al SpannableStringBuilder
            spannableBuilder.append(spannableString).append("\n");

        }

        // Se muestra el texto en el textView
        textView.setText(spannableBuilder);
    }


// Creación del metodo onClick del botón

    public void enviar(View view) {
        EditText textoEdit = findViewById(R.id.idEditText1);
        // Obtener el texto
        String texto = textoEdit.getText().toString();
        if (!texto.isEmpty()) {

           // Añadir el mensaje a la lista debajo de User A
            historialConversacion.add("User A:\n" + texto);

            // Creamos el intento que llevará la informacion a la ActividadChat2
            Intent intento = new Intent(this, ActividadChat2.class);

            // Pasamos historial
            intento.putStringArrayListExtra("historial", historialConversacion);

            startActivity(intento);
        }
        else {
            // No se realiza acción
        }
    }

}