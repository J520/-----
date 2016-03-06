package com.jf.lphone;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class Phonservice extends Service{
	private MediaRecorder recorder;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		manager.listen(new onCallListChang(), PhoneStateListener.LISTEN_CALL_STATE);
		
		System.out.println("开启服务");
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
private class onCallListChang extends PhoneStateListener{
		@Override
	public void onCallStateChanged(int state, String incomingNumber) {
			
		switch (state) {
		case TelephonyManager.CALL_STATE_IDLE:
			System.out.println("kongxian");
			if (recorder!=null) {			
				 recorder.stop();
				 recorder.reset();   // You can reuse the object by going back to setAudioSource() step
				 recorder.release(); // Now the object cannot be reused
					}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				 System.out.println("接听");
				 recorder = new MediaRecorder();
				 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				 recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				 recorder.setOutputFile("/mnt/sdcard/abcd.3gp");
				 try {
					recorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recorder.start();
			
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				
				break;
			default:
				break;
			}			
			super.onCallStateChanged(state, incomingNumber);
		}
		
	}

}
