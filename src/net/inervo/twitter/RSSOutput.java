package net.inervo.twitter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.Item;

public class RSSOutput {
	Channel newChannel;

	public RSSOutput( String title, String description, String url ) {
		prepareFeed( title, description, url );
	}

	private void prepareFeed( String title, String description, String url ) {
		newChannel = new Channel();
		newChannel.setTitle( title );
		newChannel.setDescription( description );
		newChannel.setLink( url );
		newChannel.setLanguage( "en" );

		SimpleDateFormat formatter = new SimpleDateFormat( "dd MMM yyyy HH:mm:ss Z" );
		String today = formatter.format( new Date() );
		newChannel.setPubDate( today );
	}

	public void addItem( String url, String title, String description ) {
		Item item = new Item();
		item.setTitle( title );
		item.setLink( url );
		item.setDescription( description );
		newChannel.addItem( item );
	}

	public ByteArrayOutputStream getRSSasOutputStream() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		newChannel.render( baos );
		return baos;
	}

	public void write( String filename ) {
		try {
			FileOutputStream fout = new FileOutputStream( filename );
			newChannel.render( fout );
			fout.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
