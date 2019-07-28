/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany18424021_Baitap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author WINPC
 */
public class Main {
    private static HashMap<String, ArrayList<Student>> ListStudentByClass = new HashMap<String, ArrayList<Student>>();
    private static ArrayList<String> ListNameClass = new ArrayList<String>();
    private static ArrayList<Subject> TimeTable = new ArrayList<Subject>();
    
    public static void main(String[] args) throws IOException {
        FileReader frListNameClass = new FileReader("Data/listNameClass.csv");
        BufferedReader brListNameClass = new BufferedReader(frListNameClass);
        FileWriter fw = new FileWriter("Data/out.txt");
        Scanner sc = new Scanner(System.in);
        
        String AccountLogin = "";
        String User = "";
        String Pass = "";

        //login
//        do{
//            AccountLogin = Login();
//        }while(AccountLogin.equals(""));
//        User = AccountLogin.split(" ")[0];
//        Pass = AccountLogin.split(" ")[1];
        int choiceentry;
        
        //read ListNameClass
        while(true){
            String NameClass = brListNameClass.readLine();
            if (NameClass == null)
                break;
            
            ListNameClass.add(NameClass);
        }
        
        //menu
        do{
            System.out.println("Chọn tác vụ:");
            System.out.println("1 - Import danh sách lớp (file CSV) vào hệ thống:");
            System.out.println("2 - Thêm 1 sinh viên:");
            System.out.println("3 - Xóa 1 sinh viên:");
            System.out.println("4 - Import thời khóa biểu (file CSV) vào hệ thống:");
            System.out.println("5 - Xem danh sách lớp:");
            System.out.println("6 - Xem thời khóa biểu:");
            System.out.println("7 - Import bảng điểm(file CSV) vào hệ thống:");
            System.out.println("8 - Xem bảng điểm:");
            System.out.println("9 - Sửa điểm 1 sinh viên:");
            System.out.println("10 - Import thời khóa biểu (file CSV) vào hệ thống:");
            System.out.println("11 - Đăng xuất:");
            System.out.println("12 - Đổi mật khẩu:");
            System.out.println();
            System.out.println("Enter \"1\", \"2\" or \"3\"....:");

            choiceentry = sc.nextInt();
            
            switch ( choiceentry ) {
            case 1:
                ImportListStudent();
                break;
            case 2:
                AddStudent();
                break;
            case 3:
                DeleteStudent();
                break;
            case 4:
                ImportTimeTable();
                break;
            case 5:
                Scanner scan = new Scanner(System.in);
                System.out.println("Nhập tên lớp: ");
                String tenlop = scan.nextLine();
                boolean check = false;
                if(!"".equals(tenlop) || tenlop != null){
                    for (int i = 0; i<ListNameClass.size();i++){
                        if(ListNameClass.get(i).equals(tenlop)){
                            check = true;
                            ShowListStident(ListNameClass.get(i));
                        } 
                    }
                    if(check){
                        System.err.print("Hoàn tất!!! Nhập 1 + ENTER để tiếp tục!!");
                        sc.next();
                        break;
                    }
                    System.err.print("Lớp không tồn tại!! Nhập 1 + ENTER để tiếp tục");
                    sc.next();
                    break;
                }else{
                    System.out.println("Chưa nhập tên lớp!!! Vui lòng nhập!!!");
                    break;
                }
            case 6:
                ShowTimetable();
                System.err.print("Nhấn Enter khi hoàn tất!!");
                sc.next();
                break;
            default:
             // Làm gì đó tại đây ...
          }
        }while(choiceentry != 11);

        // end project
//        fr.close();
        fw.close();
    }
    
    public static String Login() throws IOException {
        FileReader fr = new FileReader("Data/accountTeacher.csv");
        BufferedReader br = new BufferedReader(fr);
        
        String us = "";
        String pass = "";
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhập tên User: ");
        us = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        pass = sc.nextLine();
        
        while(true){
            String Acc = br.readLine();
            if (Acc == null) 
                return "";
            
            if (Acc.split(" ")[0].equals(us) && Acc.split(" ")[1].equals(pass)){
                return us + " " + pass;
            }
                
        }
    }
    
