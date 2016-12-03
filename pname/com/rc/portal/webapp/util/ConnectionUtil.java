package com.rc.portal.webapp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ws on 2015/4/30.
 */
public class ConnectionUtil {


    public static String getContentFromUrlByGetMethod(String urlStr )
    {
        return getContentFromUrlMethod(urlStr, "GET");
    }

    public static String getContentFromUrlMethod(String urlStr, String method )
    {
        if( StringUtil.isNull( method ) )
        {
            method = "POST";
        }
        return getContentFromUrl(urlStr, "", "utf-8", method);
    }

    public static String getContentFromUrl(String urlStr )
    {
        return getContentFromUrl(urlStr, "");
    }
    public static String getContentFromUrl(String urlStr, String postData )
    {
        return getContentFromUrl(urlStr, postData, "utf-8", "POST");
    }

    public static String getContentFromUrlXml(String urlStr, String postData )
    {
        return getContentFromUrl(urlStr, postData, "text/xml", "utf-8", "POST");
    }

    public static String getContentFromUrl(String urlStr, String postData,  String chartName, String method )
    {
        return getContentFromUrl( urlStr, postData, "", chartName, method );
    }
    public static String getContentFromUrl(String urlStr, String postData, String contentType, String chartName, String method )
    {
        StringBuilder res = new StringBuilder();

        if( StringUtil.isNull( chartName ) )
        {
            chartName = "UTF-8";
        }
        if( StringUtil.isNull( method ) )
        {
            method = "POST";
        }
        try {
            URL url = new URL( urlStr );

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method.toUpperCase());
            if( !StringUtil.isNull( contentType ) ) {
                urlConnection.setRequestProperty("ContentType", contentType + "; charset=" + chartName);
            }
            urlConnection.setRequestProperty("Charset", chartName);
            if( "POST".equalsIgnoreCase( method.trim() ))
            {
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(postData.getBytes(chartName));
                urlConnection.getOutputStream().close();
            }
            if( urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK )
            {
                BufferedReader in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(), chartName) );
                String line = "";
                while (null != (line = in.readLine())) {
                    res.append(line);
                    res.append(System.getProperty( "line.separator" ) );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res.toString();
    }

    /**
     * 通过request获得request的content
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
	public static String getRequestContent( HttpServletRequest request )
    {
        String ret = "";

        try {
            String encoding = request.getCharacterEncoding();
            int len = request.getContentLength();
            if( len <= 0 )
            {
                return "";
            }
            byte[] bytes = new byte[len];
            int readByteLen = request.getInputStream().read( bytes );
            if( StringUtil.isNull( encoding ) )
            {
                ret = new String(bytes);
            }else {
                ret = new String( bytes, encoding );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


    //    ----------------------------------------------------------------------------------------------------
    public static String transferParameterUrl( String url )
    {
        if( null == url )
        {
            return  "";
        }

        String newUrl = url.replaceAll("%", "%25").replaceAll( "\\+", "%2B").replaceAll( " ", "%20" ).replaceAll( "/", "%2F").replaceAll( "\\?", "%3F").replaceAll( "#", "%23" ).replaceAll( "&", "%26").replaceAll( "=", "3D" );

        return newUrl;
    }


    public static String transferParameterUrlSecond( String url )
    {
        if( null == url )
        {
            return  "";
        }

        String newUrl = url.replaceAll( "%", "%25" ).replaceAll( "\\+", "%2B").replaceAll( " ", "%20" ).replaceAll( "/", "%2F").replaceAll( "\\?", "%3F").replaceAll( "#", "%23" ).replaceAll( "&", "%26").replaceAll( "=", "3D" ).replaceAll("%","%25" );

        return newUrl;
    }


    public static Map<String, String> parseUrlParameter( String url )
    {
        Map<String, String> paramterMap = new HashMap<String, String>();

        if( StringUtil.isNull( url ) )
        {
            return paramterMap;
        }

        String search = url.substring( url.indexOf("?") + 1 ) ;
        if( StringUtil.isNull( search ) )
        {
            return paramterMap;
        }
        search = search.trim();

        String[] parameterPairs = search.split( "&" );

        for( String parameterPair : parameterPairs )
        {
            String[] keyAndValue = StringUtil.split( parameterPair, "=" );

            if( keyAndValue == null || keyAndValue.length != 2 )
            {
                continue;
            }

            if( StringUtil.isNull( keyAndValue[0] ) )
            {
                continue;
            }
            if( StringUtil.isNull( keyAndValue[1] ) )
            {
                keyAndValue[1] = keyAndValue[1].trim();
            }

            paramterMap.put( keyAndValue[0].trim(), keyAndValue[1] );

        }

        return paramterMap;
    }

    public static Map<String, String> parseUrlParameterWithDecodingGBK( String url )
    {
        return parseUrlParameterWithDecoding(url, "GBK");
    }
    public static Map<String, String> parseUrlParameterWithDecoding( String url, String encoding )
    {
        Map<String, String> paramterMap = new HashMap<String, String>();

        if( StringUtil.isNull( url ) )
        {
            return paramterMap;
        }
        if( StringUtil.isNull( encoding ) )
        {
            encoding = "UTF-8";
        }

        String search = url.substring( url.indexOf("?") + 1 ) ;
        if( StringUtil.isNull( search ) )
        {
            return paramterMap;
        }
        search = search.trim();

        String[] parameterPairs = search.split( "&" );

        try{
            for( String parameterPair : parameterPairs )
            {
                String[] keyAndValue = StringUtil.split( parameterPair, "=" );

                if( keyAndValue == null || keyAndValue.length != 2 )
                {
                    continue;
                }

                if( StringUtil.isNull( keyAndValue[0] ) )
                {
                    continue;
                }
                if( StringUtil.isNull( keyAndValue[1] ) )
                {
                    keyAndValue[1] = keyAndValue[1].trim();
                }

                paramterMap.put( keyAndValue[0].trim(), URLDecoder.decode(keyAndValue[1], encoding) );

            }
        }catch ( Exception e ){
            e.printStackTrace();
        }

        return paramterMap;

    }


}
