package edu.oussemabenabdallah.springmongodb.repositories;

import edu.oussemabenabdallah.springmongodb.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostsRepository extends MongoRepository<Post, String> {

    Optional<Post> findById(String id);

    Post findByTitle(String title);
}
