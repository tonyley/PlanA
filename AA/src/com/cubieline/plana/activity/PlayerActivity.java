/**
 * 
 */
package com.cubieline.plana.activity;

import java.io.IOException;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.TextView;

import com.cubieline.plana.R;

/**
 * @author LeiGuoting
 *
 */
public class PlayerActivity extends BaseActivity implements TextureView.SurfaceTextureListener, OnPreparedListener{
	private static final String TAG = "PlayerActivity";
	
	private String source;
	
	private TextureView textureView;
	
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		source = getIntent().getData().toString();
		setContentView(R.layout.activity_display_item);
		textureView = (TextureView)findViewById(R.id.display_textureView);
		textView = (TextView)findViewById(R.id.display_uri_txtv);
		textView.setText(source);
		textureView.setSurfaceTextureListener(this);
	}
	
	@Override
	public void onPrepared(MediaPlayer mediaPlayer) {
		mediaPlayer.start();
	}
	
	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		Log.d(TAG, "@onSurfaceTextureAvailable currentThread:" + Thread.currentThread().getName());
		Surface face = new Surface(surface);
		
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnPreparedListener(this);
		
		Log.d(TAG, "@onSurfaceTextureAvailable source[" + source + "]");
		try {
			mediaPlayer.setDataSource(source);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		int rotation = getWindowManager().getDefaultDisplay().getRotation();
		float degrees = 0f;
		 switch (rotation) {
         case Surface.ROTATION_0: degrees = 0f; break;
         case Surface.ROTATION_90: degrees = 90f; break;
         case Surface.ROTATION_180: degrees = 180f; break;
         case Surface.ROTATION_270: degrees = 270f; break;
		 }
		 */
		textureView.setRotation(90.0f);
		mediaPlayer.setSurface(face);
		mediaPlayer.prepareAsync();
	}
	
	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		//do nothing
		return false;
	}
	
	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
		
	}
	
	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		
	}
}
