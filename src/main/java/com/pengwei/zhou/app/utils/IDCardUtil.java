package com.pengwei.zhou.app.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class IDCardUtil {

  private static final int CHINA_ID_15 = 15;
  private static final int CHINA_ID_18 = 18;
  private static final String[] PROVINCE_CODES = {
      "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37",
      "41",
      "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
      "71",
      "81", "82", "91"
  };
  private static final int[] WEIGHT_FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
  private static final String[] VERIFY_CODE = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3",
      "2"};
  private static final Pattern PAT_NUM = Pattern.compile("^[0-9]*$");

  private IDCardUtil() {
  }

  public static boolean isValidIDCard(String idCard) {
    if (idCard == null) {
      return false;
    }
    String card = idCard.trim();
    return (validateIdCard18(card) || validateIdCard15(card));
  }

  private static boolean validateIdCard18(String idCard) {
    if (idCard.length() == CHINA_ID_18) {
      String code17 = idCard.substring(0, 17);
      String checkNum = idCard.substring(17, CHINA_ID_18);
      if (isNum(code17)) {
        int[] iCard = convertCharToInt(code17.toCharArray());
        int iSum17 = sumWithWeight(iCard);
        return VERIFY_CODE[iSum17 % 11].equalsIgnoreCase(checkNum);
      }
    }
    return false;
  }

  private static boolean validateIdCard15(String idCard) {
    if (idCard.length() != CHINA_ID_15) {
      return false;
    }
    if (!isNum(idCard)) {
      return false;
    }
    String proCode = idCard.substring(0, 2);
    return Arrays.asList(PROVINCE_CODES).contains(proCode) &&
        validateDate(Integer.valueOf(idCard.substring(6, 8)),
            Integer.valueOf(idCard.substring(8, 10)),
            Integer.valueOf(idCard.substring(10, 12)));
  }

  private static int[] convertCharToInt(char[] ca) {
    int len = ca.length;
    int[] iArr = new int[len];
    for (int i = 0; i < len; i++) {
      iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
    }
    return iArr;
  }

  private static int sumWithWeight(int[] iArr) {
    int iSum = 0;
    if (WEIGHT_FACTOR.length == iArr.length) {
      for (int i = 0; i < iArr.length; i++) {
        iSum = iSum + iArr[i] * WEIGHT_FACTOR[i];
      }
    }
    return iSum;
  }

  private static boolean isNum(String val) {
    return val != null && !"".equals(val) && PAT_NUM.matcher(val).matches();
  }

  private static boolean validateDate(int year, int month, int date) {
    int currentYear = LocalDate.now().getYear();
    int datePerMonth;
    if (year >= currentYear) {
      return false;
    }
    if (month < 1 || month > 12) {
      return false;
    }
    switch (month) {
      case 4:
      case 6:
      case 9:
      case 11:
        datePerMonth = 30;
        break;
      case 2:
        boolean dm =
            ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) && (year < currentYear);
        datePerMonth = dm ? 29 : 28;
        break;
      default:
        datePerMonth = 31;
    }
    return (date >= 1) && (date <= datePerMonth);
  }

}
