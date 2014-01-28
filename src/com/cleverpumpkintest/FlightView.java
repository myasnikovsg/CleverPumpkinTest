package com.cleverpumpkintest;

import java.io.InputStream;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class FlightView extends RelativeLayout {

	public FlightView(Activity context, Flight flight, boolean isDetailed) {
		super(context);
		LayoutInflater inflater = context.getLayoutInflater();
		inflater.inflate(R.layout.flight_view, this);
		((TextView) findViewById(R.id.duration_text_view)).
			setText(flight.getDurationAsString());
		((TextView) findViewById(R.id.takeoff_date_text_view)).
			setText(flight.getTakeoffDate());
		((TextView) findViewById(R.id.takeoff_time_text_view)).
			setText(flight.getTakeoffTime());
		((TextView) findViewById(R.id.takeoff_town_text_view)).
			setText(flight.getTakeoffTown());
		((TextView) findViewById(R.id.landing_date_text_view)).
			setText(flight.getLandingDate());
		((TextView) findViewById(R.id.landing_time_text_view)).
			setText(flight.getLandingTime());
		((TextView) findViewById(R.id.landing_town_text_view)).
			setText(flight.getLandingTown());
		((TextView) findViewById(R.id.carrier_text_view)).
			setText(flight.getCarrier());
		((TextView) findViewById(R.id.number_text_view)).
			setText(flight.getNumber());
		((TextView) findViewById(R.id.eq_text_view)).
			setText(flight.getEq());
		((TextView) findViewById(R.id.price_text_view)).
			setText(flight.getPriceAsString());
		if (isDetailed) {
			((TextView) findViewById(R.id.description_text_view)).
				setText(flight.getDescription());
			new DownloadImageTask((ImageView) findViewById(R.id.flight_image_view),
					(ProgressBar) findViewById(R.id.image_loading_bar))
            	.execute(flight.getImageSourcePath());
		} else {
			((TextView) findViewById(R.id.description_text_view)).
				setVisibility(View.GONE);
			((ImageView) findViewById(R.id.flight_image_view)).
				setVisibility(View.GONE);
			((ProgressBar) findViewById(R.id.image_loading_bar)).
				setVisibility(View.GONE);
		}
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView image;
		ProgressBar bar;
		
		public DownloadImageTask(ImageView image, ProgressBar bar) {
			this.image = image;
			this.bar = bar;
		}
		
		@Override
		protected void onPreExecute() {
			image.setVisibility(View.GONE);
			bar.setIndeterminate(true);
		}
		
		protected Bitmap doInBackground(String... urls) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
			String urldisplay = urls[0];
			Bitmap icon = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				icon = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	        }
			return icon;
		}
		
		protected void onPostExecute(Bitmap result) {
			bar.setVisibility(View.GONE);
			image.setVisibility(View.VISIBLE);
			image.setImageBitmap(result);
		}
	}
}
