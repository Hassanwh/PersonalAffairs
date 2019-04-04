package code.person.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
//DES加密
public class DESede
{
	byte[] _key24=new byte[24];
	private final String _alogorithm="DESede";
	
	public DESede(String key) throws Exception
	{
		try
		{
			byte[] transferkey=ASCII_To_BCD(key.getBytes("ASCII"), key.length());
			makeKey24(transferkey);
		}
		catch (Exception e)
		{
				throw new Exception(e.toString());
		}
	}
	
	public String encrypt(String src) throws Exception
	{
		try
		{
			byte[] data= encrypt(_key24, Hex.decodeHex(src.toCharArray()));
			System.out.println(new String(data));
			return Hex.encodeHexString(data).toUpperCase();
		}
		catch (Exception e)
		{
			throw new Exception(e.toString());
		}
	}
	
	public String decrypt(String src) throws Exception
	{
		try
		{
			byte[] data=decrypt(_key24,Hex.decodeHex(src.toCharArray()));
			return Hex.encodeHexString(data).toUpperCase();
		}
		catch(Exception e)
		{
			throw new Exception(e.toString());
		}
	}
	
	private byte[] decrypt(byte[] key, byte[] src)
	{
		try
		{
			SecretKey deskey = new SecretKeySpec(key, _alogorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/NoPadding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		}
		catch (java.security.NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch (javax.crypto.NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		}
		catch (java.lang.Exception e3)
		{
			e3.printStackTrace();
		}

		return null;
	}

	private byte[] encrypt(byte[] key, byte[] src)
	{
		try
		{
			SecretKey deskey = new SecretKeySpec(key, _alogorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/NoPadding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		}
		catch (java.security.NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch (javax.crypto.NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		}
		catch (java.lang.Exception e3)
		{
			e3.printStackTrace();
		}

		return null;
	}
	
	private void makeKey24(byte[] inputkey) throws Exception
	{
		if(inputkey!=null)
		{
			if(inputkey.length==16)
			{
				System.arraycopy(inputkey, 0, _key24, 0, 16);
				System.arraycopy(inputkey, 0, _key24, 16, 8);
			}
			else if(inputkey.length==24)
			{
				_key24=inputkey;
			}
			else
			{
				throw new Exception("key值不为可被处理的长度，长度既不是16也不是24");
			}
		}
		else
		{
			throw new Exception("key值为null");
		}
	}
	
	private byte[] ASCII_To_BCD(byte[] ascii, int asc_len)
	{
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++)
		{
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	private byte asc_to_bcd(byte asc)
	{
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}
}
