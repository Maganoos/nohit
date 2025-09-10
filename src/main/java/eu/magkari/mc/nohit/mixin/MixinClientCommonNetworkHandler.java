package eu.magkari.mc.nohit.mixin;

import eu.magkari.mc.nohit.NoHit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public class MixinClientCommonNetworkHandler {
	@Inject(at = @At("HEAD"), method = "sendPacket", cancellable = true)
	private void onSendPacket(Packet<?> packet, CallbackInfo ci) {
		if (packet instanceof PlayerInteractEntityC2SPacket interactPacket && NoHit.getConfig().enabled) {
			MinecraftClient mc = MinecraftClient.getInstance();
			if (mc.world == null || mc.player == null) return;

			Entity target = mc.world.getEntityById(((PlayerInteractEntityC2SPacketAccessor) interactPacket).getEntityId());
			if (target instanceof PlayerEntity) {
				NoHit.sendMessage(mc.player);
				ci.cancel();
			}
		}
	}
}