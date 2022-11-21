package com.ikeharad.mymod;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderEyeApostle extends RenderBiped<EntityEyeApostle> {
    private static final ResourceLocation TEXTURE=new ResourceLocation(MyMod.ID_MY_MOD,MyMod.NAME_EYE_APOSTLE);
    public RenderEyeApostle(RenderManager renderManagerIn, ModelBiped modelBipedIn, float shadowSize) {
        super(renderManagerIn, modelBipedIn, shadowSize);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityEyeApostle entity) {
        return TEXTURE;
    }
}
