package com.mrv.sendmessagebinding;

import android.app.Application;

import com.mrv.sendmessagebinding.data.User;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class SendMessageApplication extends Application {

    //Usuario que ha iniciado sesión. DATO GLOBAL porque siempre
    //se va a acceder a él mediante el método getApplication().getUser()
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("Se ha inicializado el objeto Application");
        user = new User("MRey", "mrv@gmail.com");
    }

    public User getUser() {
        return user;
    }
}
