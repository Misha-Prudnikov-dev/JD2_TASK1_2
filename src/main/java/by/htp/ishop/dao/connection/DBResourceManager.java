package by.htp.ishop.dao.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBResourceManager {

	private static final String DB_PROPERTIES = "db.properties";

	private static final DBResourceManager instance = new DBResourceManager();

	FileInputStream fileInputStream;
	InputStream inStream;

	Properties p = new Properties();

	public void getPropValue() throws IOException {

		inStream = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES);

		p.load(inStream);

	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return p.getProperty(key);
	}
}
