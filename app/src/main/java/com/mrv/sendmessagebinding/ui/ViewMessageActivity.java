package com.mrv.sendmessagebinding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.mrv.sendmessagebinding.R;
import com.mrv.sendmessagebinding.data.Message;
import com.mrv.sendmessagebinding.databinding.ActivitySendMessageBinding;
import com.mrv.sendmessagebinding.databinding.ActivityViewMessageBinding;

/**
 * Clase que muestra un mensaje de un usuario. Recoge el mensaje que implementa
 * la interfaz Parcelable
 */
public class ViewMessageActivity extends AppCompatActivity {
    private static final String TAG = "ViewMessageActivity";
    private ActivityViewMessageBinding binding;

    //region CICLO DE VIDA DE LA ACTIVITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //1. Recoger el Bundle que me han enviado en el Intent que ha iniciado esta Actividad
        //Esta actividad
        Bundle bundle = getIntent().getExtras();
        Message message = bundle.getParcelable("message");
        binding.setMessage(message);

        //2.1 se recoge valor a valor
        //binding.tvUser.setText(message.getUser().getName());
        //binding.tvMessageMsg.setText(message.getContent());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ViewMessageActivity -> onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ViewMessageActivity -> onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ViewMessageActivity -> onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ViewMessageActivity -> onStop()");
    }

    /**
     * Este método se ejecuta cuando se destruye la activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        Log.d(TAG, "ViewMessageActivity -> onDestroy()");
    }

    //endregion
}