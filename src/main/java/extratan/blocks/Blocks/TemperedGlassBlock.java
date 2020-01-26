package extratan.blocks.Blocks;

import extratan.blocks.TransparentBlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TemperedGlassBlock extends TransparentBlockBase {

	public TemperedGlassBlock() {
		super(Material.GLASS, SoundType.GLASS, "temperedGlassBlock", "tempered_glass_block", 0.8f); // Import the block.
	}

}
