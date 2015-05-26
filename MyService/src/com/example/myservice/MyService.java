package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {


	Messenger aMessenger;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mMessenger.getBinder();

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}
	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "Hello from Service!", Toast.LENGTH_SHORT).show();
				Bundle b = msg.getData();
				aMessenger = new Messenger(b.getBinder("saurav"));
				Message msg1 = Message.obtain();
				msg1.what = 1;
				try {
					aMessenger.send(msg1);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			default:
				super.handleMessage(msg);
			}
		}
	}
	Messenger mMessenger = new Messenger(new IncomingHandler());
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("Saurav","Service OnCreate!");
	}



}
