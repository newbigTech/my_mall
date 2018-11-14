package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.member.model.Comment;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;

public interface CommentService {

	PageResult<Comment> findCommentsByQuery(PageModel page, ProQuery query);

	Comment insert(Comment comment);

	Date getLastSysDate();

	void save(List<Comment> comments);

	Comment findById(Long id);

}
