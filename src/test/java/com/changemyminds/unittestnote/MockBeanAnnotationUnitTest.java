package com.changemyminds.unittestnote;

import com.changemyminds.unittestnote.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Author: Changemyminds.
 * Date: 2020/12/14.
 * Description:
 */
@ExtendWith(SpringExtension.class)
public class MockBeanAnnotationUnitTest {
    // 替換掉Spring內容器的UserRepository
    @MockBean
    AppUserRepository mockRepository;
    @Autowired
    ApplicationContext context;

    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned() {
//        // 模擬count()方法回傳為123L
//        Mockito.when(mockRepository.count()).thenReturn(123L);
//
//        // 從Spring ApplicationContext內取出Mock物件
//        AppUserRepository userRepoFromContext = context.getBean(AppUserRepository.class);
//        long userCount = userRepoFromContext.count();
//
//        assertEquals(123L, userCount);
//
//        // 主要是用來判斷verify的方法是否有被呼叫過
//        Mockito.verify(userRepoFromContext).count();
    }
}
