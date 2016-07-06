package com.ardublock.translator.block.Induino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RGB extends TranslatorBlock
{

	public RGB(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{	
		TranslatorBlock redBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock greenBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock blueBlock = this.getRequiredTranslatorBlockAtSocket(2);
		int red = Integer.parseInt(redBlock.toCode());
		int green = Integer.parseInt(greenBlock.toCode());
		int blue = Integer.parseInt(blueBlock.toCode());
		String ret=null;
		
		if (red < 0 || red > 255||green < 0 || green > 255||blue < 0 || blue > 255) 
		{
			throw new BlockException(this.blockId, "RGB values must be between 0 to 255");
			//pwm = "0 to 255";
		}
		

		ret = "analogWrite(6,"+red+");\nanalogWrite(3,"+green+");\nanalogWrite(5,"+blue+");\n";
		return ret;
		}

	}
