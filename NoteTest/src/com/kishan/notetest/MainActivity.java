package com.kishan.notetest;


import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaPlayer myPlayer;
	private String note;
	private EditText et;
	private int len,l;
	private Button stopPlayBtn,playBtn;
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	      et=(EditText)findViewById(R.id.editText1);
	      //et2=(EditText)findViewById(R.id.editText2);
	      note="test.wav";
	}

	public void Play(View view) {
		try{
			stopPlayBtn = (Button)findViewById(R.id.stopPlay);
			stopPlayBtn.setEnabled(true);
			note=et.getText().toString();
			String[] splitnote = note.split("\\s+");
			len =splitnote.length;
			len=len/2;
			for(l=0; l<=len+1; l++){
				if(splitnote[l].equals(".")){
					Thread.sleep(50);
					}
				else{
			    	AssetFileDescriptor afd = getAssets().openFd(splitnote[l]+".wav");
					myPlayer = new MediaPlayer();
					myPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					myPlayer.prepare();
					myPlayer.start();
					}
			}
			stopPlayBtn.setEnabled(false);
			
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		
	}
	public void StopPlay(View view) {
		try {
			if (myPlayer != null) {
				myPlayer.stop();
				myPlayer.release();
				myPlayer = null;
				stopPlayBtn = (Button)findViewById(R.id.stopPlay);
				playBtn = (Button)findViewById(R.id.playBtn);
				playBtn.setEnabled(true);
				stopPlayBtn.setEnabled(false);
		           
		           Toast.makeText(getApplicationContext(), "Stop playing the recording...", 
						   Toast.LENGTH_SHORT).show();
		       }
		   } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
