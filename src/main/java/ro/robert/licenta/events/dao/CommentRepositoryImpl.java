package ro.robert.licenta.events.dao;


import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Comment;

@Singleton
public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment> implements CommentRepository{

	public CommentRepositoryImpl() {
		super(Comment.class);
		// TODO Auto-generated constructor stub
	}

	

}
