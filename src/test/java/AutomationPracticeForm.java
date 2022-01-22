import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class AutomationPracticeForm extends TestBase{

    @Test
    void automationPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldBe(Condition.visible, Duration.ofMillis(40_000));
        $("#firstName").setValue("Sam");
        $("#lastName").setValue("lastName");
        $("#userEmail").setValue("Test@Test.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        // --------- selectDate ---------

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $$(".react-datepicker__day").find(text("25")).click();

        $("#subjectsInput").setValue("arts").pressEnter();
        $$(".custom-control-label").find(text("Sports")).click();

        // --------- uploadFile ---------

        $("#uploadPicture").uploadFile(new File("src/test/resources/test.png"));

        // ------------------------------

        $("#currentAddress").setValue("TestAdress");
        $("#submit").scrollIntoView(true);
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        //--------- Check ---------

        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        $(".modal-body").shouldHave(text("Sam"), text("lastName"), text("Test@Test.com"),
                text("Male"), text("1234567890"), text("25 October,2000"), text("Arts"), text("Sports"),
                text("test.png"), text("TestAdress"), text("NCR"), text("Delhi"));
    }
}
