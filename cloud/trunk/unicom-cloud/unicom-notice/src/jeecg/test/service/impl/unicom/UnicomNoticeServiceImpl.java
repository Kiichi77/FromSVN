package jeecg.test.service.impl.unicom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeecg.test.service.unicom.UnicomNoticeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("unicomNoticeService")
@Transactional
public class UnicomNoticeServiceImpl extends CommonServiceImpl implements UnicomNoticeServiceI {
	
}