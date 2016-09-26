package me.jcala.blog.controller;

import me.jcala.blog.domain.BlogView;
import me.jcala.blog.domain.Info;
import me.jcala.blog.service.inter.BlogSer;
import me.jcala.blog.service.inter.InfoSer;
import me.jcala.blog.service.inter.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FontEndCtrl {
    @Autowired
    private BlogSer blogSer;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private InfoSer infoSer;
    @GetMapping("/archives/{page}")
    public String archives(@PathVariable int page,Model model){
        model.addAttribute("info",infoSer.getInfo());
        model.addAttribute("archives",blogSer.getArchive(page));
        model.addAttribute("pageNum",blogSer.getArchiveNum());
        model.addAttribute("current",page);
        return "archives";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("info",infoSer.getInfo());
        return "projects";
    }
    @GetMapping("/projects/{page}")
    public String projectPage(@PathVariable int page,Model model) {
        model.addAttribute("info",infoSer.getInfo());
        model.addAttribute("projects",projectSer.getPros(page));
        model.addAttribute("pageNum",projectSer.getPageNum());
        model.addAttribute("current",page);
        return "projects";
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("info",infoSer.getInfo());
        model.addAttribute("tags",blogSer.getTagList());
        return "tags";
    }
    @GetMapping("/tags/{tagName}")
    public String tagName(@PathVariable String tagName,Model model) {
        model.addAttribute("info",infoSer.getInfo());
        List<BlogView> views=blogSer.getBlogByTag(tagName);
        model.addAttribute("views",views);
        model.addAttribute("tagName",tagName);
        return "tagView";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("info",infoSer.getInfo());
        model.addAttribute("resume",infoSer.getResumeView());
        return "about";
    }
    @GetMapping("/post/{id}")
    public String post(@PathVariable int id,Model model) {
        model.addAttribute("info",infoSer.getInfo());
        BlogView blogView=blogSer.getBlog(id);
        BlogView prev=blogSer.getPrevBlog(id);
        BlogView next=blogSer.getNextBlog(id);
        model.addAttribute("prev",prev);
        model.addAttribute("next",next);
        model.addAttribute("article",blogView.getArticle());
        return "post";
    }
    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("info",infoSer.getInfo());
        return "index";
    }
}
