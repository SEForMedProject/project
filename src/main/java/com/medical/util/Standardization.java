package com.medical.util;

import org.springframework.stereotype.Component;

/**
 * Created by ewrfcas on 2017/5/12.
 */
//标准化方法
@Component
public class Standardization {
    public String filter(String name){
        name=name.replaceAll("疼","痛").replaceAll("脑袋|头部|脑壳|头颅","头")
                .replaceAll("发梢","头发").replaceAll("眼珠","眼睛")
                .replaceAll("耳朵","耳").replaceAll("鼻头","鼻子")
                .replaceAll("嘴巴|嘴唇","嘴").replaceAll("脖子","头颈")
                .replaceAll("指头","手指").replaceAll("肺脏","肺")
                .replaceAll("脾脏","脾").replaceAll("肚子","腹部")
                .replaceAll("屁股|腚","臀部").replaceAll("腿部","腿")
                .replaceAll("脚踝","脚跟").replaceAll("喉咙","咽喉")
                .replaceAll("胸口","胸").replaceAll("脚底","足底")
                .replaceAll("脊椎|脊骨","脊柱");
        return name.replaceAll("十分|相当|非常|很|有点|特别|剧|剧烈|太|越|越来越|稍|稍微|仅仅|一点|明显地" +
                        "|有些|最|极|极其|格外|分外|更|更加|越发|有点儿|几乎|略微|过于|尤其|已|已经|曾|曾经|刚|" +
                        "才|刚才|刚刚|正在|就|就要|立刻|顿时|常|常常|时常|时时|往往|渐渐|早晚|一向|向来|从来|总是|" +
                        "始终|仍然|还是|屡次|依然|还|再|再三|偶尔|忽然|简直|大约","");
    }
}
