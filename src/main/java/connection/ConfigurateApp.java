package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Aleks
 */
public class ConfigurateApp {

	private String path;         // путь для сохранения на сервере
	private String databaseDriver;
	private String databaseHost;
	private String databasePort;
	private String databaseName;
	private String databaseUser;
	private String databasePassword;
	private String databaseUrl;

	private void init() throws IOException {

		Properties ps = new Properties();

		try (FileInputStream fs = new FileInputStream("config.properties")) {
			ps.load(fs);

			if (ps.getProperty("database.host").length() > 0) {
				databaseHost = ps.getProperty("database.host");
			}
			if (ps.getProperty("database.driver").length() > 0) {
				databaseDriver = ps.getProperty("database.driver");
			}
			if (ps.getProperty("database.port").length() > 0) {
				databasePort = ps.getProperty("database.port");
			}
			if (ps.getProperty("database.name").length() > 0) {
				databaseName = ps.getProperty("database.name");
			}
			if (ps.getProperty("database.user").length() > 0) {
				databaseUser = ps.getProperty("database.user");
			}
			if (ps.getProperty("database.password").length() > 0) {
				databasePassword = ps.getProperty("database.password");
			}
			if (ps.getProperty("pathStorage").length() > 0) {
				path = ps.getProperty("pathStorage");
			}
		}
	}

	public String getPath() {
		return path;
	}

	public String getDatabaseUrl() {
		return databaseUrl =
			databaseDriver + "://" + databaseHost + ":" + databasePort + "/" + databaseName;
	}
//
//    public String getDatabaseDriver() {
//        return databaseDriver;
//    }
//
//    public String getDatabaseHost() {
//        return databaseHost;
//    }
//
//    public String getDatabasePort() {
//        return databasePort;
//    }
//
//    public String getDatabaseName() {
//        return databaseName;
//    }

	public String getDatabaseUser() {
		return databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	@Override
	public String toString() {
		return getDatabaseUrl();
	}
}
