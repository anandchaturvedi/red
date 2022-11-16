package com.abhilash.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
	
	@RequestMapping(value = "/action" , method=RequestMethod.GET)
	   public String updateModel(@RequestParam ("eventName") String eventName) throws IOException, InterruptedException {
			
		System.out.println("eventName = " + eventName);
		String tag = "<html><h1><center>Deployment is ";
		String output = "unknown";
		String endtag = "<center></html>";
		String messageToSend = "";
		
		
		if (eventName.equalsIgnoreCase("AuditOnly"))
			messageToSend = "You can only hear Audio because this car is Radio.";
		else if(eventName.equalsIgnoreCase("DisplayOnly"))
			messageToSend = "You can not  hear Audio because this car is out of Battery.";
		else if(eventName.equalsIgnoreCase("AudioWithDisplay"))
			messageToSend = "You can only hear Audio because this car is Radio.";
		else
			messageToSend = "This car is junk";
		
		try {
					WebSocketClient wsc = new WebSocketClient();;
			        wsc.Connect("ws://192.168.0.25:9000");			        
			        wsc.SendMessage(messageToSend);
			        Thread.sleep(1000);
			        wsc.Disconnect();
			        output = "Success";
		}
		catch(Exception e)
		{
			output = "Failed";
		}
		
	       return tag+output+endtag;
	    }
   }
