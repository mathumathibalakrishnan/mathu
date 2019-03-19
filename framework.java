package mobileweb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class framework {
		static WebDriver  driver;
		static String historyvideoname;
		static ArrayList<String> history = new ArrayList<String>();
		 
	     public static void site()     {
	        //Headless browser
	    	// ChromeOptions options = new ChromeOptions();
	    //	System.setProperty("webdriver.chrome.driver", "chromedriver");
	    //options.addArguments("headless");
            // options.addArguments("window-size=1200x600");
             // driver = new ChromeDriver(options);
              //Chrome browser
           driver = new ChromeDriver();
              System.setProperty("webdriver.chrome.driver", "chromedriver");
	    	 driver.get("https://app.myoutdoortv.com/");
	       driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     }
	 
	 public static void signup() throws Exception {
		 driver.findElement(By.xpath("//button[@class='login-button navigation-link']")).click();
			Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='signup-tab-button tab-button active']")).click();	
		 driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//div[@class='input-box']//input[@type='email']")).sendKeys("mathumathi+20@viewlift.com");
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

			driver.findElement(By.xpath("//div[@class='input-box collapsible ']")).sendKeys("Mathumathi");
			
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
			
			
	driver.findElement(By.xpath("//*[@class='input-box']//input[@type='password']")).sendKeys("123456");

	driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	String name = driver.findElement(By.xpath("//span[@class='name']")).getText();
	System.out.println(name +" Signed Up");

	 }
	     public static void navigationIntoApp() {
	 
	       driver.findElement(By.xpath("//button[@class='login-button navigation-link']")).click();
	       driver.findElement(By.xpath("//*[@class='login-tab-button tab-button active']")).click();
	       driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	      driver.findElement(By.xpath("//div[@class='input-box']//input[@type='email']")).sendKeys("arul@viewlift.com");
	       driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//*[@class='input-box']//input[@type='password']")).sendKeys("test1");
	       driver.findElement(By.xpath("//input[@type='submit']")).click();
	       String name = driver.findElement(By.xpath("//span[@class='name']")).getText();
	       System.out.println(name +" logged in");                              
	}
	 
	 
	     public static ArrayList<String> videoclick() throws Exception {
	 
	            Thread.sleep(1000);
	       String videoname = null;
	       ArrayList<String> ref = new ArrayList<String>();
	           List<WebElement> li =driver.findElements(By.xpath("//div[@class='modules undefined']//div[@class='module']"));
	           int counts= li.size();
	           int count = counts-1;
	          // System.out.println(count);
	           for(int i=count-1;i>=4;i--){
	                      driver.findElement(By.xpath("//div[@class='modules undefined']//div["+i+"]/div[@class='video-tray  landscape ']//div[@data-index='1']//a")).click();
	             //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	               Actions action = new Actions(driver);
	               WebElement elm1 = driver.findElement(By.xpath("//*[@class=\"video-tray-item \"]"));
	               action.click(elm1).perform();
	               driver.findElement(By.xpath("//*[@id=\"vjs_video_3\"]")).click();
	               System.out.println("Video started");
	               driver.manage().timeouts().implicitlyWait(31, TimeUnit.SECONDS);
	               try {                       
	                          String erro1 = driver.findElement(By.cssSelector("h1[class*='fade__content']")).getText();
	                          if (erro1.contains("404")) {
	                                    System.out.println("Video not working due to " +erro1); }
	                                                driver.findElement(By.xpath("//span[contains(text(),'Home')]")).click();
	                          }catch(Exception e) {
	                                     try {
	                                     String erro =  driver.findElement(By.xpath("//div[@class='vjs-modal-dialog-content' and starts-with(text(), 'The media ')]")).getText();
	                                     if (erro.contains("The media could not be loaded")) {
	                                               System.out.println("Video not working due to " +erro);
	                                           }
	                                     }catch(Exception e1 ) {
	                                     System.out.println("Video is working fine without any issue");         
	                                      }                 
	               driver.findElement(By.xpath("//button[@class='close ']")).click();
	               JavascriptExecutor jse = (JavascriptExecutor)driver;
	               jse.executeScript("window.scrollBy(0,-250)", "");
	 
	               videoname = driver.findElement(By.xpath("//div[@class='show__title__box']/span[1]")).getText();
	               System.out.println("Video played is "+videoname);
	                ref.add(videoname);
	                historyvideoname =  driver.findElement(By.xpath("//div[@class='show__next__episode']//span[@class='name']")).getText();
	                //System.out.println("Historyname is "+historyvideoname);
	                history.add(historyvideoname);
	                                     try {
	                                     driver.findElement(By.xpath("(//*[@class='add-watchlist site-color'])[last()-1]")).click();
	                                     System.out.println("Content added to the watchlist");
	                                     }catch(Exception e2) {
	                                                driver.findElement(By.xpath("(//*[@class='remove-watchlist site-color'])[last()-1]"));
	                                                System.out.println("Content was already added to the watchlist");
	                                                driver.findElement(By.xpath("//div[@class='logo-container']")).click();
	                                     }
	               driver.findElement(By.xpath("//div[@class='logo-container']")).click();
	                                  }
	              }       
	          
	            return ref;
	}
	 
	     public static ArrayList<String> watchlistverfication(){
	            driver.findElement(By.xpath("//span[@class='name']")).click();
	            driver.findElement(By.xpath("//*[@class='dropdown']/a[@href='/user/watchlist']")).click();
	           List<WebElement> li = driver.findElements(By.xpath("//*[@class='list__body__container']//div[@class='title']"));
	           int count=li.size();
	           System.out.println("Number of contents in watchlist is "+count);
	           ArrayList<String> ref = new ArrayList<String>();
	           for(int i=0;i<count;i++){
	                      String name = driver.findElement(By.xpath("(//*[@class='list__body__container']//div[@class='title'])[last()-"+i+"]")).getText();
	                   //   System.out.println(name);
	                      ref.add(name);
	           }
	          
	           return ref;
	     }
	 
	     
	     public static void watchlist() throws Exception {
	    	ArrayList<String> ref= videoclick();
	         ArrayList<String> ref1 =  watchlistverfication(); 
	       
	        
	        for(String s: ref){
	        	  // System.out.println(s);
	        	   int i=0;
	            	   for(String s1:ref1){
	            		   if(s.equalsIgnoreCase(s1)){
	            			   i++;
	            		   }else{	   
	            		   }
	            	   }
	        	   if(i==1){
	        		   System.out.println(s+"is appearing in the waitlist");
	        	   }else{
	        		   System.out.println(s+" is not appearing in the waitlist");
	        	   }
	        	   i=0;
	           }
	        
	        }
	        

		public static ArrayList<String> historyverification(){
	        driver.findElement(By.xpath("//span[@class='name']")).click();
	        driver.findElement(By.xpath("//*[@class='dropdown']/a[@href='/user/history']")).click();
	       List<WebElement> li1 = driver.findElements(By.xpath("//*[@class='list__body__container']//div[@class='title']"));
	       int count1=li1.size();
	       System.out.println("Number of contents in History is "+count1);
	       ArrayList<String> ref = new ArrayList<String>();
	       for(int i=0;i<count1;i++){
	                  String name1 = driver.findElement(By.xpath("(//*[@class='list__body__container']//div[@class='title'])[last()-"+i+"]")).getText();
	              //    System.out.println(name1);
	                  ref.add(name1);
	       }
	      
	       return ref;
	}

	 
		public static void history() throws Exception {
		 
	     ArrayList<String> ref3 =  historyverification();   
	     
	      for(String s: history){
	    	  // System.out.println(s);
	    	   int i=0;
	        	   for(String s1:ref3){
	        		   if(s.equalsIgnoreCase(s1)){
	        			   i++;
	        		   }else{	   
	        		   }
	        	   }
	    	   if(i==1){
	    		   System.out.println(s+"is appearing in the history");
	    	   }else{
	    		   System.out.println(s+" is not appearing in the history");
	    	   }
	    	   i=0;
	       }
	      
	     }
	    public static void search() throws Exception {
	    	driver.findElement(By.cssSelector("div[class='search-icon']")).click();
	        driver.findElement(By.cssSelector("input[class='search-container-input']")).sendKeys("video");
			Thread.sleep(2000);
	        driver.findElement(By.xpath("(//a[@class='video-tray-item '])[last()-5]")).click();
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//button[@class='close ']")).click();
	        
			
	    }
	     
	public static void main(String[] args) {
	          try {
	        	  System.out.println(java.time.LocalTime.now());    
	        	   
	        	  site();
	        	 // signup();
	              navigationIntoApp();
	     
	        watchlist();
	        history();
	        search();
	          //   driver.close();
	             System.out.println(java.time.LocalTime.now());    
	       	     
	}catch(Exception e ){
	    e.printStackTrace();
	    }
	}
		
		
	}


