package com.ikeharad.mymod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.nio.charset.MalformedInputException;
import java.util.Random;

public class ParticleEyeFlame extends Particle {
    TextureAtlasSprite tex;
    static private ResourceLocation TEXTURE=new ResourceLocation("mymod:textures/particles/eye_particle_atlas.png");
    protected ParticleEyeFlame(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
        setParticleTextureIndex(new Random().nextInt(2)+1);
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        //Minecraft.getMinecraft().getTextureManager().deleteTexture(TEXTURE);
        GlStateManager.popMatrix();
    }
}
