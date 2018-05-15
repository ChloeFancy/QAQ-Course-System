package controller;

import model.CourseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/course")
@CrossOrigin("http://localhost:8081")
public class CourseController extends BaseController<CourseEntity> {


}
