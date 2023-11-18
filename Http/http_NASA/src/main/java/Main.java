import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;

public class Main {

    public static final String NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=sRhEH5uhonSltT35km1VakGReoJX4gWf10FqM5ys";
    public static String urlFromNASA;
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(30000)
                .setRedirectsEnabled(false)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();


        HttpGet request = new HttpGet(NASA_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            List<NASAAnswer> nasaAnswers = mapper.readValue(response.getEntity().getContent(), new TypeReference<>(){});
            urlFromNASA = nasaAnswers.get(0).getUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] urlSplit = urlFromNASA.split("/");
        HttpGet request1 = new HttpGet(urlFromNASA);
        try(FileOutputStream out = new FileOutputStream(urlSplit[urlSplit.length-1])) {
            CloseableHttpResponse response1 = httpClient.execute(request1);
            response1.getEntity().writeTo(out);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
