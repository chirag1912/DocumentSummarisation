package MindMapGraph;

import java.io.File;
import java.io.IOException;

public class drawGraph {

	public void main_calling(){
		// TODO Auto-generated method stub
		try {
			Process p =  Runtime.getRuntime().exec("cmd.exe /c start  draw.bat");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Process p1 =  Runtime.getRuntime().exec("cmd.exe /c start  outA.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
