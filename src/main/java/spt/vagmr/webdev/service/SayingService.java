package spt.vagmr.webdev.service;

import spt.vagmr.webdev.vo.EnglishData;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/31-14:07
 * springBootProject
 * @Description
 */
public interface SayingService {
    List<EnglishData> querySaying();
}
