package ru.kibis.example

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class WebDriverTest: StringSpec() {

    init {
        val driver: WebDriver = getDriver().also {
            it.manage()?.timeouts()?.implicitlyWait(TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            it.manage()?.window()?.maximize()
        }

        "Распечатаем в консоль расписание для группы $group" {
            driver.run {
                get(urfuUrl)
                findElement(By.ByXPath(studentsPageXPath)).click()
                Thread.sleep(TIMEOUT_IN_MILLISECONDS)
                findElement(By.ByXPath(schedulePageXPath)).click()
                Thread.sleep(TIMEOUT_IN_MILLISECONDS)
                findElement(By.ByXPath(groupPageXPath)).sendKeys(group)
                Thread.sleep(TIMEOUT_IN_MILLISECONDS)
                val schedule = findElement(By.ByXPath(scheduleXPath)).text
                println(schedule)
                quit()
                schedule.contains("Группа РИЗМ-291201к") shouldBe true
            }
        }
    }

    private fun getDriver() : WebDriver {
        System.setProperty(DRIVER_SYSTEM_KEY, DRIVER_SYSTEM_PATH)
        return ChromeDriver()
    }

    companion object {
        const val DRIVER_SYSTEM_KEY = "webdriver.chrome.driver"
        const val DRIVER_SYSTEM_PATH = "/home/ilya/bin/chromedriver/chromedriver"
        const val TIMEOUT_IN_MILLISECONDS = 5000L
        const val group = "РИЗМ-291201к"
        const val urfuUrl = "https://urfu.ru/ru/"
        const val studentsPageXPath = "/html/body/div[2]/div[1]/div[1]/div/ul/li[1]/a"
        const val schedulePageXPath = "/html/body/div[2]/div[2]/div[2]/div/div[1]/div[1]/div/p[1]/a"
        const val groupPageXPath = "//*[@id=\"group_number\"]"
        const val scheduleXPath = "//*[@id=\"schedule-group\"]"
    }
}
