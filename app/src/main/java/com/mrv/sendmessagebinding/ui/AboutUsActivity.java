package com.mrv.sendmessagebinding.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrv.sendmessagebinding.R;
import com.mrv.sendmessagebinding.SendMessageApplication;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.mipmap.ic_launcher)
                .setCover(R.drawable.sendmessageimg)
                .setName(((SendMessageApplication)getApplication()).getUser().getName())
                .setSubTitle(getResources().getString(R.string.subTitle))
                .setBrief(getResources().getString(R.string.brief))
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(getResources().getString(R.string.gitHub))
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        setContentView(view);
    }
}