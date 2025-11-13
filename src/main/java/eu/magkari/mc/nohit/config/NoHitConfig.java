package eu.magkari.mc.nohit.config;

import eu.midnightdust.lib.config.MidnightConfig;
import eu.midnightdust.lib.util.MidnightColorUtil;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class NoHitConfig extends MidnightConfig {
    @Entry public static EnabledEnum enabled = EnabledEnum.ON;
    public enum EnabledEnum {
        ON, ON_AGAIN, ON_AGAIN_AGAIN, ON_AGAIN_AGAIN_AGAIN, OFF
    }

    @Entry public static boolean bows = true;
    @Entry public static boolean message = true;
    @Entry @Condition(requiredOption = "message") public static String messageString = "tsk tsk tsk";
    @Entry(isColor = true) @Condition(requiredOption = "message") public static String messageColour = "#ffafaf";

    @Entry(width = 800) public static CrashEnum crash = CrashEnum.OFF;
    public enum CrashEnum {
        OFF, OFF_AGAIN, OFF_AGAIN_AGAIN, ON
    }

    public static Text getMessage() {
        int colour = MidnightColorUtil.hex2Rgb(messageColour).getRGB() & 0xFFFFFF;
        return Text.literal(messageString).setStyle(Style.EMPTY.withColor(colour));
    }
}