    // import file List student
    public static void ImportListStudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập đường dẫn đến file: ");
        String PathFile = sc.nextLine();
        try {
            FileReader fr = new FileReader(PathFile);
            FileWriter fw = new FileWriter(PathFile.replaceAll("csv", "txt"));

            BufferedReader br = new BufferedReader(fr);
            ArrayList<Student> LstStudet = new ArrayList<Student>();

            String nameClass = br.readLine();
            while (true){
                Student st = new Student();
                String str = br.readLine();
                if (str == null)
                    break;

                st._MSSV = str.split(",")[0]; //set MSSV for student
                st._HOTEN = str.split(",")[1]; //set Name for student
                if (str.split(",")[2].equals("Nam"))
                    st._GT = true; //set true if is boy
                else
                    st._GT = false; //set true if is girl
                st._CMND = str.split(",")[3]; //set CMND for student
                LstStudet.add(st);

                fw.write(str + '\n');
            }
            ListStudentByClass.put(nameClass, LstStudet);
            fr.close();
            fw.close();
            System.out.println("Import thành công!!!");
        } catch (Exception e) {
            System.out.println("Error:" + e);
            return;
        }
        
        
    }
    
    // import file Timetable
    public static void ImportTimeTable() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập đường dẫn đến file: ");
        String PathFile = sc.nextLine();
        FileReader fr = new FileReader(PathFile);
        FileWriter fw = new FileWriter(PathFile.replaceAll("csv", "txt"));
        
        BufferedReader br = new BufferedReader(fr);
        
        while (true){
            Subject sj = new Subject();
            String str = br.readLine();
            if (str == null)
                break;

            sj._ID = str.split(",")[0]; //set ID for subject
            sj._NAME = str.split(",")[1]; //set Name for subject
            sj._ROOM = str.split(",")[2]; //set ROOM for subject
            TimeTable.add(sj);
            
            fw.write(str + '\n');

        }
        fr.close();
        fw.close();
        
    }
    
    // show List student
    public static void ShowListStident(String Key) throws IOException {
        if (ListStudentByClass.get(Key) == null){
            System.out.println("Lớp rỗng!!!!");
            return;
        }
            
        System.out.println("================" + Key + "================");
        for (int i = 0; i< ListStudentByClass.get(Key).size(); i++){
            System.out.println( (i+1)+"." + '\t' + ListStudentByClass.get(Key).get(i)._MSSV +  '\t' +
                    ListStudentByClass.get(Key).get(i)._HOTEN + '\t' +
                    (ListStudentByClass.get(Key).get(i)._GT == true ? "Nam" : "Nữ") + '\t' +
                    ListStudentByClass.get(Key).get(i)._CMND);
        }
    }
    
    // show timetable
    public static void ShowTimetable() throws IOException {
        System.out.println("================" + "TIMETABLE" + "================");
        for (int i = 0; i< TimeTable.size(); i++){
            System.out.println(TimeTable.get(i)._ID +  '\t' +
                    TimeTable.get(i)._NAME + '\t' +
                    TimeTable.get(i)._ROOM);
        }
    }
    
    // Add Student
    public static void AddStudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên lớp: ");
        String ClassName = sc.nextLine();
        if (ListStudentByClass.get(ClassName) == null){
            System.out.println("Lớp không tồn tại!!");
            return;
        }
        
        FileWriter fw = new FileWriter("Data/" + ClassName + ".txt", true);
        
        Student st = new Student();
        System.out.print("Nhập MSSV: ");
        st._MSSV = sc.nextLine();
        System.out.print("Nhập họ tên sinh viên: ");
        st._HOTEN = sc.nextLine();
        System.out.print("Nhập CMND: ");
        st._CMND = sc.nextLine();
        System.out.print("Nhập giới tính: ");
        String strSex = sc.nextLine();
        st._GT = (strSex.toLowerCase().equals("nam"));
        
        //Add student to list
        ListStudentByClass.get(ClassName).add(st);
        
        fw.write(st._MSSV + "," + st._HOTEN + "," + strSex + "," + st._CMND + '\n');
        
        fw.close();
        System.out.println("Thên thành công!!!");
    }

    private static void DeleteStudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên lớp: ");
        String ClassName = sc.nextLine();
        if (null == ListStudentByClass.get(ClassName)){
            System.out.println("Lớp không tồn tại!!");
            return;
        }else{
            System.out.print("Nhập MSSV: ");
            String mssv = sc.nextLine();
            boolean check = false;
            for (int j=0;j<ListStudentByClass.get(ClassName).size();j++) {
                if (ListStudentByClass.get(ClassName).get(j)._MSSV.equals(mssv)){
                    //Delete student to list
                    ListStudentByClass.get(ClassName).remove(j);
                    check = true;
                }
            }
            if(!check){
                System.out.println("Sinh viên không tồn tại!!");
                return;
            }
            FileWriter fw = new FileWriter("Data/" + ClassName + ".txt", true);
            for (int i=0; i<ListStudentByClass.get(ClassName).size();i++) {                    
                    Student st = new Student();
                    st._MSSV = ListStudentByClass.get(ClassName).get(i)._MSSV;
                    st._HOTEN = ListStudentByClass.get(ClassName).get(i)._HOTEN;
                    st._CMND = ListStudentByClass.get(ClassName).get(i)._CMND;
                    String strSex;
                    if(ListStudentByClass.get(ClassName).get(i)._GT == true){
                        strSex = "nam";
                    }else {
                        strSex = "nữ";
                    }
                    fw.write(st._MSSV + "," + st._HOTEN + "," + strSex + "," + st._CMND + '\n');
            }
            System.out.println("Xóa thành công!!");
            fw.close();
        }
    }
}
