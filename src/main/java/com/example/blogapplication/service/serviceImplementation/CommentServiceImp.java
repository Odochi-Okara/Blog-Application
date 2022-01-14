package com.example.blogapplication.service.serviceImplementation;


import com.example.blogapplication.model.Comment;
import com.example.blogapplication.model.Post;
import com.example.blogapplication.repository.CommentRepository;
import com.example.blogapplication.repository.PostRepository;
import com.example.blogapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    private final PostRepository postRepository;
    private  final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImp(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean createComment(Comment comment, Long person_id, Long post_id) {

        boolean flag = false;
        Post post = postRepository.getPostById(post_id);

        comment.setPost(post);
        commentRepository.save(comment);
        flag = true;

        return flag;
    }
}
