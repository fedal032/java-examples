import com.sun.applet2.AppletParameters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class A
{
    public static void f()
    {
        System.out.println("sss");
    }
}

enum SubsystemType
{
    ACH,
    RTGS
}

enum ConnectionType
{
    GATEWAY,
    GATEWAY_INFO,
}

public class CommonTest
{
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class MasterOrder {
        String code;
        String board;
        String issueType;
    }

    @Test
    void check() {
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> gwRows = new HashMap<>();
        gwRows.put("l", true);
        gwRows.put("foo", "bar");
        data.put("params", gwRows);
        Map<String, String> params = (Map<String, String>) data.get("params");
        Boolean l = Boolean.valueOf(params.get("l"));
        System.out.println(l);


    }


    @Test
    void workWithMaps()
    {
        Map<SubsystemType, Map<String, ConnectionType>> data = getAllUsersConfig();

        Set<String> users = data.values().stream().flatMap(value -> value.keySet().stream()).collect(Collectors.toSet());
        System.out.println(users);
        //----------------------------
        Map<SubsystemType, Map<String, ConnectionType>> allUsersConfig = data;
        List<CentralSystemUser> centralSystemUsers = getCentralSystemUsers();
        //--------------------
        //Map<String /*serviceBurau*/, SetCentralSystemUser> centralSystemUserMap =
        final Map<String /*serviceBurau*/, List<CentralSystemUser>> centralSystemUserMap = centralSystemUsers.stream()
                .collect(Collectors.groupingBy(CentralSystemUser::getServiceBureau));
        //--------------------
        Map<SubsystemType, Map<ConnectionType, List<SelectItem>>> senderUserItemsMap = new HashMap<>();

        for (SubsystemType subsystemType : allUsersConfig.keySet())
        {
            Map<ConnectionType, List<SelectItem>> connectionTypeListMap = senderUserItemsMap.computeIfAbsent(subsystemType, i -> new HashMap<>());
            Map<String /*userName == serviceBurau*/, ConnectionType> userConnectionType = allUsersConfig.get(subsystemType);

            List<SelectItem> senderUserItems = new ArrayList<>();
            senderUserItems.add(new SelectItem("", ""));
            List<SelectItem> infoSenderUserItems = new ArrayList<>();
            infoSenderUserItems.add(new SelectItem("", ""));

            for (String userName : userConnectionType.keySet())
            {
                List<CentralSystemUser> centralSystemUsersList = centralSystemUserMap.get(userName);
                for (CentralSystemUser csUser : centralSystemUsersList)
                {
                    if (!StringUtils.isEmpty(csUser.getServiceBureau()) && userConnectionType.get(csUser.getServiceBureau()) == ConnectionType.GATEWAY_INFO)
                        infoSenderUserItems.add(new SelectItem(csUser.getCode(), codeValueLabel(csUser.getCode(), csUser.getName())));
                    else
                        senderUserItems.add(new SelectItem(csUser.getCode(), codeValueLabel(csUser.getCode(), csUser.getName())));
                }
            }

            connectionTypeListMap.put(ConnectionType.GATEWAY, senderUserItems);
            connectionTypeListMap.put(ConnectionType.GATEWAY_INFO, infoSenderUserItems);

            senderUserItemsMap.put(subsystemType, connectionTypeListMap);
        }

        System.out.println(senderUserItemsMap);

    }

    private String codeValueLabel(String code, String name)
    {
        boolean isBlankCode = StringUtils.isBlank(code);
        boolean isBlankValue = StringUtils.isBlank(name);

        if (isBlankCode && isBlankValue)
            return StringUtils.EMPTY;

        if (isBlankCode)
            return name;

        if (isBlankValue)
            return code;

        return code + name;
    }

    private Map<SubsystemType, Map<String,ConnectionType>> getAllUsersConfig()
    {
        return getConfigs().stream()
                .collect(
                        Collectors.groupingBy(GwConnectionConfig::getSubsystem,
                                Collectors.toMap(GwConnectionConfig::getUsername, GwConnectionConfig::getType))
                );
    }

    private List<GwConnectionConfig> getConfigs()
    {
        List<GwConnectionConfig> configs = new ArrayList<>();
        configs.add(new GwConnectionConfig(SubsystemType.ACH, ConnectionType.GATEWAY, "userAchGw"));
        configs.add(new GwConnectionConfig(SubsystemType.ACH, ConnectionType.GATEWAY_INFO, "userAchGwInfo"));
        configs.add(new GwConnectionConfig(SubsystemType.RTGS, ConnectionType.GATEWAY, "userRtgsGw"));
        configs.add(new GwConnectionConfig(SubsystemType.RTGS, ConnectionType.GATEWAY_INFO, "userRtgsGwInfo"));
        return configs;
    }

    private List<CentralSystemUser> getCentralSystemUsers()
    {
        List<CentralSystemUser> centralSystemUsers = new ArrayList<>();
        centralSystemUsers.add(new CentralSystemUser("userAchX1", "userAch1", "userAchGw"));
        centralSystemUsers.add(new CentralSystemUser("userAchX2", "userAch2", "userAchGw"));
        centralSystemUsers.add(new CentralSystemUser("userAchI", "userAchI", "userAchGwInfo"));
        centralSystemUsers.add(new CentralSystemUser("userRtgsX", "userRtgsX", "userRtgsGw"));
        centralSystemUsers.add(new CentralSystemUser("userRtgsI", "userRtgsI", "userRtgsGwInfo"));
        centralSystemUsers.add(new CentralSystemUser("userAdap", "userAdap", ""));

        return centralSystemUsers;
    }


    @Test
    void testSplitArray()
    {
        List<MasterOrder> masterOrders = new ArrayList<>();
        masterOrders.add(new MasterOrder("mo1", "board1", null));
        masterOrders.add(new MasterOrder(null, "board1", "SH"));
        masterOrders.add(new MasterOrder("mo2", "board2", null));
        masterOrders.add(new MasterOrder("mo3", "board3", null));

        Map<Boolean, List<MasterOrder>> collect = masterOrders.stream()
                .collect(Collectors.partitioningBy(i -> i.getCode() == null));

        System.out.println(collect);

    }


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

    static class Security
    {
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
    void tryStreamApi() throws Exception
    {
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

    public static class HttpRequest
    {
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

    public static class HttpResponse
    {
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
    void tt()
    {
        A a = new A();
        a = null;
        a.f();
    }

    @Test
    void useOptionalTest()
    {
        HttpRequest httpRequest = new HttpRequest();
        HttpResponse httpResponse = new HttpResponse();

        Optional<String> authHeader = retrieveAuthorizationHeader(httpRequest, httpResponse);
        if (!authHeader.isPresent())
        {
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

    @Getter
    @Setter
    @AllArgsConstructor @NoArgsConstructor
    @ToString
    private class GwConnectionConfig
    {
        private SubsystemType subsystem;
        private ConnectionType type;
        private String username;
    }

    @Getter
    @Setter
    @AllArgsConstructor @NoArgsConstructor
    @ToString
    private class SelectItem
    {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor @NoArgsConstructor
    @ToString
    private class CentralSystemUser
    {
        private String code;
        private String name;
        private String serviceBureau;
    }
}
