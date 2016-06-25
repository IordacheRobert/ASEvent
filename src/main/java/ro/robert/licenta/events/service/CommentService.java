package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Comment;

public interface CommentService {
	
	List<Comment> getListFromNamedParams(Map<String, Object> params);

	Comment saveOrUpdate(Comment item);

	Comment getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);
}
