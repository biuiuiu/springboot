package com.zzyyaa.test.demoUi;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;

import com.zzyyaa.test.theardTest.TheardTest;

@Controller
@Path("/test")
@Produces({"application/json;charset=utf-8"})
@Consumes({"application/json;charset=utf-8"})
public class Test {
	
	
	@GET
	@Path(value = "/testCallable/{num}")
	public String returnNum(@PathParam("num") String num) throws Exception{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<String> future = executorService.submit(new TheardTest());
		System.out.println(future.get());
		num = future.get();
		return num;
	}
}
