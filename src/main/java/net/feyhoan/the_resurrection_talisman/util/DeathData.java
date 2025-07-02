package net.feyhoan.the_resurrection_talisman.util;

import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.*;

public class DeathData {
    public static final Map<UUID, DeathInfo> DEATHS = new HashMap<>();

    public static record DeathInfo(
            BlockPos deathPos,
            ItemStack talisman,
            List<ItemStack> inventory,
            List<ItemStack> armor,
            List<ItemStack> offhand
    ) {}

    public static void storeInventoryAndPosition(ServerPlayerEntity player) {
        // Сохраняем только талисман
        ItemStack talisman = findTalisman(player);

        // Сохраняем весь инвентарь
        List<ItemStack> inventory = new ArrayList<>();
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if(!stack.isEmpty() && !stack.isOf(ModItems.RESURRECTION_TALISMAN)) {
                inventory.add(stack.copy());
            }
        }

        // Сохраняем броню
        List<ItemStack> armor = new ArrayList<>();
        for(ItemStack stack : player.getInventory().armor) {
            if(!stack.isEmpty()) armor.add(stack.copy());
        }

        // Сохраняем оффхенд
        List<ItemStack> offhand = new ArrayList<>();
        if(!player.getInventory().offHand.get(0).isEmpty()) {
            offhand.add(player.getInventory().offHand.get(0).copy());
        }

        DEATHS.put(player.getUuid(), new DeathInfo(
                BlockPos.ofFloored(player.getPos()),
                talisman,
                inventory,
                armor,
                offhand
        ));
    }

    public static void restoreInventory(ServerPlayerEntity player) {
        DeathInfo info = DEATHS.get(player.getUuid());
        if(info != null) {
            // Очищаем инвентарь
            player.getInventory().clear();

            // Возвращаем талисман
            player.getInventory().offerOrDrop(info.talisman());

            // Восстанавливаем инвентарь
            for(ItemStack stack : info.inventory()) {
                player.getInventory().offerOrDrop(stack);
            }

            // Восстанавливаем броню
            for(int i = 0; i < info.armor().size(); i++) {
                player.getInventory().armor.set(i, info.armor().get(i));
            }

            // Восстанавливаем оффхенд
            if(!info.offhand().isEmpty()) {
                player.getInventory().offHand.set(0, info.offhand().get(0));
            }

            DEATHS.remove(player.getUuid());
        }
    }

    private static ItemStack findTalisman(PlayerEntity player) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if(stack.isOf(ModItems.RESURRECTION_TALISMAN)) {
                return stack.copy();
            }
        }
        return ItemStack.EMPTY;
    }
}