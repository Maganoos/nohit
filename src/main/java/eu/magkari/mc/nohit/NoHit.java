package eu.magkari.mc.nohit;

import eu.magkari.mc.nohit.config.NoHitConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Unique;

public class NoHit implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AutoConfig.register(NoHitConfig.class, Toml4jConfigSerializer::new);
	}
	@Unique
	public static NoHitConfig getConfig() {
		return AutoConfig.getConfigHolder(NoHitConfig.class).getConfig();
	}
	@Unique
	public static void sendMessage(ClientPlayerEntity player) {
		if (getConfig().message) {
			player.sendMessage(Text.literal("tsk tsk tsk").styled(style -> style.withColor(5636095)), true);
		}
	}
}