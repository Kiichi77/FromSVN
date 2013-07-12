import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class TestIpmi {
	public void getSensorInfo(String cmd) throws InterruptedException{
		String command = "ipmitool -H 192.168.1.20 -U test -P 123456 sensor get '"+cmd+"'";
		System.out.println(command);
		Process p;
		Runtime r = Runtime.getRuntime();
		try {
			p = r.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String inline;
			Thread.sleep(1000);
			while (null != (inline = br.readLine())) {
				try{
					System.out.println(inline);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			while (null != (inline = br.readLine())) {
				try{
					System.out.println(inline);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public static void main(String[] args){
	int a=3;
	int b= 3;
	if(a<=b){
		System.out.println("aaa");
	}
	
	HashMap map = new HashMap();
	int qq = (Integer) map.get("sss");
	System.out.println(qq);
	
	TestIpmi aa = new TestIpmi();
	try {
		aa.getSensorInfo("Inlet Temp");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
