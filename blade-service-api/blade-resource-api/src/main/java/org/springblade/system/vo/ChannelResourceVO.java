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

import org.springblade.system.entity.ChannelResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 通道资源表视图实体类
 *
 * @author Blade
 * @since 2019-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ChannelResourceVO对象", description = "通道资源表")
public class ChannelResourceVO extends ChannelResource {
	private static final long serialVersionUID = 1L;

	private Double unitPrice;

	private String supplierName;

	private String tenantCode;
}
