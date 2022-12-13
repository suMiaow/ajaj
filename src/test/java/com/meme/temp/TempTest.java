package com.meme.temp;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meme.mongo.entity.B2bActivityOrg;
import com.meme.mongo.entity.Noob;
import com.meme.util.DateUtils;
import com.meme.util.FTPUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.CalendarUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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
    void testException() throws JsonProcessingException {
        try {
            throw new Exception("new thrown exception");

        } catch (Exception e) {
            log.error("{}", new ObjectMapper().writeValueAsString(e));
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
        Object o = Optional.ofNullable(null).orElse(1);
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
        String str = "{\"name\":\"aaa\"}";
        Noob noob = JSON.parseObject(str, Noob.class);
        log.info(noob.toString());
    }

    @Test
    void testDate2() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.SIMPLIFIED_CHINESE);
        LocalDateTime dateTime = LocalDateTime.parse("2022-07-07T14:35:16.771+08:00", dateTimeFormatter);
        log.info("dateTime: {}", dateTime);

    }

    @Test
    void testDate3() {
        System.out.println(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS", Locale.SIMPLIFIED_CHINESE))
        );
        System.out.println("210616110550592");
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

        BigDecimal b = new BigDecimal("19.9876");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String format = decimalFormat.format(b);
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
        System.out.println(RandomStringUtils.randomAlphanumeric(16));
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
        int[] a = {1, 2, 3, 4, 5};
        int[] b = Arrays.stream(a).map(i -> i * 2).toArray();
        int[][] a2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] b2 = Arrays.stream(a2).flatMapToInt(Arrays::stream).toArray();

        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(b2));
    }

    @Test
    void testUUID() {
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
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
        Map<String, Date> map = objectMapper.readValue("{\"date\":\"2022-12-09T16:44:56+07:00\"}", new TypeReference<Map<String, Date>>() {
        });
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        System.out.println(map);

        System.out.println(new Date());
        System.out.println(DateUtils.getLocalDateTime(new Date()));
    }

    @Test
    void testBool() {
        System.out.println(BooleanUtils.isNotTrue(null));
    }

}

