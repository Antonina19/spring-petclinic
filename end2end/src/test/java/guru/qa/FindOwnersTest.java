package guru.qa;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import guru.qa.domain.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FindOwnersTest {
	private IOwnersManager om = new OwnersManagerNG(); //old OwnersManager
	private int createdOwnerId;

	@BeforeEach
	void addOwner() {
		createdOwnerId = om.createOwner(Owner.builder()
			.firstName("Lana")
			.lastName("Fox")
			.address("Russia")
			.city("Moscow")
			.telephone("456789644")
			.build());
	}

	@AfterEach
	void releaseOwner(){
		om.deleteOwner(createdOwnerId);
	}

	@RepeatedTest(2)
	void findOwnersTest(){
		System.out.println("### Generated id: " + createdOwnerId);
		open("http://localhost:8080/owners/find");
		$("#lastName").setValue("Fox");
		$("button[type='submit']").click();
		$("table.table").should(Condition.visible);
		$$("tr").find(text("Name")).should(text("Lana Fox"));
	}


}
