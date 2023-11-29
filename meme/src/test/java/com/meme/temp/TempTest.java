package com.meme.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.meme.mongo.entity.B2bActivityOrg;
import com.meme.pojo.TestCompany;
import com.meme.pojo.TestUser;
import com.meme.retry.model.RetryInfo;
import com.meme.rocketmq.RocketMQUtil;
import com.meme.util.DateUtils;
import com.meme.util.FTPUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
class TempTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSplit() {
        String mobileMediaLink = "https://static.gtech.asia/dev/200001/20211227/96D6601226FEE2FBBD05FF0D77D0251D.jpeg,https://static.gtech.asia/dev/200001/20211227/2F5ACB88575C12D4E22045ECC1EF5A2A.png,https://static.gtech.asia/dev/200001/20211227/C35D9F07E76C7188A3D754618C1DE08B.jpeg";
        if (mobileMediaLink != null) {
            String[] split = mobileMediaLink.split(",");
            if (split.length > 1) {
                mobileMediaLink = split[0];
            }
        }
        log.info("mobileMediaLink: {}", mobileMediaLink);
    }

    @Test
    void testStr() {
        String str = "abc";
        log.info("str: {}", str.substring(0, str.length() - 1));
        log.info("str: {}", str.substring(str.length() - 1));
    }

    @Test
    void testException() throws Exception {
        try {
            throw new Exception("new thrown exception");

        } catch (Exception e) {
//            log.error("{}", new ObjectMapper().writeValueAsString(e));
            throw new Exception(e);
        }
    }

    @Test
    void testSdf() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.SIMPLIFIED_CHINESE);
        Date updateTime;
        try {
            updateTime = sdf.parse("2022-07-07T14:35:16.771+08:00");
            log.info("date: {}", updateTime);
        } catch (Exception ignored) {
        }
    }

    @Test
    void testList() {
        List<String> list1 = Arrays.asList("0", "2");
        List<String> list2 = Arrays.asList("0", "2");
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("0");
        list3.add("2");
        System.out.println(list1.equals(list3));
    }

    @Test
    void testOptional() {
        Integer i = null;
        Integer o = Optional.ofNullable(i).orElse(1);
        System.out.println(o);
    }

    @Test
    void testFTP() {
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("192.168.80.128", 21);
            ftpClient.login("sumi", "123456");

            ftpClient.changeWorkingDirectory("Downloads/dir1");
            ftpClient.enterLocalPassiveMode();

            InputStream inputStream = ftpClient.retrieveFileStream("b.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                log.info("from remote: {}", line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFTPUrl() {
        String s = "ftp://192.168.80.128/Downloads/a.txt";
        String s1 = s.substring(6);
        log.info(s1);
        String s2 = s1.substring(0, s1.indexOf("/"));
        log.info(s2);
        String[] split = s2.split(":");
        String host = split[0];
        int port;
        if (split.length < 2) {
            port = 21;
        } else {
            port = Integer.parseInt(split[1]);
        }
        log.info("host: {}", host);
        log.info("port: {}", port);

        String directory = null;
        if (s1.indexOf("/") < s1.lastIndexOf("/")) {
            directory = s1.substring(s1.indexOf("/") + 1, s1.lastIndexOf("/"));
        }

        log.info("directory: {}", directory);

        String fileName = s1.substring(s1.lastIndexOf("/") + 1);
        log.info("fileName: {}", fileName);

    }

    @Test
    void testFTP2() throws IOException {
        String result = FTPUtil.read("ftp://192.168.80.128:21/Downloads/dir1/b.txt", "sumi", "123456");
        System.out.println(result);
    }

    @Test
    void testDate() {
        log.info("dateFormat: {}", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        log.info("dateFormat: {}", LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
        log.info("dateFormat: {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

        Date date = new Date();
        log.info("date: {}", date);
        log.info("date: {}", new Date(date.getTime() - 60 * 1000));

        log.info("local: {}", ZonedDateTime.now());
        log.info("local: {}", DateUtils.format(ZonedDateTime.now(), DateUtils.PATTERN_28));
        log.info("utc: {}", ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")));
        log.info("utc: {}", DateUtils.format(ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")), DateUtils.PATTERN_28));

    }

    @Test
    void testCode() {
        String str = "Aaaaa::bbb::202201211804@{}";

        String code1 = str.substring(0, str.indexOf("@"));
        String[] split = code1.split("::");
        log.info("beanName: {}", split[0]);
        log.info("method: {}", split[1]);
        log.info("time: {}", split[2]);

        String json = str.substring(str.indexOf("@") + 1);
        log.info("json: {}", json);
        Integer a = null;
        int total = 1;
    }

    @Test
    void testFastJson() {
        JSONArray jsonArray = JSON.parseArray("{\"code\":\"101302301\",\"message\":\"The order information does not exist or the order is invalid\",\"success\":false}");
        log.info("{}", jsonArray);

    }

    @Test
    void testDate2() {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.SIMPLIFIED_CHINESE);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        LocalDateTime dateTime = LocalDateTime.parse("2022-07-07T14:35:16.771+08:00", dateTimeFormatter);
//        log.info("dateTime: {}", dateTime);
        log.info("{}", OffsetDateTime.parse("2024-01-18T07:59:59+08:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        log.info("{}", DateUtils.formatOffset("2024-01-18T07:59:59+08:00", DateUtils.PATTERN_28));


//        Date date = new Date();
//        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


    }

    @Test
    void testDate3() {
        System.out.println(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS", Locale.SIMPLIFIED_CHINESE))
        );
    }

    @Test
    void testDate4() {
        LocalDateTime time = LocalDateTime.parse("20240117000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        log.info("locale: {}", Locale.getDefault());
        log.info("{}", time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
    }

    @Test
    void testOptional1() {
        Optional<Object> optional1 = Optional.empty();
        Optional<Object> optional2 = Optional.ofNullable(null);
        System.out.println(optional1);
        System.out.println(optional2);
    }

    @Test
    void testBeanUtil() {
        String userCode = "aaaa";
        String userName = "发生了零零";
        boolean logicDelete = false;

        HashMap<String, Object> map = new HashMap<>();
        map.put("updateUserCode", userCode);
        map.put("updateUserName", userName);
        map.put("updateTime", LocalDateTime.now());

        B2bActivityOrg b2bActivityOrg = new B2bActivityOrg();
        b2bActivityOrg.setOrgCode("oooooooooooooo");
        b2bActivityOrg.setUpdateUserCode("oooooo");
        try {
            BeanUtils.populate(b2bActivityOrg, map);
            System.out.println(b2bActivityOrg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Test
    void test() throws JsonProcessingException, InterruptedException {
        Clock clock = Clock.systemDefaultZone();
        log.info("instant 1: {}", LocalDateTime.now(clock));
        TimeUnit.SECONDS.sleep(10);
        log.info("instant 2: {}", LocalDateTime.now(clock));

    }

    @Test
    void testTime() {
        LocalDateTime first1st = LocalDateTime.of(2022, 1, 1, 0, 0, 0, 0);
        LocalDateTime now = LocalDateTime.of(2022, 2, 28, 23, 59, 59, 999_999_999);
        long monthBetween = ChronoUnit.MONTHS.between(first1st, now);
        log.info("monthBetween: {}", monthBetween);
    }

    @Test
    void testDecimal() {

        BigDecimal b = new BigDecimal(19980070);
        BigDecimal divide = b.divide(new BigDecimal(100), 2, RoundingMode.UNNECESSARY);
        log.info("divide: {}", divide.toPlainString());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(divide);
        log.info("format: {}", format);
    }

    @Test
    void collator() {

        List<String> list = Arrays.asList("一", "二", "三", "四", "五", "六", "七");
        list.sort((o1, o2) -> {
            Collator collator = Collator.getInstance(Locale.SIMPLIFIED_CHINESE);
            return collator.compare(o1, o2);
        });

        System.out.println(list);

    }

    @Test
    void random() {
        System.out.println(RandomStringUtils.random(12, "~!@#$%^&*0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"));
        log.info(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(RandomStringUtils.randomAlphanumeric(43));
    }

    @Test
    void testLList() {

        List<List<Object>> listlist = new ArrayList<>();
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        listlist.add(list1);
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(new Object());
        list2.add(new Object());
        list2.add(new Object());
        listlist.add(list2);
        listlist.add(null);

        for (List<Object> list : listlist) {
            Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                iterator.remove();
            }
        }

        System.out.println(JSON.toJSONString(listlist));
    }

    @Test
    void testMap() {
        Map<String, List<TestCompany>> map = List.of(
                TestCompany.builder().code("0").build(),
                TestCompany.builder().code("0").build(),
                TestCompany.builder().code("1").build(),
                TestCompany.builder().code("1").build(),
                TestCompany.builder().code("1").build(),
                TestCompany.builder().code("1").build(),
                TestCompany.builder().build()
        ).stream().collect(Collectors.groupingBy(TestCompany::getCode));

        System.out.println(map);

    }

    @Test
    void testUUID() {
        log.info("UUID: {}", UUID.randomUUID().toString().replace("-", ""));
    }

    @Test
    void toMap() {
        List<String> strings = Arrays.asList("aa123", "aa456", "bb123", "bb234");
    }

    @Test
    void hash() {
        printHash("T00280083LS");
        printHash("1IB4344287");
        printHash("T00001379BR");
        printHash("T00002402BR");
    }

    static void printHash(String code) {
        System.out.printf("%s \t %s%n", code, hash(code));
    }

    static int hash(String code) {
        return Math.abs(code.hashCode()) % 64;
    }

    @Test
    void sku() {
        String skuCode = "120EV2193Q";

        if (StringUtils.startsWith(skuCode, "120E")) {
            skuCode = skuCode.replaceFirst("120E", "100E");
        }

        System.out.println(skuCode);
    }

    @Test
    void pringSql() {
        for (int i = 0; i < 64; i++) {
            System.out.println("select * from t_scan_store_record_info_" + i + " where CREATE_TIME>'2022-05-01' and CREATE_TIME<'2022-08-23';");
        }
    }

    @Test
    void printRandom() {
        for (int i = 0; i < 36; i++) {
            System.out.print((int) (Math.random() * 8));
            System.out.print("\t");
            System.out.println((int) (Math.random() * 256));
        }
    }

    @Test
    void dateTest() {
        System.out.println(System.currentTimeMillis());
        long timestamp = LocalDateTime.parse("2022-01-01T00:00:00").toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(timestamp);

        log.info("date: {}", LocalDateTime.ofInstant(Instant.ofEpochSecond(1706930114), ZoneId.systemDefault()));
    }

    @Test
    void timestamp() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void subString() {

        String str = "111_11231443";
        System.out.println(str.substring(str.indexOf("_") + 1));

    }

    @Test
    void bigDecimal() throws JsonProcessingException {
        String str = "[100.10, 100.200, 10.23, 1000]";
        List<BigDecimal> bigDecimals = objectMapper.readValue(str, new TypeReference<List<BigDecimal>>() {
        });
        System.out.println(bigDecimals.get(0).stripTrailingZeros().equals(new BigDecimal("100.1")));

        System.out.println(bigDecimals);
    }

    @Test
    void testStringBuilder() {
        StringBuilder sb = new StringBuilder("tail");
        sb.insert(0, "head->");
        System.out.println(sb);

    }

    @Test
    void testBigDecimal() {
        BigDecimal bd1 = new BigDecimal("1600.00");
        BigDecimal bd2 = new BigDecimal("1600.0000");
        System.out.println(bd1.compareTo(bd2));
        System.out.println(bd1.stripTrailingZeros().equals(bd2.stripTrailingZeros()));
    }

    @Test
    void testSubList() {
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println(list.subList(0, Math.min(5, list.size())));
    }

    @Test
    void testCollectionUtil() {
        log.info("containsInstance: {}", CollectionUtils.containsAny(Arrays.asList("aaa"), "aaa"));
    }

    @Test
    void testSubString() {
        String imgStr = "https://p16-oec-va.ibyteimg.com/tos-maliva-i-o3syd03w52-us/62935ff9745942aeacec1b8674bf37fb~tplv-o3syd03w52-origin-jpeg.jpeg?id=tos-maliva-i-o3syd03w52-us/62935ff9745942aeacec1b8674bf37fb";
        String id = StringUtils.substringAfterLast(imgStr, "id=");
        log.info("id: {}", id);
    }

    @Test
    void testInc() {
        int a = 0;
        for (int i = 0; i < 10; i++) {
            a = a++;
        }
        System.out.println(a);
    }

    @Test
    void testCatchException() {
        try {
            throw new RuntimeException("aaaa");
        } catch (RuntimeException e) {
            log.info("runtime");
        } catch (Exception e) {
            log.info("exception");
        }
    }

    @Test
    void testInvokeFunction() throws InterruptedException {
        String result = invokeFunction(3, TimeUnit.SECONDS, 1, "aaaaa", "bbbb",
                (a, b) -> {
                    System.out.println(a);
                    System.out.println(b);
                    return a;
                },
                a -> StringUtils.equals(a, "aaaaa")
        );

        log.info("result: {}", result);
    }

    private <T, U, R> R invokeFunction(int invokeTimes, TimeUnit intervalTimeUnit, long interval, T arg1, U arg2, BiFunction<T, U, R> function, Predicate<R> predicate) {

        R r = null;
        for (int i = 0; i < invokeTimes; i++) {
            r = function.apply(arg1, arg2);
            if (predicate.test(r)) {
                return r;
            } else {
                try {
                    intervalTimeUnit.sleep(interval);
                } catch (InterruptedException e) {
                    log.error(e.getMessage(), e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        return r;
    }

    @Test
    void testBigDecimalSerialize() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("price", new BigDecimal("11.11"));
        log.info("map: {}", objectMapper.writeValueAsString(map));
    }


    @Test
    void testThrow() {

        exexe();
    }

    @SneakyThrows
    private void exexe() {
        throw new Exception("exexexexexexexex");
    }

    @Test
    void testBase64() {
        log.info("baseStr: {}", encodeBase64String("product11221843product11221843"));
        log.info("baseStr: {}", encodeBase64String("newbrand-product11221843product11221843"));
        log.info("baseStr: {}", encodeBase64String("update Description"));
        log.info("baseStr: {}", encodeBase64String("some unique selling point"));

    }

    private String encodeBase64String(String str) {
        return Base64.encodeBase64String(StringUtils.getBytes(str, StandardCharsets.UTF_8));
    }

    @Test
    void testEpoch() {
        log.info("epoch: {}", LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(7)));
        log.info("currentTimeMillis: {}", System.currentTimeMillis() / 1000);
    }

    @Test
    void getTimeZone() {
        log.info("default timeZone: {}", TimeZone.getDefault());
        log.info("default ZoneOffset: {}", ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()));
        log.info("now: {}", OffsetDateTime.now());
        System.out.println(OffsetDateTime.now().toEpochSecond());
    }

    @Test
    void OffsetDateTime() {
        LocalDateTime localDateTime = DateUtils.parseLocalDateTime("20221208172556999", DateUtils.PATTERN_YYYYMMDDHHMMSSSSS);
        System.out.println(localDateTime);
        System.out.println(localDateTime.toEpochSecond(DateUtils.getDefaultZoneOffset()));
    }

    @Test
    void testDate1() throws JsonProcessingException {
        Map<String, Date> map = objectMapper.readValue("""
                {
                    "date1": "2022-12-09T16:44:56+07:00",
                    "date2": "1690363015000"
                }
                """, new TypeReference<>() {
        });
        System.out.println(map);
    }

    @Test
    void testBool() {
        System.out.println(BooleanUtils.isNotTrue(null));

    }

    @Test
    void testCreateTempPath() {
        try {
            Path luceneTemp = Files.createTempDirectory("lucene_temp");
            log.info(luceneTemp.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    void testCreatePath() throws IOException {

        Path dir = Files.createDirectories(Path.of("D:\\\\temp\\lucene"));
        log.info(dir.toString());
//        HttpURLConnection
    }

    @Test
    void testSyncProducer() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {

        String producerGroup = "syncProducer01111337";
        String namesrvAddr = "127.0.0.1:9876";

        DefaultMQProducer producer = RocketMQUtil.getDefaultProducer(producerGroup, namesrvAddr);
        producer.start();

        String topic = "topic_0111";
        String tag = "sync";
        String key = "key_01111339";

        Message msg = new Message();
        msg.setTopic(topic);
        msg.setTags(tag);
        msg.setKeys(key);
        msg.setBody("bodySync01111339body".getBytes(StandardCharsets.UTF_8));

        SendResult sendResult = producer.send(msg);

        log.info("sendResult: {}", JSON.toJSONString(sendResult));

        producer.shutdown();
    }

    @Test
    void testAsyncProducer() throws MQClientException, RemotingException, InterruptedException {
        String producerGroup = "asyncProducer01111501";
        String namesrvAddr = "127.0.0.1:9876";

        DefaultMQProducer producer = RocketMQUtil.getDefaultProducer(producerGroup, namesrvAddr);
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.start();

        String topic = "topic_0111";
        String tag = "async";
        String key = "key_01111502";

        Message msg = new Message();
        msg.setTopic(topic);
        msg.setTags(tag);
        msg.setKeys(key);
        msg.setBody("bodyaSync0111503body".getBytes(StandardCharsets.UTF_8));

        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("sendResult: {}", JSON.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable e) {
                log.error(e.getMessage(), e);
            }
        });

        TimeUnit.SECONDS.sleep(30);
        producer.shutdown();
    }

    @Test
    void testSort() {
        List<Integer> list = Arrays.asList(3, 2, 6, 3, 0, 1, 7, 3, null);
        Collections.sort(list);
        log.info("list: {}", list);
    }

    @Test
    void testTimeUnit() {
        log.info("unit: {}", TimeUnit.SECONDS);
    }

    @Test
    void testString() {
        String a = "aaaaaa:1111:389";
        System.out.println(a.substring(a.lastIndexOf(":") + 1));
        log.info("equals: {}", StringUtils.equals(null, null));
    }

    @Test
    void testObjectMapper() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("8", "b");
        map.put("3", "c");

        System.out.println(
                map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList())
        );
        ArrayList<Object> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("bbb");
        long count = list.stream().distinct().count();
        System.out.println(count);
    }


    @Test
    void testSerialize() throws JsonProcessingException {
        log.info("{}", System.currentTimeMillis());
    }

    @Test
    void testList1() {
        int i = 0;
        log.info("List: {}", Collections.singletonList(i++));
        log.info("List: {}", Collections.singletonList(i++));
    }

    @Test
    void testURI() {
        URI uri = URI.create("https://www.baidu.com");
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("https://www.baidu.com?b=2").queryParam("a", List.of(1, 2, 3)).build();
        log.info("{}", uriComponents);
//        HttpClientErrorException
    }


    @Test
    void testJson() throws JsonProcessingException {
        String jsonStr = "{\n" +
                "    \"421\": \"string value\",\n" +
                "    \"533\": [\n" +
                "      4,\n" +
                "      5\n" +
                "    ],\n" +
                "    \"567\": 123,\n" +
                "    \"721\": [\n" +
                "      \"string1\",\n" +
                "      \"string2\"\n" +
                "    ],\n" +
                "    \"854\": null\n" +
                "  }";

        Map<String, Object> map = objectMapper.readValue(jsonStr, new TypeReference<>() {
        });
        log.info("map: {}", map);

        log.info("{}", LocalDateTime.now());
    }

    @Test
    void testRetryInfo() {
        RetryInfo retryInfo = new RetryInfo().toBuilder()
                .stepList(List.of(1L, 2L, 4L, 8L, 16L, 30L, 60L))
                .timeoutSpan(5L)
                .timeoutSpanUnit(TimeUnit.MINUTES)
                .build();

        retryInfo.getNextRetryTimeMillis();

        try {
            do {
                if (retryInfo.needRetryNow()) {
                    log.info("retry.");
                    retryInfo.fail();
                } else if (retryInfo.needFinalizeNow()) {
                    log.info("finalize.");
                    retryInfo.finalized();
                }
                TimeUnit.SECONDS.sleep(1L);

            } while (!retryInfo.isFinalized());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testList2() {
        log.info("count: {}", Stream.of(1, 1, 2, 3).distinct().count());
    }


    @Test
    void rabbitmqSend() {
//        String QUEUE_NAME = "hello";
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//        try (connectionFactory.newConnection())
    }

    @Test
    void testUnicode() {
        String address3 = "กรุงเทพมหานคร/ Bangkok";
        String provinceName = StringUtils.trimToEmpty(address3);
        if (StringUtils.isNotEmpty(provinceName)
                && Character.UnicodeScript.THAI.equals(Character.UnicodeScript.of(provinceName.codePointAt(0)))) {

            provinceName = StringUtils.trimToEmpty(StringUtils.substringAfter(provinceName, "/ "));
        }

        log.info("{}", provinceName);
    }

    @Test
    void testUri() {

        log.info(
                UriComponentsBuilder.fromUriString("https://p16-oec-va.ibyteimg.com/tos-maliva-i-o3syd03w52-us/1d3ff6be21534a65b216656bd16e4c99~tplv-o3syd03w52-origin-jpeg.jpeg?from=1432613627").queryParam("id", "tos-maliva-i-o3syd03w52-us/1d3ff6be21534a65b216656bd16e4c99").build().toUriString()
        );
    }

    @Test
    void testDate01() {

        // 获取当天的零点时间（UTC+8）
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusHours(24);

        // 计算前一天的零点时间（UTC+8）
        LocalDateTime yesterday = today.minusHours(24);

        // 将时间转换为时间戳，单位秒，时区为东八区
        long startTime = yesterday.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endTime = today.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        log.info("startTime: {}", new Date(startTime));
        log.info("endTime: {}", new Date(endTime));

        ZoneOffset defaultOffset = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
        log.info("offset: {}", ZoneOffset.of("+8"));
        log.info("offset1: {}", defaultOffset);
        long startTime1 = LocalDate.now().atTime(LocalTime.MIN)
                .toInstant(defaultOffset).toEpochMilli();
        long endTime1 = LocalDate.now().atTime(LocalTime.MIN).plusHours(24)
                .toInstant(defaultOffset).toEpochMilli();
        log.info("startTime1: {}", new Date(startTime1));
        log.info("endTime1: {}", new Date(endTime1));
    }


    @Test
    void testList01() {
        List<LinkedList<String>> collect = Arrays.asList("aa", "bb", "cc").stream().map(str -> {
            LinkedList<String> list = new LinkedList<>();
            list.add(str + "-1");
            return list;
        }).collect(Collectors.toList());
        log.info("{}", collect);
    }

    @Test
    void testCollectionUtils() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<List<Integer>> partition = ListUtils.partition(list, 3);
        log.info("found: {}", partition.get(1));
        log.info("found: {}", partition.get(5));


    }

    @Test
    void testCurrency() {
        System.out.println(
                Currency.getInstance(Locale.CHINA).getCurrencyCode()
        );
        System.out.println(
                Arrays.toString(Locale.getISOCountries())

        );
        System.out.println(Locale.SIMPLIFIED_CHINESE.getDisplayCountry());
        System.out.println(Locale.SIMPLIFIED_CHINESE.getDisplayName(Locale.SIMPLIFIED_CHINESE));
        System.out.println(Locale.SIMPLIFIED_CHINESE.getDisplayCountry(Locale.SIMPLIFIED_CHINESE));
        System.out.println(Currency.getInstance(Locale.SIMPLIFIED_CHINESE).getCurrencyCode());
    }

    @Test
    void testXml() {
        String xmlString = """
                <xml>
                <Encrypt><![CDATA[XuKe5SYaIS0Geo55Yyd+v7J7YPwdGW7nGVLxDZ4HcX/6C1O9aXo3dHt3YjDkCdBr2E3TMlG7E6lw2RhReXZ0pgAsswOYNdVgNivbuiUBiTNnPx04EavcUK3eqwQjn7Qyde7m4mmVmdZj/1I8THELVrHkfOsxRLap9KlxZY6CDwphnYDHvkDNelA1bRt74hYwPqGFTYtwhSZ3N63Tg5O8kVvCH6YpP4A/BfQaD/Yma1PhnfML54+6olBjiRAu8ylBCEsc1N2hsIVqWfGLid8o1Es2zi1wOy447RlSeLWPwwbB8Jgeha2lKP9kajUZ/7aKhOTWVLCPAv5CWY5Bnf1ivcN8vOad+g+RVeHRfL8ZtUlOmKnXhpIsNtReXlKISF2FEnynuPJZieTuCtp0kJxDXjhMs94KxZA2pC26rU8N46QxaSyl+fdveMSJWnpfGxvT8mmsbrryPwr7K57DrzUMdgxN31ZWYI2jOi0YgVcolzBCvJ1T5cdPPe+NJR2yfP8EZV+M4ZWJQCa0zCQWOoTSm5erdTZ5TBa90mdK1MiYF3SrD7ivz/byXueX+YcnDDra2zAvIbf3eCh0ASwgeUZspA+QqDSSnww1Dq9tV428FaNR3UEcNRM2+Eg0YDh5fwWj]]></Encrypt>
                <MsgSignature><![CDATA[7615e72f8ded2f1eae02e3541ab95856cf89858b]]></MsgSignature>
                <TimeStamp>1409304348</TimeStamp>
                <Nonce><![CDATA[xxxxxx]]></Nonce>
                </xml>
                """;

    }

    @Test
    void testStream() {
        List<Integer> list = Stream.of(List.of(1, 2, 3, 4), List.of(5, 6, 7, 8))
                .flatMap(Collection::stream)
                .toList();

        System.out.println(list);

        Map<Integer, List<Integer>> map1 = new HashMap<>();
        map1.put(1, null);
        System.out.println(

                List.of(map1, Map.of(1, List.of(1, 2)), Map.of(1, List.of(3, 2, 2, 4)))
                        .stream()
                        .map(map -> (List) map.get(1))
                        .filter(e -> e != null)
                        .flatMap(Collection::stream).distinct().collect(Collectors.toList())

        );
    }

    @Test
    void testSpel() {
        SpelExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext templateParserContext = new TemplateParserContext();

        StandardEnvironment standardEnvironment = new StandardEnvironment();
        standardEnvironment.setActiveProfiles("dev");
        Expression expression = parser.parseExpression(standardEnvironment.resolveRequiredPlaceholders("#{${aaaaaaa:{'EC':'Default','ZZ':'EXCESS'}}}"), templateParserContext);

//        Map map = expression.getValue(Map.class);
        Object value = expression.getValue();
//        Object value = expression.getValue("");
        System.out.println(value.getClass());
        System.out.println(value);
//        System.out.println(map.keySet());
//        ConfigurablePropertyResolver
    }

    @Test
    void testString1() {
        String param = "3028345-003-18";
        String result = null;
        String[] split = StringUtils.split(param, "-");
        if (split != null && split.length > 2) {
            result = split[0] + "-" + split[1];
        }

        System.out.println(result);

    }

    @Test
    void testString2() {
        String value = "#{${ua.inventory.warehouse.mapping:{'EC':'Default','ZZ':'EXCESS'}}}";

        String v1 =
                StringUtils.substring(value, StringUtils.indexOf(value, "#{") + 2, StringUtils.lastIndexOf(value, "}"));
        String v2 = StringUtils.substring(v1, StringUtils.indexOf(v1, "${") + 2, StringUtils.lastIndexOf(v1, "}"));
        System.out.println(v1);
    }

    @Test
    void testStrip() {
        String result = strip("{{aaa}}", "{", "}");
        System.out.println(result);
    }

    private String strip(String str, String start, String end) {
        int startIndex = StringUtils.indexOf(str, start);
        if (startIndex < 0) {
            startIndex = 0;
        } else {
            startIndex = startIndex + StringUtils.length(start);
        }
        int endIndex = StringUtils.lastIndexOf(str, end);
        if (endIndex < 0) {
            endIndex = StringUtils.length(str);
        } else if (endIndex == 0 && "".equals(end)) {
            endIndex = StringUtils.length(str);
        }
        return StringUtils.substring(str, startIndex, endIndex);
    }

    @Test
    void testGson() {
        TestUser<TestCompany> user = new TestUser<>();
        user.setName("username");
        TestCompany testCompany = new TestCompany();
        testCompany.setName("companyname");
        testCompany.setAaaList(List.of("aaa", "bbb"));
        user.setData(testCompany);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
        Object o1 = gson.fromJson(json, new TypeToken<TestUser<TestCompany>>() {
        }.getType());
        System.out.println(o1);
        TestUser o2 = gson.fromJson(json, TestUser.class);
        System.out.println(o2);

        String str = "{\"nnnn\":\"companyname\",\"aaa_list\":[\"aaa\",\"bbb\"]}";
        System.out.println(gson.fromJson(str, TestCompany.class));
    }

    @Test
    void testMap1() {
        HashMap<String, TestCompany> map = new HashMap<>();

        computeMap(map);
        System.out.println(map);
        computeMap(map);
        System.out.println(map);

    }

    private void computeMap(Map<String, TestCompany> map) {

        map.compute("1", (k, v) -> {
            if (v == null) {
                v = new TestCompany();
                v.setName("v1");
            } else {
                v.setName("v2");
            }
            return v;
        });
    }

    @Test
    void testRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(5000);
//        HttpClient
//        clientHttpRequestFactory.setHttpClient();
//        clientHttpRequestFactory.setReadTimeout(20000);
//        restTemplate.setRequestFactory(clientHttpRequestFactory);
//        restTemplate.setRequestFactory();
    }

    @Test
    void testGetValue() {
        Object value = parseText("#{${wls.update-inventory.retry.error-code:{'-1', '45011', 'timeout'}}}");
        System.out.println(value);
    }

    private Object parseText(String text) {

        ParserContext parserContext = new TemplateParserContext();
        StandardEnvironment env = new StandardEnvironment();
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(env.resolveRequiredPlaceholders(text), parserContext);
        return expression.getValue();
    }

    @Test
    void testDate5() {
        String timeStr = "{\"startTime\": \"2023-03-04T00:21:21+07:00\", \"endTime\": \"2023-03-04T00:21:21+07:00\"}";
        Map<String, Date> map = JSON.parseObject(timeStr, new com.alibaba.fastjson.TypeReference<Map<String, Date>>() {
        });
        System.out.println(map);
        Date successPullTime = new Date();
        int createTimeSpan = 237500;
        int timeSpanSec = 86400;
        Date startDate = getDate(successPullTime, Calendar.SECOND, -createTimeSpan);
        Date endDate = getDate(startDate, Calendar.SECOND, timeSpanSec);

        System.out.println(startDate);
        System.out.println(endDate);
    }

    public Date getDate(Date designate, int type, int interval) {
        Calendar c = Calendar.getInstance();
        c.setTime(designate);
        c.add(type, interval);
        return c.getTime();
    }

    @Test
    void testLong() {
        System.out.println(Long.MAX_VALUE);
    }

    @Test
    void testInteger() {
        System.out.println(-Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    @Test
    void testStopWatch() throws InterruptedException {
        StopWatch stopWatch = StopWatch.createStarted();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(stopWatch.getTime());
        TimeUnit.SECONDS.sleep(4);
        System.out.println(stopWatch.getTime());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(stopWatch.getTime());
        stopWatch.stop();
    }
}

