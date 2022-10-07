package com.mrv.sendmessagebinding.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mrv.sendmessagebinding.R;
import com.mrv.sendmessagebinding.SendMessageApplication;
import com.mrv.sendmessagebinding.data.Message;
import com.mrv.sendmessagebinding.data.User;
import com.mrv.sendmessagebinding.databinding.ActivitySendMessageBinding;

/**
 * <h1>Proyecto SendMessage</h1>
 * En este proyecto aprenderemos a realizar las siguientes operaciones:
 * <ol>
 *     <li>Crear un evento en un componente Button en código XML.</li>
 *     <li>Crear un escuchador/listener del evento <code>OnClick()</code>.</li>
 *     <li>Crear un Intent y un elemento Bundle para pasar información entre activities.</li>
 *     <li>El ciclo de vida de una Activity.</li>
 *     <li>Manejar la pila de actividades.</li>
 * </ol>
 *
 * @author MRey
 * @version 1.0
 * @see android.widget.Button
 * @see android.app.Activity
 * @see android.content.Intent
 * @see android.os.Bundle
 */
public class SendMessageActivity extends AppCompatActivity {
    private static final String TAG = "SendMessageActivity";
    private SendMessageOnClickListener delegate;
    private ActivitySendMessageBinding binding;

    //region CICLO DE VIDA DE LA ACTIVITY

    /**
     * Método que se ejecuta cuando se crea la Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Establecer el Listener OnClickListener al botón mediante una clase anónima
        //que implementa la interfaz View.OnClickListener

        //SendMessageApplication sendMessageApplication = new SendMessageApplication();
        binding.setMessage(new Message(((SendMessageApplication) getApplication()).getUser()));
        binding.btSend.setOnClickListener(view -> sendMessage());
        //view -> Toast.makeText(SendMessageActivity.this,"Esto es a través de una clase anónima.", Toast.LENGTH_SHORT).show());

        Log.d(TAG, "SendMessageActivity -> onCreate()");
    }

    /**
     * Este método callback crea el menú de opciones en la vista. Se devuelve true
     * para indicar al SO que se ha realizado la opción de modificar el menú.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Este método callback se llama cuando se pulsa sobre una de las opciones del menú
     * de la aplicación.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutus:
                //Toast.makeText(this, "Se ha pulsado sobre About Us", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void sendMessage() {
        //1. Crear el contenedor para añadir los datos.
        Bundle bundle = new Bundle();
        //1.1 Pasar dato a dato
        //bundle.putString("user", binding.getMessage().getUser().getName());
        //bundle.putString("message", binding.getMessage().getContent());

        //1.2 Crear un objeto Message

        bundle.putParcelable("message", binding.getMessage());

        //2. Crear el objeto Intent explícito porque se conoce la Actividad destino
        Intent intent = new Intent(this, ViewMessageActivity.class);
        intent.putExtras(bundle);

        //3. Inicia la transición entre una vista y otra mediante StartActivity
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessageActivity -> onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SendMessageActivity -> onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SendMessageActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SendMessageActivity -> onStop()");
    }

    /**
     * Este método se ejecuta cuando se destruye la activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Se pone a null su elemento root, no es necesario vista por vista.
        binding = null;
        Log.d(TAG, "SendMessageActivity -> onDestroy()");
    }


    //endregion

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btSend definido en el XML
     * <code>android:onclick="sendMessage"</code>
     *
     * @param view
     */
    public void sendMessage(View view) {
        sendMessage();
    }

    /**
     * Esta clase NO ANÓNIMA implementa el Listener que responde siempre al
     * evento OnClick
     */
    class SendMessageOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(SendMessageActivity.this, "Esto es a través de un delegado.", Toast.LENGTH_SHORT).show();
        }
    }
}