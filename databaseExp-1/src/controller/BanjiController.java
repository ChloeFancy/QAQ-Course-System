package controller;

import model.BanjiEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/class")
@CrossOrigin("http://localhost:8081")
public class BanjiController extends BaseController<BanjiEntity> {

}
