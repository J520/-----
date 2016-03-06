package com.jf.lphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Phonereceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
	Intent intent2=new Intent(context,Phonservice.class);
	context.startService(intent2);
		
	}

}
