package controller;


import model.TeacherEntity;
import model.TeachingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teaching")
@CrossOrigin("http://localhost:8081")
public class TeachingController extends BaseController<TeachingEntity>{

}
