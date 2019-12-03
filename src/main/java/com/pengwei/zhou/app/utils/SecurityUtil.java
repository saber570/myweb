package com.pengwei.zhou.app.utils;

import jodd.crypt.BCrypt;

public class SecurityUtil {

  /**
   * 将密码加盐hash
   * @param
   * @return
   */
  public static String hashPassword(String plaintext) {
    return BCrypt.hashpw(plaintext, BCrypt.gensalt());
  }

  /**
   * 验证密码
   * @param plaintext
   * @param hashed
   * @return
   */
  public static boolean checkpw(String plaintext, String hashed) {
    return BCrypt.checkpw(plaintext, hashed);
  }
}

