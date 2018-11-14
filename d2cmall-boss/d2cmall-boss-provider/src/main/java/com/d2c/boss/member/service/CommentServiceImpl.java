package com.d2c.boss.member.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.CommentMapper;
import com.d2c.boss.member.model.Comment;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("commentService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CommentServiceImpl extends ListServiceImpl<Comment> implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public PageResult<Comment> findCommentsByQuery(PageModel page, ProQuery query) {
		PageResult<Comment> pager = new PageResult<Comment>(page);
		int count = commentMapper.countCommentsByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(commentMapper.findCommentsByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Comment insert(Comment comment) {
		Comment result = this.save(comment);
		return result;
	}

	@Override
	public Date getLastSysDate() {
		return commentMapper.getLastSysDate();
	}

	@Override
	public void save(List<Comment> comments) {
	}

	@Override
	public Comment findById(Long id) {
		return this.findOneById(id);
	}

}