package test.service;

import org.testng.annotations.Test;

import test.BaseTest;

public class ContractQuotationsServiceTest extends BaseTest {
	@Test
	public void shengchenghetong(){
		contractQuotationsService.shengchengContractFile(1);
	}
}
