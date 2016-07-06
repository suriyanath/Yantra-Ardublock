
package com.ardublock.translator.block.Induino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Servo extends TranslatorBlock {

	public Servo(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ret = "";

		int channelNumber = Integer.parseInt(tb.toCode());
		
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String angle = tb.toCode();

		if ( channelNumber < 1 || channelNumber > 2)
		{
			throw new BlockException(this.blockId, "Servo Channel should be A4 or A5");
		}
		
		if(Integer.parseInt(angle) > 180 || Integer.parseInt(angle) < 0) {
			throw new BlockException(this.blockId, "the angle of Servo must be from 0 to 180");
		}
		
		if(channelNumber==1) {
			translator.addDefinitionCommand("Servo servo1;");
			translator.addSetupCommand("servoA4.attach(18);");
			ret = "servoA4.write(" + angle + ");\n";
		}
		else {
			translator.addDefinitionCommand("Servo servoA5;");
			translator.addSetupCommand("servoA5.attach(19);");
			ret = "servoA5.write(" + angle + ");\n";
		}
		translator.addHeaderFile("Servo.h"); // the motor library
		return ret;
	}

}
