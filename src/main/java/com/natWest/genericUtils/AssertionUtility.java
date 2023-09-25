package com.natWest.genericUtils;


import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Assert;

import static com.natWest.genericUtils.JsonUtility.*;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static net.javacrumbs.jsonunit.JsonAssert.when;
import static net.javacrumbs.jsonunit.core.Option.*;


public class AssertionUtility {

    public static void assertJsonIgnoringExtraFields(Object expectedResponse, Response actualResponse) {
        try {
            assertJsonEquals(JsonUtility.toJson(expectedResponse), actualResponse.asString(), when(IGNORING_EXTRA_FIELDS));
        } catch (Throwable e) {
            Assert.fail("Exception in method assertJsonIgnoringExtraFields:" + ExceptionUtils.getStackTrace(e));
        }
    }
}
