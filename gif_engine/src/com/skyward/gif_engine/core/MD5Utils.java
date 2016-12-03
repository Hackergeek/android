package com.skyward.gif_engine.core;

import java.security.MessageDigest;

/**
 * һ�����ַ�������MD5���ܵĹ�����
 * @author skyward
 *
 */
public class MD5Utils {
	public static String decode(String str) {
		try {
			// ָ�������㷨
			MessageDigest digest = MessageDigest.getInstance("md5");
			// ���ַ���ת��Ϊ�ֽڣ���������������ϣ����
			byte[] result = digest.digest(str.getBytes());
			StringBuffer buffer = new StringBuffer();
			for (byte b : result) {
				// ��ÿһ���ֽ���0xff��&���� 0xff(11111111)
				int number = b & 0xff;
				// ���õ���������ת��Ϊʮ��������
				String string = Integer.toHexString(number);
				// ����õ���ʮ���������ĳ��ȵ���1������ʮ������
				// �������λ��Ӧ����0����8λ�����ڸ�λ���һ����0��
				if (string.length() == 1) {
					buffer.append("0");
				}
				buffer.append(string);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
