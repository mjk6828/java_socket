package java_socket.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {

	public static void main(String[] args) {
			ServerSocket server = null;
			Socket socket = null;
			BufferedReader in = null;
			BufferedWriter out = null;
			Scanner sc = new Scanner(System.in);
			//
			try {
				server = new ServerSocket(9999);
				System.out.println("연결 대기중.....");
				
				socket = server.accept();
				System.out.println("연결 되었습니다.");
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while(true) {
					// 한줄씩 읽어옴
					String inMsg = in.readLine();
					
					//만약 클라이언트가 bye라고 보내면
					if(inMsg.equalsIgnoreCase("bye")) {
						System.out.println("클라이언트가 나갔습니다.");
						break;
					}
					// 정상 메시지인 경우
					System.out.println("클라이언트 : "+ inMsg);
					
					System.out.print("보내기 >> ");
					String outMsg = sc.nextLine();
					out.write(outMsg + "\n");
					out.flush();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				
				try {
					sc.close();
					in.close();
					out.close();
					socket.close();
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
	}

}
