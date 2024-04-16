package com.ski.bootstart.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author ski
 * @date 2023/11/21
 * 两个字符串互为字母异位词，当且仅当两个字符串包含的字母相同。
 * 同一组字母异位词中的字符串具备相同点，可以使用相同点作为一组字母异位词的标志，使用哈希表存储每一组字母异位词，
 * 哈希表的键为一组字母异位词的标志，哈希表的值为一组字母异位词列表。
 * 遍历每个字符串，对于每个字符串，得到该字符串所在的一组字母异位词的标志，将当前字符串加入该组字母异位词的列表中。
 * 遍历全部字符串之后，哈希表中的每个键值对即为一组字母异位词。
 */
@Slf4j
public class GroupAnagrams {

    /**
     * 排序
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
     * 故可以将排序之后的字符串作为哈希表的键。
     */
    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     *计数
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 262626 的数组记录每个字母出现的次数。需要注意的是，在使用数组作为哈希表的键时，
     * 不同语言的支持程度不同，因此不同语言的实现方式也不同。
     */
    public static List<List<String>> groupAnagramsCount(String[] strings) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strings) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        String[] strings ={"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strings);
        log.info(lists.toString());

    }

}
