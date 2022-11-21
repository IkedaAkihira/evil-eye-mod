package com.ikeharad.mymod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleBlockDust;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleEye extends Particle {
    static public ResourceLocation TEXTURE=new ResourceLocation("mymod:textures/particles/eye_particle_atlas.png");
    protected ParticleEye(World worldIn, double posXIn, double posYIn, double posZIn) {
        super(worldIn, posXIn, posYIn, posZIn);
        setParticleTextureIndex(0);

    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        //https://github.com/jabelar/ExampleMod-1.12/blob/master/src/main/java/com/blogspot/jabelarminecraft/examplemod/client/particles/ParticleCustom.java
        GlStateManager.pushMatrix();
        //Set Texture
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);

        /*
        //glow effect
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 0.0F, 0.0F);
        GlStateManager.enableLighting();
        */

        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

        //reset Gl Setting ref EnderManEyeLayer
        //Minecraft.getMinecraft().getTextureManager().deleteTexture(TEXTURE);
        GlStateManager.popMatrix();

    }
}
