# Spring Boot 單元測試筆記

## 環境
- Win10
- Gradle Version 6.4
- Intellij IDEA Community 2020.2

## Gradle設定
```groovy
dependencies {
    // 其他省略
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        // 排除JUnit Platform上運行的JUnit3和JUnit4測試引擎
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
}
```
## JUnit5 jupiter
 
## 單元測試報告Jacoco設定
某些情況下，我們需要單元測試覆蓋率測試報告，此時就可以使用Jacoco來幫助我們產生報告。

### Gradle安裝
```groovy
plugins {
    // 其他省略
    id 'jacoco'     
} 

// 其他省略

test {
    useJUnitPlatform()
    finalizedBy jacocoTestReport // 測試完畢後產生report
}

jacoco {
    toolVersion = '0.8.5' // jacoco版本
//	reportsDir = file("$buildDir/customJacocoReportDir") // 自訂jacoco報告資料夾
}

jacocoTestReport {
    dependsOn test // 再產生報告前需要先運行測試

    reports {
        xml.enabled false
        csv.enabled false
//		html.destination file("${buildDir}/jacocoHtml")
    }

    // 測試評估後的動作
    afterEvaluate {
        classDirectories.from = files(classDirectories.files.collect {
            // 要忽略domain資料夾、UnitTestNoteApplication檔案。
            fileTree(dir: it, excludes: ['**/domain/**', '**/UnitTestNoteApplication**'])
        })
    }
}
```

### 執行jacoco，產生報告
執行jacoco時，比較容易遇到的問題就是產生的報告是亂碼，因為中文字而造成。
因此需要使用JVM的參數來設定UTF-8來避免此問題。
```
-Dfile.encoding=UTF-8
```

解決方式如下
1. 使用IDEA執行 <br>
Help => Edit Custom VM Options => 開啟202.6948.69.vmoptions <br>
將`-Dfile.encoding=utf-8`加入後，重新啟動IDEA。

**補充**
如果重新啟動IDEA後，執行仍然有亂碼問題，解決方式如下 <br>
File => Invalidate Caches / Restart => 點選Invalidate and Restart，此方式會清除Cache並重新啟動。
 
2. 使用gradlew執行
```
# 使用此方式執行，如果有包含中文產生出來會是亂碼
gradlew clean build

# 可以使用UTF-8參數進行編碼，這樣不會產生亂碼
gradlew -Dfile.encoding=UTF-8 clean build
```
### 結果顯示
 
## 參考
- [@ExtendWith(SpringExtension.class) vs @ExtendWith(MockitoExtension.class)](https://stackoverflow.com/questions/60308578/extendwithspringextension-class-vs-extendwithmockitoextension-class)
- [Jacoco Gradle 6.7.1官方文件](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
- [Junit-5](https://www.baeldung.com/junit-5-runwith)
- [中文亂碼問題](https://testerhome.com/topics/8329?order_by=like&)



