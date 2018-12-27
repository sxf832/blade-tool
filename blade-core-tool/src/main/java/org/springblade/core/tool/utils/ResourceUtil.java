/**
 * Copyright (c) 2018-2028, DreamLu 卢春梦 (qq596392912@gmail.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.core.tool.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * 资源工具类
 *
 * @author L.cm
 */
public class ResourceUtil extends org.springframework.util.ResourceUtils {
	public static final String HTTP_REGEX = "^https?:.+$";
	public static final String FTP_URL_PREFIX = "ftp:";

	/**
	 * 获取资源
	 * <p>
	 * 支持一下协议：
	 * <p>
	 * 1. classpath:
	 * 2. file:
	 * 3. ftp:
	 * 4. http: and https:
	 * 5. classpath*:
	 * 6. C:/dir1/ and /Users/lcm
	 * </p>
	 *
	 * @param resourceLocation 资源路径
	 * @return {Resource}
	 * @throws IOException IOException
	 */
	public static Resource getResource(String resourceLocation) throws IOException {
		Assert.notNull(resourceLocation, "Resource location must not be null");
		if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
			return new ClassPathResource(resourceLocation);
		}
		if (resourceLocation.startsWith(FTP_URL_PREFIX)) {
			return new UrlResource(resourceLocation);
		}
		if (resourceLocation.matches(HTTP_REGEX)) {
			return new UrlResource(resourceLocation);
		}
		if (resourceLocation.startsWith(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX)) {
			return SpringUtil.getContext().getResource(resourceLocation);
		}
		return new FileSystemResource(resourceLocation);
	}

}
