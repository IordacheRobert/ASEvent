package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.CommentRepository;
import ro.robert.licenta.events.dao.CommentRepositoryImpl;
import ro.robert.licenta.events.model.Comment;


@Singleton
public class CommentServiceImpl implements CommentService{

	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private CommentRepository commentRepository;

    @InjectParam
    public void setCommentRepository(CommentRepositoryImpl commentRepository) {
        this.commentRepository = commentRepository;
    }

    
    public List<Comment> getListFromNamedParams(Map<String, Object> params) {
        // Some other business
        return commentRepository.getListFromNamedParams(params);
    }

    public Comment saveOrUpdate(Comment item) {
        // Some other business
        return commentRepository.saveOrUpdate(item);
    }

    public Comment getSingleFromNamedParams(Map<String, Object> params) {
        // Some other business
        return commentRepository.getSingleValue(params);
    }

    public Integer delete(String deleteById, Long id) {
        // Some other business
        return commentRepository.delete(deleteById, id);
    }

}
