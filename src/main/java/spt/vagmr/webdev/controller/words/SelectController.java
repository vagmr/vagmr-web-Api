package spt.vagmr.webdev.controller.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spt.vagmr.webdev.service.words.GetService;
import spt.vagmr.webdev.vo.Result;
import spt.vagmr.webdev.vo.words.WordsItem;

import java.util.List;

/**
 * @author vagmr
 * @version 0.0.1
 * 2023/12/6-0:12
 * springBootProject
 * @Description
 */
@RestController
@RequestMapping("/words")
public class SelectController {
    @Autowired
    private GetService getService;

    @GetMapping("/all")
    public Result<List<WordsItem>> getAll(){
        return Result.success(getService.getAll());
    }
}
