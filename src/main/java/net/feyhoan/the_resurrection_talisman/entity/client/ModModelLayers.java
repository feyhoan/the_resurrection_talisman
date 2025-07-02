package net.feyhoan.the_resurrection_talisman.entity.client;

import net.feyhoan.the_resurrection_talisman.TheResurrectionTalisman;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer LOST_SPIRIT =
            new EntityModelLayer(new Identifier(TheResurrectionTalisman.MOD_ID, "lost_spirit"), "main");
}