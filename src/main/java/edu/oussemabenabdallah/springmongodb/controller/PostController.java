package edu.oussemabenabdallah.springmongodb.controller;

import edu.oussemabenabdallah.springmongodb.model.Post;
import edu.oussemabenabdallah.springmongodb.repositories.PostsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("posts")
@Controller
public class PostController {

    private final PostsRepository repository;

    public PostController(PostsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public String getAllPosts(Model model) {
        model.addAttribute("posts", repository.findAll());
        return "posts";
    }

    @RequestMapping(path = "/{id}")
    public String getPostsById(@PathVariable("id") String id, Model model) {
        model.addAttribute("post", repository.findById(id).orElse(null));
        return "post-details";
    }

    @RequestMapping(path = "title/{title}")
    public Post getPostsByTitle(@PathVariable("title") String title) {
        return repository.findByTitle(title);
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "add-post";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute Post post) {
        repository.save(post);
        return "redirect:/posts";
    }

    @RequestMapping(path = "delete/{id}")
    public String deletePost(@PathVariable("id") String id) {
        repository.delete(Objects.requireNonNull(repository.findById(id).orElse(null)));
        return "redirect:/posts";

    }

    @RequestMapping(path = "edit/{id}", method = RequestMethod.POST)
    public String updatePost(@PathVariable("id") String id, @ModelAttribute Post post) {
        post.setId(id);
        repository.save(post);
        return "redirect:/posts";
    }

    @RequestMapping(path = "edit/{id}", method = RequestMethod.GET)
    public String updatePost(@PathVariable("id") String id, Model model) {
        Post post = repository.findById(id).orElse(new Post());
        model.addAttribute("post", post);
        return "edit-post";
    }
}
