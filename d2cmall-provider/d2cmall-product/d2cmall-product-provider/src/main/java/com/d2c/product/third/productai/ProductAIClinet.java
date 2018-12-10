package com.d2c.product.third.productai;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.product.model.Product;

/**
 * 以图搜图
 * 
 * @author Administrator
 *
 */
@Component
public class ProductAIClinet {
	/**
	 * 数据集Id
	 */
	@Value("${AI_IMAGE_SET_ID}")
	private String imageSetId;
	/**
	 * 服务id
	 */
	@Value("${AI_SERVICE_ID}")
	private String serviceId;

//	private static IWebClient client;
	private static String accessKeyId = "77475ec14d13b7341d2550fd6d57071c";
	private static String secretKey = "c363fdf375ccc61d4d20e25fae89b602";
	private static final Log logger = LogFactory.getLog(ProductAIClinet.class);
//	static {
//		if (client == null) {
//			IProfile profile = new DefaultProfile(accessKeyId, secretKey, "1", null);
//			client = new DefaultProductAIClient(profile);
//		}
//	}

	/**
	 * 以url搜图
	 * 
	 * @param url
	 * @param page
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public PageResult<String> searchByUrl(String url, PageModel page) throws IOException, Exception {
		PageResult<String> pager = new PageResult<>(page);
//		try {
//			ImageSearchByImageUrlRequest request = new ImageSearchByImageUrlRequest(serviceId);
//			request.setUrl(url);
//			request.setCount(page.getPageSize());
//			// 没有返回总共有多少,只能app多请求一次，默认是按相似度排的
//			request.getOptions().put("page", page.getStartNumber() + " ");
//			String response = client.getResponse(request).getResponseJsonString();
//			JSONObject obj = JSONObject.parseObject(response);
//			if (obj != null) {
//				Set<String> set = new LinkedHashSet<>();
//				JSONArray array = (JSONArray) obj.get("results");
//				for (int i = 0; i < array.size(); i++) {
//					set.add(array.getJSONObject(i).get("metadata").toString());
//				}
//				List<String> list = new ArrayList<>();
//				list.addAll(set);
//				pager.setList(list);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
		return pager;
	}

	/**
	 * 上传商品图片
	 * 
	 * @param product
	 * @throws Exception
	 */
	public int uploadPic(Product product) throws Exception {
//		if (product != null) {
//			try {
//				ArrayList<String> tagList = new ArrayList<>();
//				tagList.add(JSONObject.parseObject(product.getTopCategory()).getString("name"));
//				JSONArray categoryArray = JSONArray.parseArray(product.getProductCategory());
//				tagList.add(((JSONObject) categoryArray.get(categoryArray.size() - 1)).getString("name"));
//				DataSetSingleAddByImageUrlRequest addImageRequest = new DataSetSingleAddByImageUrlRequest(imageSetId,
//						tagList, product.getId().toString());
//				for (String url : product.getProductImageList()) {
//					try {
//						addImageRequest.imageUrl = "http://img.d2c.cn/" + url;
//						client.getResponse(addImageRequest);
//					} catch (Exception e) {
//						logger.error(e.getMessage(), e);
//					}
//				}
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
		return 1;
	}

}
