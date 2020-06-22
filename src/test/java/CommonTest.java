import org.apache.commons.text.StringEscapeUtils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CommonTest
{
   @Test
    void testEscape()
    {
        String[] arr = {"<script>alert(1)</script>", "test"};
        print(escapeHtml(arr));
        print(escapeJson(arr));

    }

    private void print(String[] res)
    {
        for (int i = 0; i < res.length; i++)
        {
            System.out.println(i + " " + res[i]);
        }
    }

    private String[] escapeHtml(String[] text)
    {

        return Arrays.stream(text)
                .map(StringEscapeUtils::escapeHtml4)
                .toArray(String[]::new);

    }

    private String[] escapeJson(String[] text)
    {

        return Arrays.stream(text)
                .map(StringEscapeUtils::escapeJson)
                .toArray(String[]::new);
    }

    static class Security{
        private String type;
        private String ISIN;

        public Security()
        {
        }

        public Security(String type, String ISIN)
        {
            this.type = type;
            this.ISIN = ISIN;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getISIN()
        {
            return ISIN;
        }

        public void setISIN(String ISIN)
        {
            this.ISIN = ISIN;
        }
    }

    @Test
    void tryStreamApi() throws Exception {
        List<Security> securities = getSecurityList();
        securities = securities.stream().filter(SECURITIES_FILTER::test).collect(Collectors.toList());
        securities.forEach(security -> System.out.println(security.getType() + " " + security.getISIN()));

    }

    public static final Predicate<Security> SECURITIES_FILTER = security -> !"CUR".equals(security.getType());

    private List<Security> getSecurityList()
    {
        List<Security> securities = new ArrayList<>();
        securities.add(new Security("SHR", "1231321"));
        securities.add(new Security("BND", "7987987"));
        securities.add(new Security("CUR", "USD"));
        securities.add(new Security("BND", "0000000"));
        return securities;
    }

    public static class HttpRequest {
        private String data;
        private String header;

        public String getData()
        {
            return data;
        }

        public void setData(String data)
        {
            this.data = data;
        }

        public String getHeader()
        {
            return header;
        }

        public void setHeader(String header)
        {
            this.header = header;
        }
    }

    public static class HttpResponse {
        private String data;

        public String getData()
        {
            return data;
        }

        public void setData(String data)
        {
            this.data = data;
        }
    }

    @Test
    void useOptionalTest()
    {
        HttpRequest httpRequest = new HttpRequest();
        HttpResponse httpResponse = new HttpResponse();

        Optional<String> authHeader = retrieveAuthorizationHeader(httpRequest, httpResponse);
        if (!authHeader.isPresent()) {
            sendUnauthorized401(httpResponse);
            System.out.println("resp " + httpResponse.getData());
            return;
        }

    }

    private Optional<String> retrieveAuthorizationHeader(HttpRequest httpRequest, HttpResponse httpResponse)
    {
        Optional<String> authHeader = Optional.ofNullable(httpRequest.getHeader());
        if (!authHeader.isPresent())
        {
            System.out.println("Missing Authorization header. Sending 401 Unauthorized response");
        }
        return authHeader;
    }

    private void sendUnauthorized401(HttpResponse httpResponse)
    {
        httpResponse.setData("401 ERROR");
    }
}
