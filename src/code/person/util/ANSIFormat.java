package code.person.util;

import java.io.ObjectInputStream.GetField;

//ANSI编码
public class ANSIFormat
{
	private String pin;
	private String accno;

	public ANSIFormat(String pin, String accno)
	{
		this.pin = pin;
		this.accno = accno;
	}

	public byte[] process(String pin, String accno)
	{
		byte arrPin[] = getHPin(pin);
		byte arrAccno[] = getHAccno(accno);
		byte arrRet[] = new byte[8];
		// PIN BLOCK 格式等于 PIN 按位异或 主帐号;
		for (int i = 0; i < 8; i++)
		{
			arrRet[i] = (byte) (arrPin[i] ^ arrAccno[i]);
		}

		Util.printHexString("PinBlock：", arrRet);
		return arrRet;
	}

	public String process2()
	{
		byte arrPin[] = getHPin(this.pin);
		byte arrAccno[] = getHAccno(this.accno);
		byte arrRet[] = new byte[8];
		// PIN BLOCK 格式等于 PIN 按位异或 主帐号;
		for (int i = 0; i < 8; i++)
		{
			arrRet[i] = (byte) (arrPin[i] ^ arrAccno[i]);
		}

		return Util.transferHexString(arrRet);
	}

	private byte[] getHPin(String pin)
	{
		byte arrPin[] = pin.getBytes();
		byte encode[] = new byte[8];
		encode[0] = (byte) 0x06;
		encode[1] = (byte) Util.uniteBytes(arrPin[0], arrPin[1]);
		encode[2] = (byte) Util.uniteBytes(arrPin[2], arrPin[3]);
		encode[3] = (byte) Util.uniteBytes(arrPin[4], arrPin[5]);
		encode[4] = (byte) 0xFF;
		encode[5] = (byte) 0xFF;
		encode[6] = (byte) 0xFF;
		encode[7] = (byte) 0xFF;
		Util.printHexString("encoded pin：", encode);
		return encode;
	}

	private byte[] getHAccno(String accno)
	{
		// 取出主帐号；
		int len = accno.length();
		byte arrTemp[] = accno.substring(len < 13 ? 0 : len - 13, len - 1)
				.getBytes();
		byte arrAccno[] = new byte[12];
		for (int i = 0; i < 12; i++)
		{
			arrAccno[i] = (i <= arrTemp.length ? arrTemp[i] : (byte) 0x00);
		}
		byte encode[] = new byte[8];
		encode[0] = (byte) 0x00;
		encode[1] = (byte) 0x00;
		encode[2] = (byte) Util.uniteBytes(arrAccno[0], arrAccno[1]);
		encode[3] = (byte) Util.uniteBytes(arrAccno[2], arrAccno[3]);
		encode[4] = (byte) Util.uniteBytes(arrAccno[4], arrAccno[5]);
		encode[5] = (byte) Util.uniteBytes(arrAccno[6], arrAccno[7]);
		encode[6] = (byte) Util.uniteBytes(arrAccno[8], arrAccno[9]);
		encode[7] = (byte) Util.uniteBytes(arrAccno[10], arrAccno[11]);
		Util.printHexString("encoded accno：", encode);
		return encode;
	}

}
