package com.qq.weixin.mp.aes;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.qq.weixin.mp.aes.AesException;

public class WXBizMsgCrypt {
	String CHARSET = "utf-8";
	Base64 base64 = new Base64();
	byte[] aesKey;
	String token;
	String appId;

	/**
	 * ���캯��
	 * @param token ����ƽ̨�ϣ����������õ�token
	 * @param encodingAesKey ����ƽ̨�ϣ����������õ�EncodingAESKey
	 * @param appId ����ƽ̨appid
	 * 
	 * @throws AesException ִ��ʧ�ܣ���鿴���쳣�Ĵ�����;���Ĵ�����Ϣ
	 */
	public WXBizMsgCrypt(String token, String encodingAesKey, String appId) throws AesException {
		if (encodingAesKey.length() != 43) {
			throw new AesException(AesException.IllegalAesKey);
		}

		this.token = token;
		this.appId = appId;
		aesKey = Base64.decodeBase64(encodingAesKey + "=");
	}
	
	/**
	 * ������Ϣ����ʵ�ԣ����һ�ȡ���ܺ������.
	 * <ol>
	 * 	<li>�����յ����������ɰ�ȫǩ��������ǩ����֤</li>
	 * 	<li>����֤ͨ��������ȡxml�еļ�����Ϣ</li>
	 * 	<li>����Ϣ���н���</li>
	 * </ol>
	 * 
	 * @param msgSignature ǩ��������ӦURL������msg_signature
	 * @param timeStamp ʱ�������ӦURL������timestamp
	 * @param nonce ���������ӦURL������nonce
	 * @param postData ���ģ���ӦPOST���������
	 * 
	 * @return ���ܺ��ԭ��
	 * @throws AesException ִ��ʧ�ܣ���鿴���쳣�Ĵ�����;���Ĵ�����Ϣ
	 */
	public String decryptMsg(String msgSignature, String timeStamp, String nonce, String postData)
			throws AesException {

		// ��Կ�������˺ŵ�app secret
		// ��ȡ����
		Object[] encrypt = XMLParse.extract(postData);
		// ��֤��ȫǩ��
		String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt[1].toString());

		// ��URL�е�ǩ���Ƚ��Ƿ����
		// System.out.println("�������յ�URL�е�ǩ����" + msg_sign);
		// System.out.println("������У��ǩ����" + signature);
		if (!signature.equals(msgSignature)) {
			throw new AesException(AesException.ValidateSignatureError);
		}

		// ����
		String result = decrypt(encrypt[1].toString());
		return result;
	}
	
	/**
	 * ������ƽ̨�ظ��û�����Ϣ���ܴ��.
	 * <ol>
	 * 	<li>��Ҫ���͵���Ϣ����AES-CBC����</li>
	 * 	<li>���ɰ�ȫǩ��</li>
	 * 	<li>����Ϣ���ĺͰ�ȫǩ�������xml��ʽ</li>
	 * </ol>
	 * 
	 * @param replyMsg ����ƽ̨���ظ��û�����Ϣ��xml��ʽ���ַ���
	 * @param timeStamp ʱ����������Լ����ɣ�Ҳ������URL������timestamp
	 * @param nonce ������������Լ����ɣ�Ҳ������URL������nonce
	 * 
	 * @return ���ܺ�Ŀ���ֱ�ӻظ��û������ģ�����msg_signature, timestamp, nonce, encrypt��xml��ʽ���ַ���
	 * @throws AesException ִ��ʧ�ܣ���鿴���쳣�Ĵ�����;���Ĵ�����Ϣ
	 */
	public String encryptMsg(String replyMsg, String timeStamp, String nonce) throws AesException {
		// ����
		String encrypt = encrypt(getRandomStr(), replyMsg);
		// ���ɰ�ȫǩ��
		if (timeStamp == "") {
			timeStamp = Long.toString(System.currentTimeMillis());
		}

		String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt);

		// System.out.println("���͸�ƽ̨��ǩ����: " + signature[1].toString());
		// ���ɷ��͵�xml
		String result = XMLParse.generate(encrypt, signature, timeStamp, nonce);
		System.out.println(result);
		return result;
	}
	
	// �������16λ�ַ���
	String getRandomStr() {
		String base = "hCVmcH18wmP23A8LHfeZdMDzIX86U85Oah4SlBXsd3b";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * �����Ľ��м���.
	 * 
	 * @param text ��Ҫ���ܵ�����
	 * @return ���ܺ�base64������ַ���
	 * @throws AesException aes����ʧ��
	 */
	String encrypt(String randomStr, String text) throws AesException {
		ByteGroup byteCollector = new ByteGroup();
		byte[] randomStrBytes = null;
		byte[] textBytes =null;
		
		byte[] appidBytes = null;
		try {
			randomStrBytes = randomStr.getBytes(CHARSET);
			textBytes = text.getBytes(CHARSET);
			appidBytes = appId.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);

		// randomStr + networkBytesOrder + text + appid
		byteCollector.addBytes(randomStrBytes);
		byteCollector.addBytes(networkBytesOrder);
		byteCollector.addBytes(textBytes);
		byteCollector.addBytes(appidBytes);

		// ... + pad: ʹ���Զ������䷽ʽ�����Ľ��в�λ���
		byte[] padBytes = PKCS7Encoder.encode(byteCollector.size());
		byteCollector.addBytes(padBytes);

		// ������յ��ֽ���, δ����
		byte[] unencrypted = byteCollector.toBytes();

		try {
			// ���ü���ģʽΪAES��CBCģʽ
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			// ����
			byte[] encrypted = cipher.doFinal(unencrypted);

			// ʹ��BASE64�Լ��ܺ���ַ������б���
			String base64Encrypted = base64.encodeToString(encrypted);

			return base64Encrypted;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.EncryptAESError);
		}
	}
	
	// ����4���ֽڵ������ֽ���
	byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}
	
	/**
	 * �����Ľ��н���.
	 * 
	 * @param text ��Ҫ���ܵ�����
	 * @return ���ܵõ�������
	 * @throws AesException aes����ʧ��
	 */
	String decrypt(String text) throws AesException {
		byte[] original;
		try {
			// ���ý���ģʽΪAES��CBCģʽ
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

			// ʹ��BASE64�����Ľ��н���
			byte[] encrypted = Base64.decodeBase64(text);

			// ����
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.DecryptAESError);
		}

		String xmlContent, from_appid;
		try {
			// ȥ����λ�ַ�
			byte[] bytes = PKCS7Encoder.decode(original);

			// ����16λ����ַ���,�����ֽ����AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

			int xmlLength = recoverNetworkBytesOrder(networkOrder);

			xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), CHARSET);
			from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
					CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.IllegalBuffer);
		}

		// appid����ͬ�����
		if (!from_appid.equals(appId)) {
			throw new AesException(AesException.ValidateAppidError);
		}
		return xmlContent;

	}
	
	// ��ԭ4���ֽڵ������ֽ���
	int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}
}
