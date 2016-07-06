package com.ardublock.translator.block.Induino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Ultrasonic extends TranslatorBlock
{
	public static final String Function = "int __readUltrasonic_cm(int trigPin, int echoPin)\n{\n pinMode(trigPin, OUTPUT);\ndigitalWrite(trigPin, LOW);\ndelayMicroseconds(2);\ndigitalWrite(trigPin, HIGH);\ndelayMicroseconds(10);\ndigitalWrite(trigPin, LOW);\npinMode(echoPin, INPUT);\nreturn pulseIn(echoPin, HIGH)/ 29 / 2;\n}";
	public Ultrasonic(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String trigPin;
		String echoPin;
		TranslatorBlock translatorBlock;
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		trigPin = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		echoPin = translatorBlock.toCode();
		translator.addDefinitionCommand(Function);
		String ret = "__readUltrasonic_cm("+trigPin+","+echoPin+");\n";
		return ret;
	}
	
}
