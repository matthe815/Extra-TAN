package extratan.blocks;

import extratan.core.ExtraTAN;
import extratan.creativetabs.CreativeTabHandler;
import lieutenant.registry.RegisterHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * A base for more easily defining block types.
 * @author Matthew
 *
 */
public class BlockBase extends Block {

	public BlockBase(Material material, SoundType footstepSound, String name, String registryName, float hardness) {
		super(material);
		
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(CreativeTabHandler.ExtraTANBlocks)
			.setHardness(hardness)
			.setResistance(hardness / 2);
		
		setSoundType(footstepSound);
		
		RegisterHandler.AddBlock(this);
	}

}
