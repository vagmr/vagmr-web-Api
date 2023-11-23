package spt.vagmr.webdev.service.impl;

import org.springframework.stereotype.Service;
import spt.vagmr.webdev.mapper.SayingMapper;
import spt.vagmr.webdev.service.SayingService;
import spt.vagmr.webdev.vo.EnglishData;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/10/31-14:10
 * springBootProject
 * @Description
 */
@Service
public class SayingServiceImpl implements SayingService {
    private final SayingMapper sayingMapper;
    private static final int Page_Limit = 10;

    //加了final得用构造器注入
    public SayingServiceImpl(SayingMapper sayingMapper) {
        this.sayingMapper = sayingMapper;
    }

    @Override
    public List<EnglishData> querySaying() {
        return sayingMapper.selectAll(Page_Limit);
    }
}
