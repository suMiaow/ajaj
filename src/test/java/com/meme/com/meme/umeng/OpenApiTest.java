package com.meme.com.meme.umeng;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.client.exception.OceanException;
import com.umeng.umini.param.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class OpenApiTest {

    private ApiExecutor apiExecutor;
    private final String dataSourceId = "61c95bd6e014255fcbcb6219";

    @BeforeEach
    public void init() {
        apiExecutor = new ApiExecutor("6144312", "QqCL2H1sKl");
        apiExecutor.setServerHost("gateway.open.umeng.com");
    }

    public UmengUminiGetTotalUserResult getTotalUser(String fromDate, String toDate) throws OceanException {

        UmengUminiGetTotalUserParam param = new UmengUminiGetTotalUserParam();
        // 测试环境只支持http
//        param.getOceanRequestPolicy().setUseHttps(false);
        param.setDataSourceId(dataSourceId);
        param.setFromDate(fromDate);
        param.setToDate(toDate);


        return apiExecutor.execute(param);
    }

    public UmengUminiGetEventListResult getEventList() throws OceanException {
        UmengUminiGetEventListParam param = new UmengUminiGetEventListParam();
        param.setDataSourceId(dataSourceId);

        return apiExecutor.execute(param);
    }

    public UmengUminiGetEventProvertyListResult getEventPropertyList(String eventName) throws OceanException {
        UmengUminiGetEventProvertyListParam param = new UmengUminiGetEventProvertyListParam();

        param.setDataSourceId(dataSourceId);
        param.setEventName(eventName);

        return apiExecutor.execute(param);
    }

    public UmengUminiGetEventOverviewResult getEventOverview(String eventName, String timeUnit, String fromDate, String toDate) throws OceanException {
        UmengUminiGetEventOverviewParam param = new UmengUminiGetEventOverviewParam();

        param.setDataSourceId(dataSourceId);
        param.setEventName(eventName);
        param.setTimeUnit(timeUnit);
        param.setFromDate(fromDate);
        param.setToDate(toDate);

        return apiExecutor.execute(param);
    }

    public UmengUminiGetAllPropertyValueCountResult getAllPropertyValueCount(String eventName,
                                                                             String propertyName,
                                                                             String timeUnit,
                                                                             String fromDate,
                                                                             String toDate) throws OceanException {

        UmengUminiGetAllPropertyValueCountParam param = new UmengUminiGetAllPropertyValueCountParam();

        param.setDataSourceId(dataSourceId);
        param.setEventName(eventName);
        param.setPropertyName(propertyName);
        param.setTimeUnit(timeUnit);
        param.setFromDate(fromDate);
        param.setToDate(toDate);

        return apiExecutor.execute(param);
    }

    public UmengUminiGetVisitPageListResult getVisitPageList(
            String timeUnit,
            String fromDate,
            String toDate) throws OceanException {

        UmengUminiGetVisitPageListParam param = new UmengUminiGetVisitPageListParam();

        param.setDataSourceId(dataSourceId);
        param.setTimeUnit(timeUnit);
        param.setFromDate(fromDate);
        param.setToDate(toDate);
        param.setOrderBy("visitTimes");
        param.setDirection("desc");
        param.setPageIndex(0);
        param.setPageSize(30);

        return apiExecutor.execute(param);
    }


    @Test
    void testApi() {

        try {
            log.info("totalUser: \n{}\n", JSONObject.toJSONString(getTotalUser("2021-12-25", "2021-12-31")));
            log.info("eventList: \n{}\n", JSONObject.toJSONString(getEventList()));
            log.info("eventPropertyList: \n{}\n", JSONObject.toJSONString(getEventPropertyList("add-cart")));
            log.info("eventOverview: \n{}\n", JSONObject.toJSONString(getEventOverview("add-cart", "day", "2021-12-25", "2021-12-31")));
            log.info("allPropertyValueCount: \n{}\n", JSONObject.toJSONString(getAllPropertyValueCount("add-cart", "productCode", "day", "2021-12-25", "2021-12-31")));
            log.info("visitPageList: \n{}\n", JSONObject.toJSONString(getVisitPageList("day", "2021-12-25", "2021-12-30")));

        } catch (OceanException e) {
            log.error("errorCode: {}", e.getErrorCode());
            log.error("errorMessage: {}", e.getErrorMessage());
            e.printStackTrace();
        }
    }


}
