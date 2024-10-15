package com.example.proyectoud2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
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

    // ArrayList para guardar el historial que se va acumulando en el textView
    private ArrayList<String> historialConversacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate: Actividad iniciada");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_chat2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        TextView textView2 = findViewById(R.id.tv_act2Grande);
        textView2.setGravity(Gravity.START);
        Intent intento= getIntent();
        historialConversacion = intento.getStringArrayListExtra("historial");


        // Si el arrayList no esta inicializado (es null) se crea uno nuevo
        if (historialConversacion == null) {
            historialConversacion = new ArrayList<>();
            Log.i("MainActivity", "onCreate: No se recibió historial. Inicializando lista vacía.");
        }
        else {
            Log.i("MainActivity", "onCreate: Historial recibido con " + historialConversacion.size() + " mensajes.");
        }

        actualizarConversacion(textView2);
        Log.i("MainActivity", "onCreate: Configuración de la actividad completada.");
    }



    // Método que actualiza el TextView con el historial de conversación,
    // aplicando estilos y colores según el usuario que envió cada mensaje.

    private void actualizarConversacion(TextView textView) {

        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder();

        for (String mensaje : historialConversacion) {

            SpannableString spannableString = new SpannableString(mensaje);
            int backgroundColor;

       if(mensaje.startsWith("User A:")){

           backgroundColor = Color.parseColor("#ADD8E6");
           ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLACK);
           spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User A:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       }
       else if (mensaje.startsWith("User B:")) {

           backgroundColor = Color.parseColor("#90EE90");
           ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
           spannableString.setSpan(colorSpan, 0, mensaje.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
           spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, "User A:".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       }
       else{
           Log.w("MainActivity", "actualizarConversacion: Mensaje no reconocido - " + mensaje);
       }
           spannableBuilder.append(spannableString).append("\n");
        }

        textView.setText(spannableBuilder);
        Log.i("MainActivity", "actualizarConversacion: Finalizada la actualización del TextView.");
    }



    // Método que se llama al hacer clic en el botón "Enviar".
    // Este método recoge el texto ingresado en el EditText,
    // lo añade al historial de conversación y lo envía a la ActividadChat2.
    public void enviar(View view) {

        EditText textoEdit = findViewById(R.id.idEditText2);
        String texto = textoEdit.getText().toString();  // Obtener el texto

        if (!texto.isEmpty()) {

            historialConversacion.add("User B:\n" + texto);
            Log.i("MainActivity", "enviar: El mensaje fue añadido al historial.");
            Intent intento = new Intent(this, MainActivity.class);
            intento.putStringArrayListExtra("historial", historialConversacion); // Pasar el historial
            startActivity(intento);
        }
        else{
            Log.w("MainActivity", "enviar: Campo de texto vacío. No se envió ningún mensaje.");
        }
    }
}