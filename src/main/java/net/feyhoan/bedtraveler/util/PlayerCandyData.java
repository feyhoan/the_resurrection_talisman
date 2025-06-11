package net.feyhoan.bedtraveler.util;

import net.minecraft.nbt.NbtCompound;

public class PlayerCandyData {
    public boolean inCandyDimension = false;
    public String originalDimension = "";
    public double originalX = 0;
    public double originalY = 0;
    public double originalZ = 0;
    public int lollipopsEaten = 0;

    public static PlayerCandyData fromNbt(NbtCompound nbt) {
        PlayerCandyData data = new PlayerCandyData();
        data.inCandyDimension = nbt.getBoolean("InCandyDimension");
        data.originalDimension = nbt.getString("OriginalDimension");
        data.originalX = nbt.getDouble("OriginalX");
        data.originalY = nbt.getDouble("OriginalY");
        data.originalZ = nbt.getDouble("OriginalZ");
        data.lollipopsEaten = nbt.getInt("LollipopsEaten");
        return data;
    }

    public NbtCompound toNbt() {
        NbtCompound nbt = new NbtCompound();
        nbt.putBoolean("InCandyDimension", inCandyDimension);
        nbt.putString("OriginalDimension", originalDimension);
        nbt.putDouble("OriginalX", originalX);
        nbt.putDouble("OriginalY", originalY);
        nbt.putDouble("OriginalZ", originalZ);
        nbt.putInt("LollipopsEaten", lollipopsEaten);
        return nbt;
    }
}