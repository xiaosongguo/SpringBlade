package org.springblade.system.schedule;

import lombok.AllArgsConstructor;
import org.springblade.system.service.IBillService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * - 第一位，表示秒，取值 0-59；
 * - 第二位，表示分，取值 0-59；
 * - 第三位，表示小时，取值 0-23；
 * - 第四位，日期天/日，取值 1-31；
 * - 第五位，日期月份，取值 1-12；
 * - 第六位，星期，取值 1-7，星期一、星期二…；
 * 注：不是第1周、第2周的意思，另外：1表示星期天，2表示星期一。
 * - 第七位，年份，可以留空，取值 1970-2099。
 * （*）星号：可以理解为每的意思，每秒、每分、每天、每月、每年……。
 * （?）问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天 3 点执行，所以第六位星期的位置是不需要关注的，就是不确定的值。同时，日期和星期是两个相互排斥的元素，通过问号来表明不指定值。假如 1 月 10 日是星期一，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
 * （-）减号：表达一个范围，如在小时字段中使用“10-12”，则表示从 10~12 点，即 10、11、12。
 * （,）逗号：表达一个列表值，如在星期字段中使用“1、2、4”，则表示星期一、星期二、星期四。
 * （/）斜杠：如 x/y，x 是开始值，y 是步长，比如在第一位（秒） 0/15 就是，从 0 秒开始，每 15 秒，最后就是 0、15、30、45、60，另
 */
@Component
@AllArgsConstructor
public class SchedulerTask {

	private IBillService billService;

	@Scheduled(cron = "0 0 0 4 * ?")
	private void createBills() {
		billService.createBills();
	}

}
