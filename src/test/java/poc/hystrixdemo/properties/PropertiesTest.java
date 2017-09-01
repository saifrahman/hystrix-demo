package poc.hystrixdemo.properties;


import org.junit.Test;

import junit.framework.Assert;
import poc.hystrixdemo.commands.properties.DefaultPropertyCommand;
import poc.hystrixdemo.commands.properties.DynamicInstanceCommand;
import poc.hystrixdemo.commands.properties.GlobalDefaultCommand;
import poc.hystrixdemo.commands.properties.InstanceSpecificCommand;

public class PropertiesTest {
	
	//comment out the property from config file to run this
	@Test
	public void getGlobalDefaultValue(){
		String s = new GlobalDefaultCommand().execute();
		Assert.assertEquals("2000", s);
		System.out.println(s);
	}
	@Test
	public void getDefaultValue(){
		String s = new DefaultPropertyCommand().execute();
		Assert.assertEquals("2000", s);
		System.out.println(s);
	}
	@Test()
	public void getInstanceValue(){
		String s = new InstanceSpecificCommand().execute();
		Assert.assertNotSame("3000", s);
		System.out.println(s);
	}
	@Test
	public void getDynamicInstanceValue(){
		String s = new DynamicInstanceCommand().execute();
		Assert.assertEquals("4000", s);
		System.out.println(s);
	}
	
	
}
