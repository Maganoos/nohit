package eu.magkari.mc.nohit;

import eu.magkari.mc.nohit.config.NoHitConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.util.GlfwUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameMode;

public class NoHit implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        MidnightConfig.init("nohit", NoHitConfig.class);

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.getGameMode() != GameMode.SURVIVAL || (NoHitConfig.enabled == NoHitConfig.EnabledEnum.OFF)) return ActionResult.PASS;
            if (entity instanceof PlayerEntity) {
                sendMessageOrCrash(player);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });

        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (NoHitConfig.enabled != NoHitConfig.EnabledEnum.OFF && NoHitConfig.bows && (stack.isOf(Items.BOW) || stack.isOf(Items.CROSSBOW))) {
                sendMessageOrCrash(player);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });
	}

	public static void sendMessageOrCrash(PlayerEntity player) {
        if (NoHitConfig.crash == NoHitConfig.CrashEnum.ON) GlfwUtil.makeJvmCrash();
		if (NoHitConfig.message) player.sendMessage(NoHitConfig.getMessage(), true);
	}
}