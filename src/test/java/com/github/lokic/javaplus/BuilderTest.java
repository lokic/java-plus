package com.github.lokic.javaplus;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

public class BuilderTest {

    @Test
    public void test() {
        UserInfo expect = new UserInfo();
        expect.setName("lokic");
        expect.setAge(1);
        expect.setAddress("zhejiang,hangzhou,abc");

        UserInfo res = Builder.of(UserInfo::new)
                .with(UserInfo::setName, "lokic")
                .with(UserInfo::setAge, 1)
                .with(UserInfo::setAddress, "abc")
                .with(UserInfo::setProvinceAndCity, "zhejiang", "hangzhou")
                .build();

        Assert.assertEquals(expect, res);
    }

    @Data
    public static class UserInfo {
        private String name;
        private Integer age;
        private String address;

        public void setProvinceAndCity(String province, String city) {
            this.address = province + "," + city + "," + (address == null ? "" : address);
        }
    }

}