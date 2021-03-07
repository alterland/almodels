package ru.alterland.almodels.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.data.EmptyModelData;

public class ObjRenderer extends TileEntityRenderer<TileEntity> {

    public ObjRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        BlockRendererDispatcher dispatcher = Minecraft.getInstance().getBlockRendererDispatcher();

        BlockState state = tileEntityIn.getBlockState();
        IBakedModel model = dispatcher.getModelForState(state);

        matrixStackIn.push();

        IVertexBuilder vertexBuffer = bufferIn.getBuffer(RenderType.getSolid());

        float brightness = 0.87F;

        dispatcher.getBlockModelRenderer().renderModel(
                matrixStackIn.getLast(), vertexBuffer, state, model, brightness, brightness, brightness, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE
        );

        matrixStackIn.pop();

    }
}
