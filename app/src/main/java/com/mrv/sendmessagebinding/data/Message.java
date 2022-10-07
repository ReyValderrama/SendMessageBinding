package com.mrv.sendmessagebinding.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Clase modelo que contiene el dato del usuario y el mensaje que manda.
 *
 * @author MRey
 * @version 1.0
 */
public class Message implements Serializable, Parcelable {
    private User user;
    private String content;

    //Por defecto, si no se declara un constructor, Java implementa el constructor vacío

    /**
     * Construcor con parámetros
     *
     * @param user
     * @param content
     */
    public Message(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public Message(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * En este método se devuelve la representación de un objeto Message.
     * Al ser User un objeto, se debe implementar la interfaz Serializable y
     * Parcelable en el objeto User
     *
     * @return
     */
    @Override
    @NonNull
    public String toString() {
        return getUser() + "-> " + getContent();
    }


    //region MÉTODOS DE LA INTERFAZ PARCELABLE
    protected Message(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
    //endregion
}
