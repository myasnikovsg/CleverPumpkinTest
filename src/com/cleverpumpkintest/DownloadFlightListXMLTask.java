package com.cleverpumpkintest;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;

public abstract class DownloadFlightListXMLTask extends AsyncTask<String, Void, Flight[]> {
	
	@Override
	protected Flight[] doInBackground(String... params) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		}
		FlightXMLParser parser = new FlightXMLParser();
		Flight result[] = null;
		ArrayList<Flight> parsedList;
		try {
			parsedList = parser.parse(params[0]);
			result = parsedList.toArray(new Flight[parsedList.size()]);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		return result;
	}
	
	@Override
	protected abstract void onPostExecute(Flight[] result);
}
