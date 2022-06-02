package team.lte.bizservice;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import team.lte.bizservice.entity.po.C2inew;
import team.lte.bizservice.service.C2inewService;

import javax.annotation.Resource;

@SpringBootTest
@ActiveProfiles("dev")
public class TestList {
    @Resource
    C2inewService c2inewService;

    @Test
    public void testlist() {
        c2inewService.createTbc2inew(2);
    }
}
