package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class upload {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		upload a = new upload();
		try {
			a.fetch_url();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetch_url() {
		try {
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.webqa.lego.com/en-us/creator/galleries/upload");

			Thread.sleep(2000);

			Boolean isPresent = driver.findElements(By.className("transparency")).size() > 0;
			// System.out.println(isPresent);
			driver.switchTo().frame("legoid-iframe");
			if (isPresent) {
				driver.findElement(By.id("fieldUsername")).sendKeys("newqauser1");
				driver.findElement(By.id("fieldPassword")).sendKeys("lego@123");
				driver.findElement(By.id("buttonSubmitLogin")).click();
				Thread.sleep(2000);
				// System.out.println("Logged in");
			} else {
				System.out.println("Already Logged In");
			}

			driver.get("https://www.webqa.lego.com/en-us/creator/galleries/upload");
			driver.findElement(By.xpath("//lego-album-upload-button/span/input"))
					.sendKeys("C:\\Users\\a1PreBhu\\Workspace\\LEGO\\MCDP.jpg");
			Thread.sleep(5000);
			driver.findElement(By.id("albumTitle")).sendKeys("TestAlbum");
			driver.findElement(By.id("albumDescription")).sendKeys("Automation Upload");
			driver.findElement(By.className("btn-lg")).click();
			Thread.sleep(5000);

			Boolean isPresent1 = driver.findElements(By.className("album-editor-success-modal")).size() > 0;
			if (isPresent1) {
				System.out.println("Album uploaded successfully");
			} else {
				System.out.println("Album not uploaded");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
