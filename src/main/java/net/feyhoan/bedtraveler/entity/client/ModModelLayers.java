package net.feyhoan.bedtraveler.entity.client;

import net.feyhoan.bedtraveler.BedTraveler;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer JELLY_BEAR =
            new EntityModelLayer(new Identifier(BedTraveler.MOD_ID, "jelly_bear"), "main");
}