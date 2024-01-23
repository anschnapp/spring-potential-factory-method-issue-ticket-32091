package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
class DemoApplicationTests {
	@Autowired
	private ServiceWithDependency serviceWithDependency2Imp;

	@Test
	 void test() {
		serviceWithDependency2Imp.test();
	}
}
