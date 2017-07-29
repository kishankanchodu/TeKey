package com.kishan.notetest;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MediaPlayer myPlayer;
	private String note,txtview;
	private int len,l,delaytime;
	private EditText et;
	private Button stopBtn;
	private Button playBtn;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		stopBtn = (Button)findViewById(R.id.StopBtn);
		playBtn = (Button)findViewById(R.id.PlayBtn);
		textView = (TextView)findViewById(R.id.textView1);
		et=(EditText)findViewById(R.id.editText1);
	}
	
	final Handler handler = new Handler();
	public void PlayNote(View v){
		textView.setText("");
		l=0;
		note=et.getText().toString();
		final String[] splitnote = note.split("\\s+");
		len =splitnote.length;
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				try{
					stopBtn.setEnabled(true);
					AssetFileDescriptor afd = getAssets().openFd(splitnote[l]+".wav");
					myPlayer = new MediaPlayer();
					myPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					myPlayer.prepare();
					myPlayer.start();
				} catch (Exception e) {
				e.printStackTrace();
				}
				l=l+1;
				txtview=textView.getText().toString();
				if((l<len) && txtview.equals("")){
					if(splitnote[l].contains(".")){
						delaytime=10;//10ms delay
						handler.postDelayed(this, delaytime);
					}
					else{
						delaytime=0;//0ms delay
						handler.postDelayed(this, delaytime);
						}
				}	
			}
		}, 0);
	}
	
	public int check(String[] splitnote){
		if(splitnote[l].contains(".")){
			delaytime=2000;
		}
		else
			delaytime=2;
		return delaytime;
	}
	
	public void StopPlay(View v) {
		textView.setText("Stoped");
	   }

}
