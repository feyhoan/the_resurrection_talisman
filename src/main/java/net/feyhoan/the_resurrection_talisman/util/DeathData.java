package net.feyhoan.the_resurrection_talisman.util;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.feyhoan.the_resurrection_talisman.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

public class DeathData {
    private static final String SAVE_FILE_NAME = "the_resurrection_talisman_deaths.dat";
    public static final Map<UUID, DeathInfo> DEATHS = new HashMap<>();

    public record DeathInfo(
            BlockPos deathPos,
            ItemStack talisman,
            ItemStack[] mainInventory,
            ItemStack[] armor,
            ItemStack[] offhand
    ) {
        public NbtCompound toNbt() {
            NbtCompound nbt = new NbtCompound();
            nbt.putLong("deathPos", deathPos.asLong());

            NbtCompound talismanNbt = new NbtCompound();
            talisman.writeNbt(talismanNbt);
            nbt.put("talisman", talismanNbt);

            nbt.put("mainInventory", writeItemStackArray(mainInventory));
            nbt.put("armor", writeItemStackArray(armor));
            nbt.put("offhand", writeItemStackArray(offhand));

            return nbt;
        }

        public static DeathInfo fromNbt(NbtCompound nbt) {
            BlockPos pos = BlockPos.fromLong(nbt.getLong("deathPos"));

            ItemStack talisman = ItemStack.fromNbt(nbt.getCompound("talisman"));

            ItemStack[] mainInventory = readItemStackArray(nbt.getList("mainInventory", NbtCompound.COMPOUND_TYPE));
            ItemStack[] armor = readItemStackArray(nbt.getList("armor", NbtCompound.COMPOUND_TYPE));
            ItemStack[] offhand = readItemStackArray(nbt.getList("offhand", NbtCompound.COMPOUND_TYPE));

            return new DeathInfo(pos, talisman, mainInventory, armor, offhand);
        }

        private static NbtList writeItemStackArray(ItemStack[] stacks) {
            NbtList list = new NbtList();
            for (ItemStack stack : stacks) {
                NbtCompound stackNbt = new NbtCompound();
                if (stack != null && !stack.isEmpty()) {
                    stack.writeNbt(stackNbt);
                }
                list.add(stackNbt);
            }
            return list;
        }

        private static ItemStack[] readItemStackArray(NbtList list) {
            ItemStack[] stacks = new ItemStack[list.size()];
            for (int i = 0; i < list.size(); i++) {
                NbtCompound stackNbt = list.getCompound(i);
                stacks[i] = stackNbt.isEmpty() ? ItemStack.EMPTY : ItemStack.fromNbt(stackNbt);
            }
            return stacks;
        }
    }

    // Методы для сохранения/загрузки данных
    public static void load(MinecraftServer server) {
        File saveFile = new File(server.getSavePath(WorldSavePath.ROOT).toFile(), SAVE_FILE_NAME);
        if (saveFile.exists()) {
            try {
                NbtCompound nbt = NbtIo.readCompressed(saveFile);
                NbtCompound deathsNbt = nbt.getCompound("deaths");

                DEATHS.clear();
                for (String key : deathsNbt.getKeys()) {
                    UUID uuid = UUID.fromString(key);
                    DEATHS.put(uuid, DeathInfo.fromNbt(deathsNbt.getCompound(key)));
                }
            } catch (IOException e) {
                TheResurrectionTalisman.LOGGER.error("Failed to load death data", e);
            }
        }
    }

    public static void save(MinecraftServer server) {
        try {
            NbtCompound root = new NbtCompound();
            NbtCompound deathsNbt = new NbtCompound();

            DEATHS.forEach((uuid, info) ->
                    deathsNbt.put(uuid.toString(), info.toNbt()));

            root.put("deaths", deathsNbt);

            File saveFile = new File(server.getSavePath(WorldSavePath.ROOT).toFile(), SAVE_FILE_NAME);
            NbtIo.writeCompressed(root, saveFile);
        } catch (IOException e) {
            TheResurrectionTalisman.LOGGER.error("Failed to save death data", e);
        }
    }

    public static void storeInventoryAndPosition(ServerPlayerEntity player) {
        ItemStack talisman = findTalisman(player);
        ItemStack[] mainInv = copyInventory(player.getInventory().main);
        ItemStack[] armor = copyArmor(player.getInventory().armor);
        ItemStack[] offhand = copyOffhand(player.getInventory().offHand);

        DEATHS.put(player.getUuid(), new DeathInfo(
                player.getBlockPos(),
                talisman,
                mainInv,
                armor,
                offhand
        ));
        save(player.server); // Автосохранение
    }

    private static ItemStack[] copyInventory(List<ItemStack> inventory) {
        return inventory.stream()
                .map(stack -> stack.isEmpty() ? ItemStack.EMPTY : stack.copy())
                .toArray(ItemStack[]::new);
    }

    private static ItemStack[] copyArmor(List<ItemStack> inventory) {
        return inventory.stream()
                .map(stack -> stack.isEmpty() ? ItemStack.EMPTY : stack.copy())
                .toArray(ItemStack[]::new);
    }

    private static ItemStack[] copyOffhand(List<ItemStack> inventory) {
        return inventory.stream()
                .map(stack -> stack.isEmpty() ? ItemStack.EMPTY : stack.copy())
                .toArray(ItemStack[]::new);
    }

    public static void restoreInventory(ServerPlayerEntity player) {
        DeathInfo info = DEATHS.get(player.getUuid());
        if (info != null) {
            player.getInventory().clear();

            // Восстанавливаем основной инвентарь (без талисмана)
            for (int i = 0; i < info.mainInventory().length; i++) {
                ItemStack stack = info.mainInventory()[i];
                if (!stack.isEmpty()) {
                    player.getInventory().setStack(i, stack.copy());
                }
            }

            // Восстанавливаем броню и оффхенд
            for (int i = 0; i < info.armor().length; i++) {
                ItemStack stack = info.armor()[i];
                if (!stack.isEmpty()) {
                    player.getInventory().armor.set(i, stack.copy());
                }
            }

            if (!info.offhand()[0].isEmpty()) {
                player.getInventory().offHand.set(0, info.offhand()[0].copy());
            }

            // Удаляем запись (без повторного добавления талисмана)
            DEATHS.remove(player.getUuid());
        }
    }

    private static ItemStack findTalisman(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(ModItems.RESURRECTION_TALISMAN)) {
                return stack.copy();
            }
        }
        return ItemStack.EMPTY;
    }


    public static Optional<DeathInfo> getDeathInfo(UUID playerId) {
        return Optional.ofNullable(DEATHS.get(playerId));
    }

    // Удалить данные игрока
    public static void removeDeathInfo(UUID playerId, MinecraftServer server) {
        DEATHS.remove(playerId);
        save(server); // Автосохранение
    }

    // Проверить, есть ли данные об игроке
    public static boolean hasDeathInfo(UUID playerId) {
        return DEATHS.containsKey(playerId);
    }

    // Перебор всех записей (для частиц в MixinServerWorld)
    public static void forEachDeathInfo(BiConsumer<UUID, DeathInfo> action) {
        DEATHS.forEach(action);
    }
}