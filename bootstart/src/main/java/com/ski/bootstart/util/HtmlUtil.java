package com.ski.bootstart.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author ski
 * @date 2024/1/31
 */
@Slf4j
public class HtmlUtil {

    public static String html2Text(String inputString) {
        //含html标签的字符串
        String htmlStr = inputString;
        String textStr = "";
        java.util.regex.Pattern pScript;
        java.util.regex.Matcher mScript;
        java.util.regex.Pattern pStyle;
        java.util.regex.Matcher mStyle;
        java.util.regex.Pattern pHtml;
        java.util.regex.Matcher mHtml;

        try {
            //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regExScript = "<\\s*?script[^>]*?>[\\s\\S]*?<\\s*?/\\s*?script\\s*?>";
            //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            //定义HTML标签的正则表达式
            String regExHtml = "<[^>]+>";

            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            //过滤script标签
            htmlStr = mScript.replaceAll("");

            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            //过滤style标签
            htmlStr = mStyle.replaceAll("");

            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            //过滤html标签
            htmlStr = mHtml.replaceAll("");

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        //返回文本字符串
        return textStr;
    }

    public static void main(String[] args) {
        String text = "<p><script src=\"https://lf3-cdn-tos.bytecdntp.com/cdn/expire-1-M/jquery/2.0.3/jquery.min.js\"></script></p>\n" +
                "<div id=\"ai-assist-root-text\" data-v-app=\"\">\n" +
                "<p><span>The construction of the Erie Canal, which is a famous waterway,<img src=\"smiley-2.gif\" alt=\"Smiley face\" width=\"42\" height=\"42\">\n faced and overcame three challenges. </span></p>\n" +
                "<p><span>The first challenge was that the canal had to be built through forest, however, useful devices had been invited and animals helped to pull the devices which reduce the difficulty of digging in the forest; Additionally, considering that the canal had to pass through wetlands where the workers could easily get malaria, the work were done in winter so the mosquitoes that cause the disease were not active.</span></p>\n" +
                "<p><span>The second challenge was a lack of workers. Nevertheless, the European immigration brought sufficient number of workers who had urgent needs for jobs.</span></p>\n" +
                "<p><span>Finally, the facility of the canal cut down the time and money cost for travelling to the Midwest to a large extent, thus a lot of people moved there and generated enough commerce that covered the cost of construction.</span></p>\n" +
                "</div>";

        String s = html2Text(text);
        log.info(s);
    }
}
