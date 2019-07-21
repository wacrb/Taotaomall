package com.taotao.jsontolist;

import com.taotao.common.utils.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Jsontolist {
    @Test
    public void test1() throws Exception {
        String paramData="[{\"id\":2,\"title\":\"aa\",\"name\":\"雄鹰表\"},{\"id\":3,\"title\":\"bb\",\"name\":\"陈定生\"},{\"id\":4,\"title\":\"cc\",\"name\":\"张阿勇\"}]";
        List jsonList =  JsonUtils.jsonToList(paramData, Map.class);
        //System.out.println(jsonList);
    }
}
