package com.jzfree.rotatevideo.sample;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.io.IOException;
import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final String TAG = "MainActivity";
    private static final String URL = "http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/08/17/63bf8bcc706a46b58ee5c821edaee661_h264818000nero_aac32-5.mp4";

    private ResizeTextureView textureView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureView = findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(this, Uri.parse(URL));
            mediaPlayer.setSurface(surface);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(MediaPlayer::start);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, e.getMessage());
        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        getVideoInfo();
    }

    private void getVideoInfo() {
        Bundle bundle = new Bundle();
        bundle.putString("uri", URL);
        MetadataLoader metadataLoader = new MetadataLoader(this, bundle);
        List<Metadata> metadata = metadataLoader.loadInBackground();
        int width = 0;
        int height = 0;
        for (Metadata metadatum : metadata) {
            Log.d(TAG, "metadata key:" + metadatum.getKey() + " value:" + metadatum.getValue());
            if (FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH.equals(metadatum.getKey())) {
                width = Integer.parseInt(metadatum.getValue().toString());
            }
            if (FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT.equals(metadatum.getKey())) {
                height = Integer.parseInt(metadatum.getValue().toString());
            }
        }
        textureView.resize(width, height);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
}
