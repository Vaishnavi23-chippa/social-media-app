package com.cg.socialmedia.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.socialmedia.entity.Post;
import com.cg.socialmedia.exception.ResourceNotFoundException;
import com.cg.socialmedia.repository.PostRepo;
import com.cg.socialmedia.service.PostService;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepo postRepo;
	
	@PostMapping("/post")
	public Post uploadPost(@RequestBody Post p)
	{
	return postService.uploadPost(p);
	}
	
	@GetMapping("/posts")
	public List<Post> viewAllPosts(){
		return postService.viewAllPosts();
	}
	
	@GetMapping("/post/{id}")
	public Post searchPostById(@PathVariable long id) {
		return postService.searchByPostId(id);	
	}
	

	@DeleteMapping("/post/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePost(@PathVariable long id) throws ResourceNotFoundException{
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not exist with id :" + id));
       
        postRepo.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
	
	


}
