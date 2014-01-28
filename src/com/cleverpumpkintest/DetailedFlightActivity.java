package com.cleverpumpkintest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class DetailedFlightActivity extends Activity {
	
	protected ProgressDialog loadingDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String flightNumber = (String) getIntent().getExtras().get("flight_number");
		setTitle(getResources().getString(R.string.detailed_flight_text) + flightNumber);
		loadingDialog = new ProgressDialog(this);
		loadingDialog.setMessage(getResources().getString(R.string.loading_string));
		loadingDialog.show();
		new DownloadFlightListXMLTask() {
			
			@Override
			protected void onPostExecute(Flight[] result) {
				loadingDialog.dismiss();
				loadFromArray(result[0]);
			}
		}.execute(getResources().getString(R.string.flight_url_prefix) + flightNumber + ".xml");
	}
	
	protected void loadFromArray(Flight flight) {
		setContentView(new FlightView(this, flight, true));
	}
}
