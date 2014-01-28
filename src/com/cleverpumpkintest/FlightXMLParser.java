package com.cleverpumpkintest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class FlightXMLParser {
	
    public ArrayList<Flight> parse(String urlString) throws XmlPullParserException, IOException {
    	InputStream in = downloadUrl(urlString);
    	
    	ArrayList<Flight> result = new ArrayList<Flight>();
    	Flight temp = null;
    	boolean inPriceTag = false;
    	boolean inDescriptionTag = false;
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new InputStreamReader(in));
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            	switch (parser.getEventType()) {
            	case XmlPullParser.START_TAG:
            		if (parser.getName().compareTo("trip") == 0) {
                		temp = new Flight();
                		temp.setDurationFromString(parser.getAttributeValue(0));
                	} else
            		if (parser.getName().compareTo("takeoff") == 0) {
            			temp.setTakeoffDate(parser.getAttributeValue(0));
            			temp.setTakeoffTime(parser.getAttributeValue(1));
            			temp.setTakeoffTown(parser.getAttributeValue(2));
            		} else
            		if (parser.getName().compareTo("landing") == 0) {
            			temp.setLandingDate(parser.getAttributeValue(0));
            			temp.setLandingTime(parser.getAttributeValue(1));
            			temp.setLandingTown(parser.getAttributeValue(2));
            		} else
            		if (parser.getName().compareTo("flight") == 0) {
                		temp.setCarrier(parser.getAttributeValue(0));
                		temp.setNumber(parser.getAttributeValue(1));
                		temp.setEq(parser.getAttributeValue(2));
                	} else
                	if (parser.getName().compareTo("price") == 0) {
                		inPriceTag = true;
                	} else
                	if (parser.getName().compareTo("description") == 0) {
                		inDescriptionTag = true;
                	} else
                	if (parser.getName().compareTo("photo") == 0) {
                		temp.setImageSourcePath(parser.getAttributeValue(0));
                	}
                	break;
            	case XmlPullParser.END_TAG:
            		if (parser.getName().compareTo("trip") == 0)
            			result.add(temp);
            		break;
            	case XmlPullParser.TEXT:
            		if (inPriceTag) {
            			temp.setPrice(Double.parseDouble(parser.getText()));
            			inPriceTag = false;
            		} else 
            		if (inDescriptionTag) {
            			temp.setDescription(parser.getText());
            			inDescriptionTag = false;
            		}
            		break;
            	default:
                	break;
            	}
            	parser.next();
            }
            return result;
        } finally {
            in.close();
        }
    }

    private InputStream downloadUrl(String urlString) throws IOException {
    	URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
    
}
