package fitpeopackage;
//imported files
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//download java jdk and set paths
//downloads chromedriver and selenium jars and add them to the project
//create a project in Intellij then a package and add a java class and include all the setup of selenium and chrome driver to project
// import jars and use in code
public class fitPeoClass {
    public static void main(String[] args) throws InterruptedException {
        //Browse chrome driver and here in 2nd parameter we give the path of our own chromedriver.exe in our local
        System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\IdeaProjects\\FitPeoProject\\chromedriver.exe");
        //Create chrome instance
        WebDriver driver = new ChromeDriver();
        //Open application
        driver.get("https://www.google.com/");
        //maximize browser
        driver.manage().window().maximize();
        //1.navigate to FitPeo Homepage
        driver.navigate().to("https://www.fitpeo.com/");
        Thread.sleep(3000);
        //2.navigate to Revenue Calculator Page
        /*//i.navigate to Revenue Calculator Page by using xpath
        driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();*/
        //ii.navigate to Revenue Calculator Page by using navigate().to()
        driver.navigate().to("https://fitpeo.com/revenue-calculator");
        //scroll down to slider section
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("scroll(0,3351)");//total scroll length
        jse.executeScript("scroll(0,400)");
        //slide to 820
        WebElement slide= driver.findElement(By.cssSelector(".MuiSlider-thumb > input:nth-child(1)"));//.MuiSlider-thumb .MuiSlider-root .MuiSlider-thumb > input:nth-child(1)
        Actions move= new Actions(driver);
        int width= slide.getSize().width;
        int target = 820;
        int min=0;
        int max=2000;
        int offset=(int)(((target-min)/(double)(max-min))*width);
        move.dragAndDropBy(slide,offset,0).perform();
        //update text to 560
        WebElement updatetextfield= driver.findElement(By.cssSelector("#\\:R57alklff9da\\:"));
        updatetextfield.clear();
        updatetextfield.sendKeys("560");
        //validate slider value
        String slidevalue =slide.getAttribute("value");
        if("560".equals(slidevalue)){
            System.out.println("slide value equals 560");
        }else{
            System.out.println("slide value not equals 560");
        }
        //updatetextfield.sendKeys("820");
        //Select CPT Codes
        WebElement cpt99091= driver.findElement(By.cssSelector("div.css-4o8pys:nth-child(1) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));
        WebElement cpt99453= driver.findElement(By.cssSelector("div.css-4o8pys:nth-child(2) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));
        WebElement cpt99454= driver.findElement(By.cssSelector("div.css-4o8pys:nth-child(3) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));
        WebElement cpt99474= driver.findElement(By.cssSelector("div.MuiBox-root:nth-child(8) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));
        cpt99091.click();
        cpt99453.click();
        cpt99454.click();
        jse.executeScript("scroll(0,800)");
        cpt99474.click();
        //validate total recurring reimbursement
        WebElement header= driver.findElement(By.cssSelector("p.css-1xroguk:nth-child(4) > p:nth-child(1)"));
        String text=header.getText();
        if(text.contains("$110700")){
            System.out.println("Header displaying Total recurring reimbursement for all patients per month: shows the value $110700");
        }
        else{
            System.out.println("Header is not displaying Total recurring reimbursement for all patients per month: shows the value $110700");
        }

        //close browser
        //driver.close();//closes current tab
        driver.quit();//close all apps
    }

}

