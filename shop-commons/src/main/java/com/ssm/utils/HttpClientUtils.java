package com.ssm.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.*;

public class HttpClientUtils {
    private static final String CHARSET="utf-8";

    public static String get(String url, Map<String,String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String responseText=null;

        try {
            URIBuilder uriBuilder=new URIBuilder(url);
            LinkedList<NameValuePair> list=new LinkedList<>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();

            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }

            uriBuilder.setParameters(list);

            HttpGet httpGet=new HttpGet(uriBuilder.build());
            httpResponse = httpClient.execute(httpGet);
            //getEntity()方法获得 HttpEntity对象  该对象封装了响应数据
            HttpEntity entity = httpResponse.getEntity();
            //EntityUtils toString方法可以快速获取响应内容

            responseText=EntityUtils.toString(entity,CHARSET);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //6.释放资源
            //先开的后关
            if(httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return responseText;
        }



    }

    public static String get(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String responseText=null;
        
        
        try {
            HttpGet httpGet=new HttpGet(url);

            httpResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();

            responseText= EntityUtils.toString(entity, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }        finally {
            //6.释放资源
            //先开的后关
            if(httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return responseText;
        }
    }


    public static String post(String url,Map<String,String> params){
        //1.创建CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String responseText=null;

        //2.创建HttpPost 对象 并指定路径
        HttpPost httpPost=new HttpPost(url);


        List<NameValuePair> list=new LinkedList<>();

        //3.准备post参数  设置参数
        if(params!=null&&params.size()>0){

            //便利Map设置参数
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            Iterator<Map.Entry<String, String>> it = entrySet.iterator();
            while(it.hasNext()){
                Map.Entry<String, String> entry = it.next();
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }



        try {
            //设置参数   制定编码
            httpPost.setEntity(new UrlEncodedFormEntity(list,CHARSET));
            //设置请求头等

            //  httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            //4.发送请求
            httpResponse  = httpClient.execute(httpPost);

            if(httpResponse.getStatusLine().getStatusCode()==200){//响应状态吗是200

                //5.取数实体内容
                HttpEntity entity = httpResponse.getEntity();



                responseText=EntityUtils.toString(entity,CHARSET);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //6释放资源
            //先开的后关
            if(httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return responseText;
        }

    }

    public  static String  delete(String url){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseText=null;

        CloseableHttpResponse httpResponse=null;

        HttpDelete httpDelete=new HttpDelete(url);


        try {
            httpResponse = httpClient.execute(httpDelete);

            if(httpResponse.getStatusLine().getStatusCode()==200){

                responseText=EntityUtils.toString(httpResponse.getEntity(),CHARSET);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //6释放资源
            //先开的后关
            if(httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return responseText;
        }

    }

    public  static  String  put(String url,Map<String,String> params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String responseText=null;


        HttpPut httpPut=new HttpPut(url);

        //设置参数 private String productName;
        //
        //    private String barCode;
        //
        //    private Long brandId;
        List<NameValuePair> list=new LinkedList<>();
        if(params!=null&&params.size()>0){
            Set<String> keySet = params.keySet();

            Iterator<String> it = keySet.iterator();
            while(it.hasNext()){
                String key = it.next();
                list.add(new BasicNameValuePair(key,params.get(key)));


            }
        }

        try {
            //设置参数
            httpPut.setEntity(new UrlEncodedFormEntity(list,CHARSET));
            //请求
            httpResponse = httpClient.execute(httpPut);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                //打印实体


                responseText=EntityUtils.toString(httpResponse.getEntity(),CHARSET);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(httpClient!=null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return  responseText;
        }


    }
}
