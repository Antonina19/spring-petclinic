package guru.qa;

import guru.qa.domain.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddOwnerTest {
	private IOwnersManager om = new OwnersManager(); //old OwnersManager

	@Test
	void checkAddOwner(){
		open("http://localhost:8080/owners/find");
		$("a.btn").click();
		$("#firstName").setValue("Dog");
		$("#lastName").setValue("Bublic");
		$("#address").setValue("Russia");
		$("#city").setValue("Sochi");
		$("#telephone").setValue("6990554");
		$("button.btn-default").click();
		Owner actualOwner = om.findByLastName("Bublic").get(0);
		Assertions.assertEquals("Sochi", actualOwner.getCity());
	}
}
