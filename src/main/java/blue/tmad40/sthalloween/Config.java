
package blue.tmad40.sthalloween;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class Config {

	// Provide Instances
	private static Config instance;

	public Config() {
		instance = this;
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public File messagesf, storagef;
	public FileConfiguration messages, storage;

	// Load/reload files
	public void loadFiles() {

		messagesf = new File(Main.getInstance().getDataFolder(), "messages.yml");
		storagef = new File(Main.getInstance().getDataFolder(), "storage.yml");

		if (!messagesf.exists()) {
			messagesf.getParentFile().mkdirs();
			copy(Main.getInstance().getResource("messages.yml"), messagesf);
		}
		if (!storagef.exists()) {
			storagef.getParentFile().mkdirs();
			copy(Main.getInstance().getResource("storage.yml"), storagef);
		}

		messages = new YamlConfiguration();
		storage = new YamlConfiguration();

		try {

			messages.load(messagesf);
			storage.load(storagef);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Save files
	public void saveFiles(String input) {
		try {

			if (input.equals("messages")) {
				messages.save(messagesf);
			} else if (input.equals("storage")) {
				storage.save(storagef);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Copy default files
	public void copy(InputStream in, File file) {

		try {

			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}
			out.close();
			in.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
