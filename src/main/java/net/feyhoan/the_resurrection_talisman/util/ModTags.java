package net.feyhoan.the_resurrection_talisman.util;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.block.Block;
import net.minecraft.client.gl.EffectShaderStage;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.command.EffectCommand;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(TheResurrectionTalisman.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(TheResurrectionTalisman.MOD_ID, name));
        }
    }


}