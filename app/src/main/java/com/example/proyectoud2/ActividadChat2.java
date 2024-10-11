package com.example.proyectoud2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        // Creo un StringBuilder
        StringBuilder sb = new StringBuilder();

        // Creo un bucle que recorre el arrayList donde se guarda la conversación
        for (String mensaje : historialConversacion) {

            // Se añade mensaje al stringBuilder, uno al final del otro con salto de línea
            sb.append(mensaje).append("\n");
        }
        // Se muestra el texto en el textView
        textView.setText(sb.toString());
    }

    // Creación del metodo onClick del botón
    public void enviar(View view) {

        EditText textoEdit = findViewById(R.id.idEditText2);
        // Obtener el texto
        String texto = textoEdit.getText().toString();  // Obtener el texto

        // Añadir el mensaje a la lista debajo de User B
        historialConversacion.add("User B: \n" + texto);

        // Creamos el intento que llevará la informacion a la MainActivity
        Intent intento = new Intent(this,MainActivity.class);

        // Pasamos historial
        intento.putStringArrayListExtra("historial", historialConversacion); // Pasar el historial

        startActivity(intento);

    }
}