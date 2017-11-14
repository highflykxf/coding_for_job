package coding.test.huawei;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main3 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine().trim();
		sc.close();
		
		String[] hostnames = s.split("\\|");
		List<HostName> hostname_list = new ArrayList<HostName>();
		for(int i=0; i<hostnames.length; i++){
			HostName tmp = new HostName(hostnames[i]);
			hostname_list.add(tmp);
		}
		Collections.sort(hostname_list);
		StringBuilder sb = new StringBuilder("");
		for(int i=0; i<hostname_list.size();i++){
			sb.append(hostname_list.get(i).hostname);
			if(i<hostname_list.size()-1)
				sb.append("|");
		}
		System.out.println(sb.toString());
	}
}

class HostName implements Comparable<HostName>{
	String hostname;
	int len;
	String[] strs;
	public HostName(String s){
		this.strs = s.split("\\.");
		this.len = this.strs.length;
		this.hostname = s;
	}
	@Override
	public int compareTo(HostName o) {
		int min_len = 0, res = 0;
		min_len = this.len < o.len? this.len : o.len;
		for(int i=0;i<min_len;i++){
			res = strs[this.len-1-i].compareTo(o.strs[o.len-1-i]);
			if(res!=0)
				break;
		}
		if(res==0)
			res = this.len - o.len;
		return res;
	}
	
}