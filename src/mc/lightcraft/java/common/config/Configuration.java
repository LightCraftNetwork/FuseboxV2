package mc.lightcraft.java.common.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Configuration {

    private JavaPlugin plugin;
    private FileConfiguration conf = null;
    private File file = null;
    private String fname = null;
    private boolean changed = false;

    /**
     * Creates a new Configuration instance based around provided information
     * @param plugin The plugin that this Configuration applies to
     * @param filename the path to the file you want the configuration of (origin folder is equal to plugin.getDataFolder())
     */
    public Configuration(JavaPlugin plugin, String filename) {
        this.plugin = plugin;
        this.fname = filename;
    }

    /**
     * Re-loads the current configuration file with the defaults for the file. Defaults are loaded via a data stream that is created by pathing to the file within the jar. The file jar path should be equivalent to the files configuration path. If the jar path is null, the no defaults are applied.
     */
    public void ReloadConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), this.fname);
        }
        this.conf = YamlConfiguration.loadConfiguration(this.file);

        InputStream isDefaults = this.plugin.getResource(this.fname);
        if (isDefaults != null) {
            @SuppressWarnings("deprecation") // This is only here because these is no workaround
			YamlConfiguration confDefault = YamlConfiguration.loadConfiguration(isDefaults);
            this.conf.setDefaults(confDefault);
        }
    }

    /**
     * Returns the fileConfiguration of the file that this Configuration class was instantiated around
     * @return the FileConfiguration of the file you instantiated this class around
     */
    public FileConfiguration GetConfig() {
        if (this.conf == null) {
            ReloadConfig();
        }
        return this.conf;
    }

    /**
     * Saves all changes to the Configuration file if possible
     * @return if the save was successful
     */
    public boolean SaveConfig() {
        if ((this.conf == null) || (this.file == null)) {
            return false;
        }
        try {
            this.conf.save(this.file);
            return true;
        }
        catch (IOException ex) {
            this.plugin.getLogger().log(Level.SEVERE, "[" + this.plugin.getName() + "] Error saving configuration file: '" + this.fname + "'!");
        }
        return false;
    }

    /**
     * Marks the config file as changed
     */
    public void setChanged() {
        if(!changed)changed=true;
    }

    /**
     * Checks if the file is changed
     * @return true if the file is changed
     */
    public boolean getChanged() {
        return changed;
    }

}
