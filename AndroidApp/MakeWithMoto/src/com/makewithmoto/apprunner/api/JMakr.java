package com.makewithmoto.apprunner.api;

import android.app.Activity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.makewithmoto.apidoc.APIAnnotation;
import com.makewithmoto.hardware.MAKRBoard;

public class JMakr extends JInterface {
	

	private ReadThread mReadThread;
	private String receivedData;
	private MAKRBoard makr;
	private String TAG = "JMakr";
	
	boolean isStarted = false;

	public JMakr(Activity a) {
		super(a);
		makr = new MAKRBoard();
	}
	
	@JavascriptInterface
	@APIAnnotation(description = "initializes makr board", example = "makr.start();")	
	public void start() {
		
		if(!isStarted){
	         /* Create a receiving thread */
		    mReadThread = new ReadThread();
		    mReadThread.start();
		
            makr.start();
            
            isStarted = true;
		}
	}
	
	@JavascriptInterface
	@APIAnnotation(description = "clean up and poweroff makr board", example = "makr.stop();")
	public void stop() {
		if(isStarted){
		    isStarted = false;
		    if (mReadThread != null)
			    mReadThread.interrupt();
		    makr.stop(); 
		}
	}

	
	private class ReadThread extends Thread {
		

		@Override
		public void run() {
			super.run();
			while (!isInterrupted()) {
				receivedData = "";
				
				if(isStarted){
				    receivedData = makr.readSerial();
				}
				
				if(receivedData != ""){
				    c.get().runOnUiThread(new Runnable() {
					    public void run() {

							Log.d(TAG,"Got data: "+receivedData);
						    callback("OnSerialRead("+receivedData+");");   
					    }
				    });	
				}		
			}
		}
	}
	
	
	@JavascriptInterface
	@APIAnnotation(description = "sends commands to makr board", example = "makr.writeSerial(\"LEDON\");")
	public void writeSerial(String cmd) {
		if(isStarted){
		    makr.writeSerial(cmd);
		}
	}

	
	@JavascriptInterface
	@APIAnnotation(description = "resumes makr activity", example = "makr.resume();")
	public void resume() {
		makr.resume();
	}
	
	@JavascriptInterface
	@APIAnnotation(description = "pause makr activity", example = "makr.pause();")
	public void pause() {
		makr.pause();
	}

}