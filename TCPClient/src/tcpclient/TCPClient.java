/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author sa237
 * Name:Sanskriti Agrawal
 * Roll No: 1810110216
 */
public class TCPClient {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws Exception{
        try{
            Socket client = new Socket("localhost",6623);
            DataInputStream input = new DataInputStream(client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            System.out.println("Enter the number of nodes");
            Scanner in = new Scanner(System.in);
            int num_nodes;
            num_nodes = in.nextInt();
            System.out.println("Enter the adjacency matrix inputs");
            int mat[][] = new int[num_nodes][num_nodes];
            for(int i=0;i<num_nodes;i++){
                for(int j=0;j<num_nodes;j++){
                    mat[i][j] = in.nextInt();
                }
            }
            System.out.println("Enter the nodes to be analysed");
            Scanner in2 = new Scanner(System.in);
            String node1 = in2.nextLine();
            String node2 = in2.nextLine();
            System.out.println("Enter the length");
            int length = in.nextInt();
            // send the number of nodes
            output.writeInt(num_nodes);
            // send the adjacency matrix
            for(int k = 0; k < num_nodes; k++) {
                for(int p=0;p<num_nodes;p++){
                    output.writeInt(mat[k][p]);
                }
                
            }
            //send the length of the path
            output.writeInt(length);
            //send the starting node
            output.writeChar(node1.charAt(0));
            //send the ending node
            output.writeChar(node2.charAt(0));
            output.flush();
            //getting the output from the server
            String conclusion = input.readUTF();
            System.out.println(conclusion);
            client.close();
            
                
            
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        

}
}
