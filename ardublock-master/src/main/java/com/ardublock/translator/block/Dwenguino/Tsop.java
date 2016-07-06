package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Tsop extends TranslatorBlock
{
	public static final String REMOTE = "int __remote(int pinNumber)\n{\nint value = 0;\nint time = pulseIn(pinNumber,LOW);\n if(time>2000)\n{\nfor(int counter1=0;counter1<12;counter1++)\n{\nif(pulseIn(pinNumber,LOW)>1000)\n{\nvalue = value + (1<< counter1);\n}\n}\n}\nreturn value;\n}\n\n";
	public Tsop(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret;
		TranslatorBlock translatorBlock;
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String pinNumber=translatorBlock.toCode();
		translator.addDefinitionCommand(REMOTE);
		translator.addSetupCommand("pinMode("+pinNumber+",INPUT);");
		ret = "__remote(";		
		ret = ret + pinNumber;
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	}
	
}
