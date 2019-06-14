package com.coll.Config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DBConfigTest
{
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext("com.coll");
	}
}
