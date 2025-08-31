package eu.magkari.mc.nohit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "nohit")
public class NoHitConfig implements ConfigData {
    public boolean enabled = true;
    public boolean bows = true;
    public boolean message = true;
}
