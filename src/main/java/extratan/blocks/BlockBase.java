package extratan.blocks;

import extratan.core.ExtraTAN;
import extratan.creativetabs.CreativeTabHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	public BlockBase(Material material, SoundType footstepSound, String name, String registryName, float hardness) {
		super(material);
		setUnlocalizedName(ExtraTAN.modId + "." + name);
		setRegistryName(registryName);
		setCreativeTab(CreativeTabHandler.ExtraTANBlocks);
		setHardness(hardness);
		setResistance(hardness / 2);
		setSoundType(footstepSound);
	}

}
