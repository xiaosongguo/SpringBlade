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
package org.springblade.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.boot.file.BladeFile;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.SystemConstant;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.system.entity.FileManager;
import org.springblade.system.feign.IFileManagerClient;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 *	文件上传下载
 */
@RestController
@AllArgsConstructor
@Api(value = "", tags = "接口")
public class FileController extends BladeController {

	private IFileManagerClient fileManagerClient;

	/**
	 * 上传
	 */
	@PostMapping("/upload")
	@ApiOperation(value = "上传", notes = "", position = 8)
	public R upload(@RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "0") Integer fileType) {
		String userAccount = SecureUtil.getUserAccount();
		BladeFile bladeFile = this.getFile(file, userAccount);
		boolean transfer = bladeFile.transfer(false);
		FileManager fileManager = new FileManager();
		if (transfer) {
			fileManager.setName(bladeFile.getOriginalFileName());
			fileManager.setPath(bladeFile.getUploadVirtualPath());
			fileManager.setUserId(SecureUtil.getUserId());
			fileManager.setStatus(0);
			fileManager.setFileType(fileType);
		}
		//保存到数据库
		return this.fileManagerClient.upload(fileManager);
	}

	/**
	 * 文件下载
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/download")
	public ResponseEntity<Resource> downloadCacheFile(@RequestParam("fileName") String fileName) throws IOException {
			// 获取文件名称，中文可能被URL编码
			fileName = URLDecoder.decode(fileName, "UTF-8");
			// 获取本地文件系统中的文件资源
			FileSystemResource resource = new FileSystemResource(SystemConstant.me().getRemotePath() + fileName);

			// 解析文件的 mime 类型
			String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
			// 无法判断MIME类型时，作为流类型
			mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
			// 实例化MIME
			MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);

			/*
			 * 构造响应的头
			 */
			HttpHeaders headers = new HttpHeaders();
			// 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
			String downfile = fileName.substring(fileName.lastIndexOf(StringPool.SLASH)+1);

			String filenames = new String(downfile.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
			headers.setContentDispositionFormData("attachment", filenames);
			headers.setContentType(mediaType);

			/*
			 * 返还资源
			 */
			return ResponseEntity.ok()
				.headers(headers)
				.contentLength(resource.getInputStream().available())
				.body(resource);

	}


}
