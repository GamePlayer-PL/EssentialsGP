package io.github.gameplayerpl;

//import jdk.nashorn.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShellEssentials {
    //@Override
    public static boolean mkdir(String path) {
        File d = new File(path);
        if (d.exists()) {
            return false;
        } else {
            d.mkdirs();
            return true;
        }
    }

    //@Override
    public static boolean mkfile(String filepath) {
        File f = new File(filepath);
        if (f.exists()) {
            return false;
        } else {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    //@Override
    public static boolean mkfinefile(String path, String filename) {
        Boolean md = mkdir(path);
        Boolean mf = mkfile(path + "/" + filename);
        if (mf == true) {
            return true;
        } else {
            return false;
        }
    }

    //@Override
    public static void writefile(String path, String filename, String data, String type) {

        boolean mff = mkfinefile(path, filename);
        if (type == "console_txt") {
            try {
                PrintWriter ctxt = new PrintWriter(path + "/" + filename);
                ctxt.println(data);
                ctxt.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (type == "txt") {
            try {
                Files.write(Paths.get(path + "/" + filename), data.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type == "buffer") {
            int noOfLines = 10000;
            File file = new File(path + "/" + filename);
            FileWriter fr = null;
            BufferedWriter br = null;
            String dataWithNewLine = data + System.getProperty("line.separator");
            try {
                fr = new FileWriter(file);
                br = new BufferedWriter(fr);
                for (int i = noOfLines; i > 0; i--) {
                    br.write(dataWithNewLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (type == "txt2") {
            File file = new File(path + "/" + filename);
            FileWriter fr = null;
            try {
                fr = new FileWriter(file);
                fr.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //close resources
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readfile(String path, String filename, String type) {
        File dir = new File(path);
        if(!dir.exists()) {
            return null;
        } else {
            dir = new File(path+"/"+filename);
            if(!dir.exists()) {
                return null;
            } else {
                if(type == "txt") {
                    try {
                        File myObj = new File(path+"/"+filename);
                        Scanner myReader = new Scanner(myObj);
                        String file = "";
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            file = file + data + "\n";
                        }
                        myReader.close();
                        return file;
                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        return null;
                    }
                } else if(type == "console_txt") {
                    try {
                        File myObj = new File(path+"/"+filename);
                        Scanner myReader = new Scanner(myObj);
                        String file = "";
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            file = data;
                        }
                        myReader.close();
                        return file;
                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }
    }
    public static JSONArray readjson(String path, String filename) {
        File dir = new File(path+"/"+filename);
        if(!dir.exists()) {
            return null;
        } else {
            JSONArray i = new JSONArray();
            try {
                JSONParser j = new JSONParser();
                FileReader file = new FileReader(path+"/"+filename);
                Object obj = null;
                try {
                    obj = j.parse(file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JSONArray employeeList = (JSONArray) obj;
                i = employeeList;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return i;
        }
    }
    public static boolean writejson(String path, String filename, JSONArray data) {
        boolean mff = mkdir(path);
        try {
            FileWriter file = new FileWriter(path + "/" + filename);
            file.write(String.valueOf(data));
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
