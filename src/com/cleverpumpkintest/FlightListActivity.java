package com.cleverpumpkintest;

import java.util.Arrays;
import java.util.Comparator;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class FlightListActivity extends ListActivity {
	
	protected ProgressDialog loadingDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadingDialog = new ProgressDialog(this);
		loadingDialog.setMessage(getResources().getString(R.string.loading_string));
		loadingDialog.show();
		new DownloadFlightListXMLTask() {
			
			@Override
			protected void onPostExecute(Flight[] result) {
				loadingDialog.dismiss();
				loadFromArray(result);
			}
		}.execute(getResources().getString(R.string.flight_list_url));
	}
	
	protected void loadFromArray(Flight flights[]) {
		FlightListAdapter adapter = new FlightListAdapter(this, flights);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(FlightListActivity.this, DetailedFlightActivity.class);
		i.putExtra("flight_number", ((FlightListAdapter) getListAdapter()).
				getFlights()[position].getNumber());
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.flight_list_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// spoiled indentation, sorry
		Arrays.sort( ((FlightListAdapter) getListAdapter()).getFlights(), 
				(item.getItemId() == R.id.duration_sort) ?
						new Comparator<Flight>() {
							@Override
							public int compare(Flight lhs, Flight rhs) {
								return lhs.getDuration() - rhs.getDuration();
							}
						} :
						new Comparator<Flight>() {
							@Override
							public int compare(Flight lhs, Flight rhs) {
								return Double.compare(lhs.getPrice(), rhs.getPrice());
							}
						});
		((BaseAdapter) getListAdapter()).notifyDataSetChanged();
		return super.onMenuItemSelected(featureId, item);
	}
	
	private class FlightListAdapter extends BaseAdapter {
		private Flight[] flights;
		private final Activity context;
		
		public FlightListAdapter(Activity context, Flight[] flights) {
			this.context = context;
			this.flights = flights;
		}
		
		public Flight[] getFlights() {
			return flights;
		}
		@Override
		public int getCount() {
			return flights.length;
		}
		
		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return new FlightView(context, flights[position], false);
		}
		
	}
}
