package ru.alterland.almodels.block;

import net.minecraft.block.AbstractBlock;;
import ru.alterland.almodels.tileentity.DoubleBedTileEntity1;

public class DoubleBedBlock1 extends I322Block {

    public DoubleBedBlock1(final AbstractBlock.Properties properties) {
        super(properties, new DoubleBedTileEntity1());
    }
}
