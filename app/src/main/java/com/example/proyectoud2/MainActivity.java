package com.example.proyectoud2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private ArrayList<String> historialConversacion; // Lista para el historial



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

        TextView textView3 = findViewById(R.id.tv_act1Grande);
        Intent intento= getIntent();


        historialConversacion = intento.getStringArrayListExtra("historial");

        if (historialConversacion == null) {
            historialConversacion = new ArrayList<>();
        }

        // Mostrar todo el historial en el TextView
        actualizarConversacion(textView3);
    }

    // Método para actualizar el TextView con la conversación actualizada
    private void actualizarConversacion(TextView textView) {
        StringBuilder sb = new StringBuilder();
        for (String mensaje : historialConversacion) {
            sb.append(mensaje).append("\n"); // Añadir cada mensaje
        }
        textView.setText(sb.toString()); // Mostrar en el TextView
    }




    /*textView3.setText(intento.getStringExtra("claveTexto")); // Mostrar el valor en un TextView*/





    public void enviar(View view) {
        EditText textoEdit = findViewById(R.id.idEditText1);
        String texto = textoEdit.getText().toString();  // Obtener el texto


        // Añadir el mensaje a la lista
        historialConversacion.add("User A: " + texto);


        Intent intento = new Intent(this,ActividadChat2.class);

        intento.putStringArrayListExtra("historial", historialConversacion); // Pasar el historial

       /* intento.putExtra("claveTexto",texto);*/

        startActivity(intento);




    }



}