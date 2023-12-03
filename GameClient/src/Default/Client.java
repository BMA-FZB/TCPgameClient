package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 2020;

	public static void main(String[] args) {
		System.out.println("guess number between 0-10 ");
		try {
			doGuess();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		

	}

    private static void doGuess() throws IOException {
		System.out.println(">> ");
		int message=new Scanner(System.in).nextInt();
		Socket socket=new Socket(SERVER_ADDRESS,SERVER_PORT);
		doSendInt(socket, message);
		
		int receivedMessage=doReceiveInt(socket);
		displayMessage(receivedMessage);
		
		
	}



	private static void displayMessage(int rm) throws IOException {
		if(rm==0) {
			System.out.println("try again");
			doGuess();
		}
		if(rm==1) {
			System.out.println("you losed ");
		}
		
		
		
	}

	private static void doSend(Socket socket, String message) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeBytes(message + "\n");
    }

    private static String doReceive(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readLine();
    }
    private static void doSendInt(Socket socket, int value) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeInt(value);
    }

    private static int doReceiveInt(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readInt();
    }
}
