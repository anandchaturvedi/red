package com.abhilash.test;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

@RestController
public class CommandController {
	
	String model = null;

	@RequestMapping(value = "/run" , method=RequestMethod.GET)
   public String runCommnad(@RequestParam ("commandName") String command) throws IOException {
		String sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        oDefaultExecutor.setExitValue(0);
        try {
            int returnval = oDefaultExecutor.execute(oCmdLine);
        } catch (ExecuteException e) {
            System.err.println("Execution failed.");
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            System.err.println("permission denied.");
            e.printStackTrace();
            throw e;
        }
        
       return "SUCCESS";
    }
	@RequestMapping(value = "/runpy" , method=RequestMethod.GET)
	   public String runpyCommnad(@RequestParam ("commandName") String command) throws IOException {
			String sCommandString = command;
	        CommandLine oCmdLine = CommandLine.parse("python3 " +   sCommandString);
	        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
	        oDefaultExecutor.setExitValue(0);
	        try {
	            int returnval = oDefaultExecutor.execute(oCmdLine);
	        } catch (ExecuteException e) {
	            System.err.println("Execution failed.");
	            e.printStackTrace();
	            throw e;
	        } catch (IOException e) {
	            System.err.println("permission denied.");
	            e.printStackTrace();
	            throw e;
	        }
	        
	       return "SUCCESS";
	    }
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/action" , method=RequestMethod.POST)
	   public String updateModel(@RequestBody String inputmodel) throws IOException, InterruptedException {
			
		model = inputmodel;
		
		
	        Message.deploy = model;
//	        	 ServerSocket server = new ServerSocket(9090);
//	        	 System.out.println("Server has started on 127.0.0.1:9090.\r\nWaiting for a connection…");
//	        			 Socket client = server.accept();
//	        			 System.out.println("A client connected.");
//	        			 DataOutputStream dout=new DataOutputStream(client.getOutputStream()); 
//	        			 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
//	        			 dout.writeUTF(model);  
//	        			 dout.flush();  
//	        			 Thread.sleep(100);
//	      
	        
	       return "SUCCESS";
	    }
   }
