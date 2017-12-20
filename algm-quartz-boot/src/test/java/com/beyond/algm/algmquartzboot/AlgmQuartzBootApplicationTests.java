package com.beyond.algm.algmquartzboot;

import com.beyond.algm.algmquartzboot.infra.CallQuartzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgmQuartzBootApplicationTests {
	@Autowired
	private CallQuartzService callQuartzService;

	@Test
	public void contextLoads() throws Exception{
		callQuartzService.callQuartzs();
	}
}
