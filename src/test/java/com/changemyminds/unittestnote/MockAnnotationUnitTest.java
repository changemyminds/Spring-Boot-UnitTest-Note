package com.changemyminds.unittestnote;

import com.changemyminds.unittestnote.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Author: Changemyminds.
 * Date: 2020/12/14.
 * Description:
 */
@ExtendWith(MockitoExtension.class)
public class MockAnnotationUnitTest {
    @Mock
    AppUserRepository mockRepository;

    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned() {
//        // 模擬count()方法回傳為123L
//        Mockito.when(mockRepository.count()).thenReturn(123L);
//
//        long userCount = mockRepository.count();
//
//        assertEquals(123L, userCount);
//
//        // 主要是用來判斷verify的方法是否有被呼叫過
//        Mockito.verify(mockRepository).count();
    }
}
