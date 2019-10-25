package com.example.cc.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cc on 2018/3/15.
 */

public class ChineseCharUtil {

    private static Pattern PATTERN = Pattern.compile("[\u4e00-\u9fa5]"); // 中文

    private static Pattern NOT_CHINESE_NUM_LETTER_PATTERN = Pattern.compile("[^\\u4e00-\\u9fa5a-zA-Z0-9]+");
    // 非中文、字母、数字

    /**
     * 中文、中文标点算2个字符，字母和数字为1个字符
     *
     * @param value 需要计算的文字内容
     * @return 文字长度
     */
    public static int getLength(String value) {
        if (TextUtils.isEmpty(value)) {
            return 0;
        }
        Matcher matcher = PATTERN.matcher(value);
        int chineseCount = 0;
        while (matcher.find()) {
            chineseCount++;
        }
        int chinesePunctuationCount = 0; // 中文标点
        for (int i = 0; i < value.length(); i++) {
            char singleChar = value.charAt(i);
            if (isChinesePunctuation(singleChar)) {
                chinesePunctuationCount++;
            }
        }
        int allCount = value.length();
        int otherCount = allCount - chineseCount - chinesePunctuationCount;
        int charCount = chineseCount * 2 + chinesePunctuationCount * 2 + otherCount;
        return charCount;
    }

    public static int getLength(CharSequence value) {
        if (TextUtils.isEmpty(value)) {
            return 0;
        }
        return getLength(value.toString());
    }

    /**
     * 根据UnicodeBlock方法判断中文标点符号
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isChinese(char c) {
        try {
            String string = Character.toString(c);
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            Matcher matcher = PATTERN.matcher(string);
            if (matcher.find()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getChinese(String text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        Matcher matcher = PATTERN.matcher(text);
        StringBuffer stringBuffer = new StringBuffer("");
        while (matcher.find()) {
            stringBuffer.append(matcher.group());
        }
        return stringBuffer.toString();
    }

    /**
     * 是否为英文标点符号
     */
    public static boolean isEnglishPuncPunctuation(char ch) {
        if (0x21 <= ch && ch <= 0x22) return true;
        if (ch == 0x27 || ch == 0x2C) return true;
        if (ch == 0x2E || ch == 0x3A) return true;
        if (ch == 0x3B || ch == 0x3F) return true;
        return false;
    }

    /**
     * 是否为emoji
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 是否非中文、英文、数字
     */
    private static boolean isNotChineseAndNumAndLetter(char ch) {
        Matcher matcher = NOT_CHINESE_NUM_LETTER_PATTERN.matcher(String.valueOf(ch));
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为特殊符号（非中文、非数字、非英文标点、非中文标点、非字母、非emoji）
     *
     * @return
     */
    private static boolean isSpecialChar(char ch) {
        if (!isChinesePunctuation(ch)
                && !isEnglishPuncPunctuation(ch)
                && !isEmojiCharacter(ch)
                && isNotChineseAndNumAndLetter(ch)) {
            return true;
        }
        return false;
    }


    /**
     * 获取有emoji的时候的长度，中文及中文符号占两个，英文及emoji还有其他占一个
     * */
//    public static int getLengthWithEmoji(String value){
//        return getLength(value)+EmojiHelper.getEmojiCount(value)-EmojiHelper.getEmojiLength(value)-EmojiHelper.getEmojiLinkCount(value);
//    }

}
