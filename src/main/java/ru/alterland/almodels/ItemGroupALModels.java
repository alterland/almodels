package ru.alterland.almodels;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemGroupALModels extends ItemGroup {

    public ItemGroupALModels() {
        super(ALModels.MOD_ID);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.RED_BED);
    }
}
