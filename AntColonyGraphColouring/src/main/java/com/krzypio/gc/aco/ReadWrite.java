package com.krzypio.gc.aco;

import java.io.*;
import java.util.Scanner;

public class ReadWrite {
    private String fileName;
    boolean syso = false;

    public ReadWrite(String fileName){
        this.fileName = fileName;
    }

    public void write(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
        if (syso) System.out.println("\tGraph saved in file: " +fileName);

    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }//readFromInputStream

    public void deleteLastChar(StringBuilder stringBuilder){
        if( stringBuilder.length() > 0 )
            stringBuilder.setLength( stringBuilder.length() - 1 );
    }

    public String read() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringBuilder.append(data);
                stringBuilder.append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        deleteLastChar(stringBuilder);
        if (syso) System.out.println("\tGraph loaded from file: " +fileName);
        return stringBuilder.toString();
    }//read
}
