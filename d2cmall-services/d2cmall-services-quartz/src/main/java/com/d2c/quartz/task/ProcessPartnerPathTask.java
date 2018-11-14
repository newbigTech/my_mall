package com.d2c.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.member.model.Partner;
import com.d2c.member.query.PartnerSearcher;
import com.d2c.member.service.PartnerService;
import com.d2c.quartz.task.base.BaseTask;
import com.d2c.quartz.task.base.EachPage;

@Component
public class ProcessPartnerPathTask extends BaseTask {

	@Autowired
	private PartnerService partnerService;

	@Scheduled(fixedDelay = 60 * 1000 * 60)
	public void execute() {
		// if (properties.getDebug()) {
		// return;
		// }
		super.exec();
	}

	@Override
	public void execImpl() {
		this.doInitPath();
	}

	private void doInitPath() {
		try {
			PartnerSearcher searcher = new PartnerSearcher();
			this.processPager(500, new EachPage<Partner>() {

				@Override
				public int count() {
					return partnerService.countBySearcher(searcher);
				}

				@Override
				public PageResult<Partner> search(PageModel page) {
					return partnerService.findBySearcher(searcher, page);
				}

				@Override
				public boolean each(Partner object) {
					if (object.getPath() == null) {
						String path = object.getId().toString();
						Long parentId = object.getParentId();
						while (parentId != null) {
							Partner parent = partnerService.findById(parentId);
							if (parent != null) {
								path = path + "," + parent.getId();
								parentId = parent.getParentId();
							} else {
								break;
							}
						}
						partnerService.updatePath(object.getId(), path);
					}
					return true;
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
