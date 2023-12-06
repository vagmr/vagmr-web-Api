package spt.vagmr.webdev.service.words;

import spt.vagmr.webdev.vo.words.WordsItem;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:14
 * springBootProject
 * @Description
 */

public interface GetService {

    List<WordsItem> getAll();
}
