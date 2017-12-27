package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;


/**
 * HTTP访问调用工具类
 * @author zhouqi
 *
 */
public class HttpUtil {

	/**
	 * 生成xml格式参数
	 * @return
	 * @throws Exception
	 */
	public static String createXmlParam(Map<String, String> data) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (String in : data.keySet()) {
			if(null != data.get(in) && ""!=data.get(in)){
				sb.append("<").append(in).append(">").append(data.get(in)).append("</").append(in).append(">");
			}
		}
		sb.append("</xml>");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * XML参数类型接口访问
	 * @param url 接口路径
	 * @param xmlParam xml类型参数
	 * @return
	 * @throws Exception
	 */
	public static String xmlClient(String url ,String xmlParam) throws Exception {
		String str=null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestEntity(new StringRequestEntity(xmlParam, "text/xml", "UTF-8"));
		client.executeMethod(post);
		byte[] by = post.getResponseBody();
		str=new String(by);
		 //释放连接
        post.releaseConnection();
        return str;
    }
	
	/**
	 * json参数类型接口访问
	 * @param url 接口路径
	 * @param jsonParam json类型参数
	 * @return
	 * @throws Exception
	 */
	public static String jsonClient(String url ,String jsonParam) throws Exception {
		String str=null;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		post.setRequestEntity(new StringRequestEntity(jsonParam, "application/json", "UTF-8"));
		client.executeMethod(post);
		byte[] by = post.getResponseBody();
		str=new String(by);
		 //释放连接
        post.releaseConnection();
        return str;
    }
	
	/**
	 * 参数拼接类型接口访问
	 * @param url
	 * @param requestMethod
	 * @return
	 */
	public static String strClient(String url,String requestMethod) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
            if(null != requestMethod && "POST".equalsIgnoreCase(requestMethod)){
            	httpURLConnection.setRequestMethod("POST");
            }else{
            	httpURLConnection.setRequestMethod("GET");
            }
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            httpURLConnection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
            		httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 将路径参数集合进行URL拼接
	 * @param map
	 * @return 拼接后的路径?参数字符串
	 */
	public static String urlDataMapToStr(Map<String, String> map,String url){
		StringBuilder sb = new StringBuilder();
		sb.append(url).append("?");
		for (String in : map.keySet()) {
			if(null != map.get(in) && ""!=map.get(in)){
				sb.append(in).append("=").append(map.get(in)).append("&");
			}
		}
		String str=sb.toString();
		return str.substring(0, str.length()-1);
	}
	
	/**
	 * application/x-www-form-urlencoded类型post访问
	 * @param urlReq 访问路径
	 * @param param 参数（key=val&key=val）
	 * @return
	 */
	public static String postFormReq(String urlReq, String param) {
		String result = "";
	    BufferedReader in = null;
        try {
            URL url = new URL(urlReq);
            URLConnection urlConnection = url.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 得到请求的输出流对象
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            // 把数据写入请求的Body
            out.write(param);
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
//            String encoding = urlConnection.getContentEncoding();
            in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
