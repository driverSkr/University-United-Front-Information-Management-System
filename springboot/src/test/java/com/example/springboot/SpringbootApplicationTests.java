package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.springboot.config.MarkdownUtils.markdownToHtml;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {

        String html = markdownToHtml("　  3月10日，省委统战部派出以省纪委省监委驻省委统战部纪检监察组组长徐小平为组长的调研组一行4人到井冈山大学调研座谈，吉安市委统战部副部长、市侨办主任周明霞陪同调研。井冈山大学党委书记胡春晓，党委常委、统战部长肖洪海出席了调研座谈会，学校有关部门负责人参加了座谈会。\n" +
                "\n" +
                "　  座谈会上，胡春晓介绍了学校基本情况，重点介绍了学校学习贯彻新发布的《中国共产党统一战线工作条例》有关情况，介绍了学校的民族宗教工作，以及省委巡视组巡视过程中涉及到的相关工作的整改情况。肖洪海汇报了学校近年来开展统一战线工作有关情况，就学习《条例》的做法、打造统战工作品牌、民族宗教工作和有关工作提出了建议。国际交流合作处、保卫处等、学工部等部门负责人也就相关工作作了汇报。\n" +
                "\n" +
                "　  徐小平充分肯定了井冈山大学的统一战线工作，他指出，井冈山大学党委对统战工作高度重视，层层传导压力，压实了工作责任，建立健全了相关工作机制，《条例》学习全覆盖，统战工作有特色有亮点有成效。调研组一行还就相关工作与学校展开了深入交流和探讨。\n" +
                "\n");
        System.out.println(html);
    }


}
