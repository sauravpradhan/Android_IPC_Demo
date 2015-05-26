package com.example.myipcactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	Messenger mService = null; 

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), "Hello from Activity!", Toast.LENGTH_SHORT).show();
				break;
			default:
				super.handleMessage(msg);
			}
		}
	}
	Messenger mMessenger = new Messenger(new IncomingHandler());
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("Saurav","Inside onStart()!");
		
	}
	@Override
	protected void onResume() {
		Intent servInt = new Intent();
		//servInt.setPackage("com.example.myservice");
		//servInt.setClassName("com.example.myservice","MyService.java" );
		ComponentName cn = new ComponentName("com.example.myservice", "com.example.myservice.MyService");
		Intent intent = new Intent();
		intent.setComponent(cn);
		
	
		//servInt.setClassName("com.example.myservice", "MyService.class");
		
		Log.d("Saurav","Binding the service!");
		try
		{
			bindService(intent, mConec,Context.BIND_AUTO_CREATE);
		}
		catch (Exception e)
		{
			Log.d("Saurav","Could not Bind the service! Exception is:"+e);
		}		super.onResume();
	}
	private ServiceConnection mConec = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d("Saurav","Inside onServiceConnected()!");
			// TODO Auto-generated method stub
			mService = new Messenger(service);
			sayHello();

		}
	};
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unbindService(mConec);
	}
	public void sayHello()
	{
		Log.d("Saurav","Sending message to the Service!");
		Message msg = Message.obtain();
		msg.what = 1;
		Bundle b = new Bundle();
		b.putBinder("saurav", mMessenger.getBinder());
		msg.setData(b);
		try {
			mService.send(msg);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
