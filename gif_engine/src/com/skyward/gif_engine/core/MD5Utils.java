package com.skyward.gif_engine.core;

import java.security.MessageDigest;

/**
 * 一个对字符串进行MD5加密的工具类
 * @author skyward
 *
 */
public class MD5Utils {
	public static String decode(String str) {
		try {
			// 指定加密算法
			MessageDigest digest = MessageDigest.getInstance("md5");
			// 将字符串转换为字节，并对其进行随机哈希运算
			byte[] result = digest.digest(str.getBytes());
			StringBuffer buffer = new StringBuffer();
			for (byte b : result) {
				// 把每一个字节与0xff做&运算 0xff(11111111)
				int number = b & 0xff;
				// 将得到的运算结果转换为十六进制数
				String string = Integer.toHexString(number);
				// 如果得到的十六进制数的长度等于1，即该十六进制
				// 数不足八位，应该用0补足8位，即在高位添加一个“0”
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
