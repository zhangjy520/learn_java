package cc.gukeer.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Command {
    public static void exeCmd(String commandStr) {
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally
		{
			if (br != null)
			{
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void unzip(String password) throws IOException, InterruptedException {
		String cmd = "D:\\winRar\\rar x -hp"+password+" E:\\testunlock.rar E:\\ces\\";
		Process proc;
		proc = Runtime.getRuntime().exec(cmd);

		if (proc.waitFor() != 0) {
			System.out.println("密码错误");
			/*try {
				throw new Exception("错误:解压文件");
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		}else {
			System.out.println("密码破解成功,您的密码是："+password);
		}
	}



	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> passwordList = new ArrayList<>(); //WxTEst.getPassword();
		passwordList.add("123456");
		passwordList.add("1234567890");

		for (int i = 0; i <passwordList.size(); i++) {
			 unzip(passwordList.get(i));
		}
		/*while (true){
			if (passwordList.size()>0){

			}

		}*/

	}
}
class Passwords implements Runnable{
	@Override
	public void run() {
		try {
			Command.unzip(password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Passwords(String password){
		this.password = password;
	}

}
