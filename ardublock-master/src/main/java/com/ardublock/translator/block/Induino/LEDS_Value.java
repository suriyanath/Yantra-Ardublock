package com.ardublock.translator.block.Induino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDS_Value extends TranslatorBlock {
	public LEDS_Value(Long blockId, Translator translator, String codePrefix,
			String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException,
			SubroutineNotDeclaredException, BlockException {
		TranslatorBlock dataBlock11 = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock dataBlock12 = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock dataBlock13 = this.getRequiredTranslatorBlockAtSocket(2);
		String data11 = dataBlock11.toCode();
		String data12 = dataBlock12.toCode();
		String data13 = dataBlock13.toCode();
		translator.addSetupCommand("pinMode(11,OUTPUT);\npinMode(12,OUTPUT);\npinMode(13,OUTPUT);\n");
		return "digitalWrite(11,"+data11+");\ndigitalWrite(12,"+data12+");\ndigitalWrite(13,"+data13+");";
	}
}
