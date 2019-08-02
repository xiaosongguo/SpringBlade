/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.Ismg;

/**
 * 网关表视图实体类
 *
 * @author Blade
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IsmgVO对象", description = "网关表")
public class IsmgVO extends Ismg {
	private static final long serialVersionUID = 1L;

	private String signName;

	private  Long channelId;

	private String dictValue;//字典lable

	private Integer dictKey;//字典value
}
