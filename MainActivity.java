package com.example.musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {
    private TextView song, status;
    private SeekBar progress;
    private Button playPause;
    private int index = 0;
    private boolean playing = false;
    private final String[] songs = {"Brano 1 (test)", "Brano 2 (test)", "Brano 3 (test)"};

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        song=findViewById(R.id.song); status=findViewById(R.id.status);
        progress=findViewById(R.id.progress); playPause=findViewById(R.id.playPause);

        playPause.setOnClickListener(v -> {
            playing = !playing;
            playPause.setText(playing ? "❚❚ Pausa" : "▶ Play");
            status.setText(playing ? "PLAY premuto ✓" : "PAUSA premuto ✓");
            if (playing && progress.getProgress() == 0) progress.setProgress(10);
        });

        findViewById(R.id.next).setOnClickListener(v -> {
            index=(index+1)%songs.length;
            song.setText(songs[index]);
            progress.setProgress(0);
            status.setText("AVANTI premuto ✓");
        });

        findViewById(R.id.replay).setOnClickListener(v -> {
            progress.setProgress(0);
            status.setText("REPLAY premuto ✓");
        });

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar s,int p,boolean fromUser) {
                if(fromUser) status.setText("Posizione: " + p + "% ✓");
            }
            public void onStartTrackingTouch(SeekBar s) {}
            public void onStopTrackingTouch(SeekBar s) {}
        });
    }
}
