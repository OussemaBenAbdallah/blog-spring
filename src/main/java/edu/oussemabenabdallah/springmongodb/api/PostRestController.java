package edu.oussemabenabdallah.springmongodb.api;

import edu.oussemabenabdallah.springmongodb.model.Post;
import edu.oussemabenabdallah.springmongodb.repositories.PostsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("api/v1/post")
@RestController
@CrossOrigin(origins = "*")
public class PostRestController {

    private final PostsRepository repository;

    public PostRestController(PostsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Post getPostsById(@PathVariable("id") String id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping(path = "/title/{title}")
    public Post getPostsByTitle(@PathVariable("title") String title) {
        return repository.findByTitle(title);
    }

    @PostMapping
    public String addPost(@RequestBody Post post) {
        repository.save(post);
        return "post: " + post + " added successfully!";
    }

    @DeleteMapping(path = "delete/{id}")
    public String deletePost(@PathVariable("id") String id) {
        repository.delete(Objects.requireNonNull(repository.findById(id).orElse(null)));
        return "delete successful";

    }

    @PutMapping(path = "edit/{id}")
    public String updatePost(@PathVariable("id") String id, @RequestBody Post post) {
        post.setId(id);
        repository.save(post);
        return "updated successfully: " + post;

    }
}
