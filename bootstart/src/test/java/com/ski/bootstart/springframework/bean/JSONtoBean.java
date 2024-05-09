package com.ski.bootstart.springframework.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangzijie
 * @date 2024/5/9
 */
public class JSONtoBean {

    @Data
    public static class FollowReadingScoreVo implements Serializable {

        /**
         * 发音准确度
         */
        private Double accuracyScore;
        /**
         * 总分
         */
        private Double pronunciationScore;
        /**
         * 完整性
         */
        private Double completenessScore;
        /**
         * 流畅度
         */
        private Double fluencyScore;
        /**
         * 韵律得分
         */
        private Double prosodyScore;
        /**
         * 得分详情
         */
        private List<PronunciationAssessmentResultJson> details;


        @Data
        public static class PronunciationAssessmentResultJson implements Serializable {
            /**
             * 原文
             */
            private String DisplayText;
            /**
             *
             */
            private List<NBest> NBest;
        }

        @Data
        public static class NBest implements Serializable {
            private List<Word> Words;
        }

        @Data
        public static class Word implements Serializable {

            private String Word;

            private  PronunciationAssessment PronunciationAssessment;
        }

        @Data
        public static class PronunciationAssessment implements Serializable {

            private Double AccuracyScore;

            private String ErrorType;
        }


    }


        public static void main(String[] args) {
            String json = "{\"accuracyScore\":0.8,\"pronunciationScore\":0.9,\"completenessScore\":0.7,\"fluencyScore\":0.85,\"prosodyScore\":0.88,\"details\":[{\"DisplayText\":\"Original text\",\"NBest\":[{\"Words\":[{\"Word\":\"word1\",\"PronunciationAssessment\":{\"AccuracyScore\":0.8,\"ErrorType\":\"Type1\"}},{\"Word\":\"word2\",\"PronunciationAssessment\":{\"AccuracyScore\":0.7,\"ErrorType\":\"Type2\"}}]}]}]}";

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                FollowReadingScoreVo followReadingScoreVo = objectMapper.readValue(json, FollowReadingScoreVo.class);
                System.out.println(followReadingScoreVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
