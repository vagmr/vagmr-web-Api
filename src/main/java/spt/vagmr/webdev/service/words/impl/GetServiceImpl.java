package spt.vagmr.webdev.service.words.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spt.vagmr.webdev.mapper.secondary.WordsMapper;
import spt.vagmr.webdev.service.words.GetService;
import spt.vagmr.webdev.vo.words.WordsItem;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:18
 * springBootProject
 * @Description
 */
@Service
public class GetServiceImpl implements GetService {
    @Autowired
    private WordsMapper wordsMapper;
    @Override
    public List<WordsItem> getAll() {
        return wordsMapper.queryAll();
    }
}
