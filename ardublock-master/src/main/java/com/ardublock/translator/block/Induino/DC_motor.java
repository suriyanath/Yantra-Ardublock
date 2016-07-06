package com.ardublock.translator.block.Induino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class DC_motor extends TranslatorBlock
{

	public DC_motor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{	
		TranslatorBlock channelBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock speedBlock = this.getRequiredTranslatorBlockAtSocket(1);
		int channelNumber = Integer.parseInt(channelBlock.toCode());
		int speed = Integer.parseInt(speedBlock.toCode());
		String ret=null;
		
		if (channelNumber > 2 || channelNumber < 1) 
		{
			throw new BlockException(this.blockId, "the Channel# of DC Motor must be 1 or 2");
			//channelNumber = "1 or 2";
		}
		if (speed > 255 || speed < -255) 
		{
			throw new BlockException(this.blockId, "the Speed of DC Motor must be -255(full speed backward) to 255(full speed forward)");
			//Speed = "-255 to 255";
		}
		
		
		
		
		if (channelNumber == 1)
		{
			if(speed>0) {			
				ret = "\tdigitalWrite(4,LOW);\n";
				ret = ret+"\tanalogWrite("+3+","+ speed+");\n";
			}
			else if(speed<0) {			
				ret = "\tdigitalWrite(4,HIGH);";
				ret = ret+"\tanalogWrite("+3+","+ (speed+255)+");\n";
			}
			else {			
				ret = "\tdigitalWrite(4,LOW);";
				ret = ret+"\tanalogWrite("+3+","+ speed +");\n";
			}
		}
		else if (channelNumber == 2)
		{
			if(speed>0) {			
				ret = "\tdigitalWrite(6,LOW);\n";
				ret = ret+"\tanalogWrite("+5+","+ speed+");\n";
			}
			else if(speed<0) {			
				ret = "\tdigitalWrite(6,HIGH);";
				ret = ret+"\tanalogWrite("+5+","+ (speed+255) +");\n";
			}
			else {			
				ret = "\tdigitalWrite(6,LOW);";
				ret = ret+"\tanalogWrite("+5+","+ speed +");\n";
			}
		}
		translator.addSetupCommand("pinMode(4, OUTPUT);\npinMode(2, OUTPUT);\npinMode(7, OUTPUT);\ndigitalWrite(7,LOW);\ndigitalWrite(2,HIGH);\npinMode(6, OUTPUT);");
		return ret;
		}

	}
