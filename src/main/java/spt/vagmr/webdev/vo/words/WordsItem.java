package spt.vagmr.webdev.vo.words;

import lombok.Data;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:08
 * springBootProject
 * @Description
 */
@Data
public class WordsItem {
    private Integer id;
    private String cet4Word;
    private String cet4Phonetic;
    private String cet4Translate;
    private String cet4Distortion;
    private String cet4Phrase;
    private String cet4Samples;
}
