/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany18424021_Baitap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    private static ArrayList<Users> ListUser = new ArrayList<Users>();
    private static HashMap<String, ArrayList<Student>> ListStudentByClass = new HashMap<String, ArrayList<Student>>();
    private static ArrayList<String> ListNameClass = new ArrayList<String>();
    private static HashMap<String, ArrayList<Subject>> TimeTable = new HashMap<String, ArrayList<Subject>>();
    private static HashMap<String, ArrayList<Point>> PointTable = new HashMap<String, ArrayList<Point>>();

    
    public static void main(String[] args) throws IOException {
        FileReader frListNameClass = new FileReader("Data/listNameClass.csv");
        BufferedReader brListNameClass = new BufferedReader(frListNameClass);
        FileWriter fw = new FileWriter("Data/out.txt");
        Scanner sc = new Scanner(System.in);
        
        Users AccountLogin = new Users();
        String User = "";
        String Pass = "";
        String CV = "";

        //login
        do{
            AccountLogin = Login();
        }while(AccountLogin == null);
        User = AccountLogin._USER;
        Pass = AccountLogin._PASS;
        CV = AccountLogin._CV;
        int choiceentry;
        
        //read ListNameClass
        while(true){
            String NameClass = brListNameClass.readLine();
            if (NameClass == null)
                break;
            
            ListNameClass.add(NameClass);
        }
        
        if(CV.equals("2")){
            do{
                System.out.println("Chọn tác vụ:");
                System.out.println("1 - Xem điểm:");
                System.out.println("2 - Đăng xuất:");
                System.out.println("3 - Đổi mật khẩu:");
                System.out.println();
                System.out.println("Enter \"1\", \"2\" or \"3\"....:");

                choiceentry = sc.nextInt();

                switch ( choiceentry ) {
                case 1:
                    ShowPointTableMSSV(User);
                    System.err.print("Nhấn 1 + Enter khi hoàn tất!!");
                    sc.next();
                    break;
                case 2:
                    System.err.print("Bạn muốn đăng xuất bây giờ?? 1:ok 2:cancel: ");
                    int choose = sc.nextInt();
                    if(choose == 1){
                        choiceentry = 13;
                    }
                    break;
                case 3:
                    ChangePassword(User,Pass,CV);
                    System.err.print("Nhấn 1 + Enter khi hoàn tất!!");
                    sc.next();
                    break;
                default:
                    AccountLogin = Login();
              }
            }while(choiceentry != 13);
        }
        
        //menu
        do{
            System.out.println("Chọn tác vụ:");
            System.out.println("1 - Import danh sách lớp (file CSV) vào hệ thống:");
            System.out.println("2 - Thêm 1 sinh viên:");
            System.out.println("3 - Cập nhật 1 sinh viên:");
            System.out.println("4 - Xóa 1 sinh viên:");
            System.out.println("5 - Xem danh sách lớp:");
            System.out.println("6 - Import thời khóa biểu (file CSV) vào hệ thống:");
            System.out.println("7 - Xem thời khóa biểu:");
            System.out.println("8 - Import bảng điểm(file CSV) vào hệ thống:");
            System.out.println("9 - Xem bảng điểm:");
            System.out.println("10 - Sửa điểm 1 sinh viên:");
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
                UpdateStudent();
                break;
            case 4:
                DeleteStudent();
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
                ImportTimeTable();
                break;
            case 7:
                Scanner scan1 = new Scanner(System.in);
                System.out.println("Nhập tên lớp: ");
                String tenlop1 = scan1.nextLine();
                boolean check1 = false;
                if(!"".equals(tenlop1) || tenlop1 != null){
                    for (int i = 0; i<ListNameClass.size();i++){
                        if(ListNameClass.get(i).equals(tenlop1)){
                            check = true;
                            ShowTimetable(ListNameClass.get(i));
                        } 
                    }
                    if(check1){
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
            case 8:
                ImportPointTable();
                System.err.print("Nhấn 1 + Enter khi hoàn tất!!");
                sc.next();
                break;
            case 9:
                Scanner scan2 = new Scanner(System.in);
                System.out.println("Nhập tên môn học: ");
                String tenmonhoc = scan2.nextLine();
                boolean check2 = false;
                if(!"".equals(tenmonhoc) || tenmonhoc != null){
                    for (int i = 0; i<PointTable.size();i++){
                           check2 = ShowPointTable(tenmonhoc);
                    }
                    if(check2){
                        System.err.print("Hoàn tất!!! Nhập 1 + ENTER để tiếp tục!!");
                        sc.next();
                        break;
                    }
                    System.err.print("Hoàn tất!!! Nhập 1 + ENTER để tiếp tục!!");
                    sc.next();
                    break;
                }else{
                    System.out.println("Chưa nhập tên môn học!!! Nhập 1 + ENTER để tiếp tục!!!");
                    sc.next();
                    break;
                }
            case 10:
                UpdatePointTable();
                System.err.print("Nhấn 1 + Enter khi hoàn tất!!");
                sc.next();
                break;
            case 11:
                System.err.print("Bạn muốn đăng xuất bây giờ?? 1:ok 2:cancel: ");
                int choose = sc.nextInt();
                if(choose == 1){
                    choiceentry = 13;
                }
                break;
            case 12:
                ChangePassword(User,Pass,CV);
                System.err.print("Nhấn 1 + Enter khi hoàn tất!!");
                sc.next();
                break;
            default:
                AccountLogin = Login();
          }
        }while(choiceentry < 13);

        // end project
//        fr.close();
        fw.close();
    }
    
    public static Users Login() throws IOException {
        FileReader fr = new FileReader("Data/account.csv");
        BufferedReader br = new BufferedReader(fr);
        
        while (true){
            Users us = new Users();
            String str = br.readLine();
            if (str == null)
                break;
            us._USER = str.split(",")[0]; //set _USER for student
            us._PASS = str.split(",")[1]; //set _PASS for student
            us._CV = str.split(",")[2];//set _CV for student
            ListUser.add(us);
        }
        
        String us = "";
        String pass = "";
        String cv = "";
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhập tên User: ");
        us = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        pass = sc.nextLine();
        
        for (int i=0; i<ListUser.size();i++) {
            if (ListUser.get(i)._USER.equals(us) && ListUser.get(i)._PASS.equals(pass)){
                cv = ListUser.get(i)._CV;
                return ListUser.get(i);
            }
        }
        fr.close();
        return null;
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
        FileWriter fw = new FileWriter((PathFile + "_tkb").replaceAll("csv", "txt"));
        
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Subject> LstSubject = new ArrayList<Subject>();
        String nameClass = br.readLine();
        while (true){
            Subject sj = new Subject();
            String str = br.readLine();
            if (str == null)
                break;

            sj._ID = str.split(",")[0]; //set ID for subject
            sj._NAME = str.split(",")[1]; //set Name for subject
            sj._ROOM = str.split(",")[2]; //set ROOM for subject
            LstSubject.add(sj);
            
            fw.write(str + '\n');

        }
        TimeTable.put(nameClass, LstSubject);
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
    public static void ShowTimetable(String Key) throws IOException {
        if (TimeTable.get(Key) == null){
            System.out.println("Lớp rỗng!!!!");
            return;
        }
        
        System.out.println("================" + "TIMETABLE" + "================");
        for (int i = 0; i< TimeTable.size(); i++){
            System.out.println(TimeTable.get(Key).get(i)._ID +  '\t' +
                    TimeTable.get(Key).get(i)._NAME + '\t' +
                    TimeTable.get(Key).get(i)._ROOM);
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

    // Delete Student
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
            FileWriter fw = new FileWriter("Data/" + ClassName + ".txt");
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

    // Update Student
    private static void UpdateStudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên lớp: ");
        String ClassName = sc.nextLine();
        if (null == ListStudentByClass.get(ClassName)){
            System.out.println("Lớp không tồn tại!!");
            return;
        }else{
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
            boolean check = false;
            for (int j=0;j<ListStudentByClass.get(ClassName).size();j++) {
                if (ListStudentByClass.get(ClassName).get(j)._MSSV.equals(st._MSSV)){
                    //Update student to list
                    ListStudentByClass.get(ClassName).get(j)._HOTEN = st._HOTEN;
                    ListStudentByClass.get(ClassName).get(j)._CMND = st._CMND;
                    ListStudentByClass.get(ClassName).get(j)._GT = st._GT;
                    check = true;
                }
            }
            if(!check){
                System.out.println("Sinh viên không tồn tại!!");
                return;
            }
            FileWriter fw = new FileWriter("Data/" + ClassName + ".txt");
            for (int i=0; i<ListStudentByClass.get(ClassName).size();i++) {                    
                    Student st1 = new Student();
                    st1._MSSV = ListStudentByClass.get(ClassName).get(i)._MSSV;
                    st1._HOTEN = ListStudentByClass.get(ClassName).get(i)._HOTEN;
                    st1._CMND = ListStudentByClass.get(ClassName).get(i)._CMND;
                    String strSex1;
                    if(ListStudentByClass.get(ClassName).get(i)._GT == true){
                        strSex1 = "nam";
                    }else {
                        strSex1 = "nữ";
                    }
                    fw.write(st1._MSSV + "," + st1._HOTEN + "," + strSex1 + "," + st1._CMND + '\n');
            }
            System.out.println("Cập nhật thành công!!");
            fw.close();
        }
    }

    private static void ImportPointTable() throws FileNotFoundException, IOException {
        int sldau=0, slrot=0;
        float tyledau=0,tylerot=0;
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập đường dẫn đến file: ");
        String PathFile = sc.nextLine();
        try {
            FileReader fr = new FileReader(PathFile);
            FileWriter fw = new FileWriter((PathFile + "_diem").replaceAll("csv", "txt"));

            BufferedReader br = new BufferedReader(fr);
            ArrayList<Point> LstPoint = new ArrayList<Point>();

            String nameSuject = br.readLine();
            while (true){
                Point p = new Point();
                String str = br.readLine();
                if (str == null)
                    break;

                p._MSSV = str.split(",")[0]; //set MSSV for student
                p._HOTEN = str.split(",")[1]; //set Name for student
                p._DiemGK = str.split(",")[2];//set _DiemGK for student
                p._DiemCK = str.split(",")[3]; //set _DiemCK for student
                p._DiemKhac = str.split(",")[4]; //set _DiemKhac for student
                p._DiemTong = str.split(",")[5]; //set _DiemTong for student
                if( Float.parseFloat(p._DiemTong) < 5){
                    p._Danhgia = "rớt";
                    slrot += 1;
                }else{
                    p._Danhgia = "đậu";
                    sldau += 1;
                }
                LstPoint.add(p);
                fw.write(str + '\n');
            }
            PointTable.put(nameSuject, LstPoint);
            tylerot = (slrot * 100) / LstPoint.size();
            tyledau = (sldau * 100) / LstPoint.size();
            fw.write("Tỷ lệ rớt:" + tylerot + '\n');
            fw.write("Tỷ lệ đậu:" + tyledau + '\n');
            fr.close();
            fw.close();
            System.out.println("Import thành công!!!");
        } catch (Exception e) {
            System.out.println("\n Error:" + e);
            return;
        }
    }

    private static void ChangePassword(String _User,String _Pass, String _CV) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("User mane: " + _User);
        System.out.println("Nhập pass cũ: ");
        String pass = sc.nextLine();
        if(_Pass.equals(pass)){
                System.out.println("Nhập pass mới: ");
                pass = sc.nextLine();
        }
        for (int i=0;i<ListUser.size();i++) {
            if(ListUser.get(i)._USER.equals(_User)){
                ListUser.get(i)._PASS = pass;
            }
        }
        FileWriter fw = new FileWriter("Data/account.csv");
        for (int j=0;j<ListUser.size();j++) {
            fw.write(ListUser.get(j)._USER + "," + ListUser.get(j)._PASS + "," + ListUser.get(j)._CV + '\n');
        }
        fw.close();
    }

    private static void UpdatePointTable() throws IOException {
        int sldau=0, slrot=0;
        float tyledau=0,tylerot=0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên môn học: ");
        String SubjectName = sc.nextLine();
        if (null == PointTable.get(SubjectName)){
            System.out.println("Môn học không có điểm!!");
            return;
        }else{
            Point p = new Point();
            System.out.print("Nhập MSSV: ");
            p._MSSV = sc.nextLine();
            System.out.print("Nhập họ tên sinh viên: ");
            p._HOTEN = sc.nextLine();
            System.out.print("Nhập điểm giữa kì: ");
            p._DiemGK = sc.nextLine();
            System.out.print("Nhập điểm cuối kì: ");
            p._DiemCK = sc.nextLine();
            System.out.print("Nhập điểm khác: ");
            p._DiemKhac = sc.nextLine();
            p._DiemTong = String.valueOf((Float.parseFloat(p._DiemGK) + Float.parseFloat(p._DiemCK) + Float.parseFloat(p._DiemKhac))/3);
            if( Float.parseFloat(p._DiemTong) < 5){
                    p._Danhgia = "rớt";
                }else{
                    p._Danhgia = "đậu";
                }
            boolean check = false;
            for (int j=0;j< PointTable.get(SubjectName).size();j++) {
                if (PointTable.get(SubjectName).get(j)._MSSV.equals(p._MSSV)){
                    //Update point to list
                    PointTable.get(SubjectName).get(j)._HOTEN = p._HOTEN;
                    PointTable.get(SubjectName).get(j)._DiemGK = p._DiemGK;
                    PointTable.get(SubjectName).get(j)._DiemCK = p._DiemCK;
                    PointTable.get(SubjectName).get(j)._DiemKhac = p._DiemKhac;
                    PointTable.get(SubjectName).get(j)._DiemTong = p._DiemTong;
                    PointTable.get(SubjectName).get(j)._Danhgia = p._Danhgia;
                    check = true;
                }
            }
            if(!check){
                System.out.println("Sinh viên không tồn tại!!");
                return;
            }
            FileWriter fw = new FileWriter("Data/" + SubjectName + "_diem" + ".txt");
            for (int i=0; i<PointTable.get(SubjectName).size();i++) {                    
                    Point p1 = new Point();
                    p1._MSSV = PointTable.get(SubjectName).get(i)._MSSV;
                    p1._HOTEN = PointTable.get(SubjectName).get(i)._HOTEN;
                    p1._DiemGK = PointTable.get(SubjectName).get(i)._DiemGK;
                    p1._DiemCK = PointTable.get(SubjectName).get(i)._DiemCK;
                    p1._DiemKhac = PointTable.get(SubjectName).get(i)._DiemKhac;
                    p1._DiemTong = PointTable.get(SubjectName).get(i)._DiemTong;
                    p1._Danhgia = PointTable.get(SubjectName).get(i)._Danhgia;
                 
                    fw.write(p1._MSSV + "," + p1._HOTEN + "," + p1._DiemGK + "," + p1._DiemCK + "," + p1._DiemKhac + "," + p1._DiemTong + "," + p1._Danhgia + '\n');
                    if( Float.parseFloat(p1._DiemTong) < 5){
                        slrot +=1;
                    }else{
                        sldau +=1;
                    }
            }
            
            tylerot = (slrot * 100) / PointTable.get(SubjectName).size();
            tyledau = (sldau * 100) / PointTable.get(SubjectName).size();
            fw.write("Tỷ lệ rớt:" + tylerot + '\n');
            fw.write("Tỷ lệ đậu:" + tyledau + '\n');
            System.out.println("Cập nhật thành công!!");
            fw.close();
        }
    }

    private static boolean ShowPointTable(String Key) {
        if (PointTable.get(Key) == null){
            System.out.println("Môn học không tồn tại!!!!");
            return false;
        }
        int sldau=0, slrot=0;
        float tyledau=0,tylerot=0;
        System.out.println("================" + "POINTTABLE" + "================");
        for (int i = 0; i< PointTable.get(Key).size(); i++){
            System.out.println(PointTable.get(Key).get(i)._MSSV+  '\t' +
                    PointTable.get(Key).get(i)._HOTEN + '\t' +
                    PointTable.get(Key).get(i)._DiemGK + '\t' +
                    PointTable.get(Key).get(i)._DiemCK + '\t' +
                    PointTable.get(Key).get(i)._DiemKhac + '\t' +
                    PointTable.get(Key).get(i)._DiemTong + '\t' +
                    PointTable.get(Key).get(i)._Danhgia);
            if( Float.parseFloat(PointTable.get(Key).get(i)._DiemTong) < 5){
                    slrot += 1;
                }else{
                    sldau += 1;
                }
        }
        tylerot = (slrot * 100) / PointTable.get(Key).size();
        tyledau = (sldau * 100) / PointTable.get(Key).size();
        System.out.println("Tỷ lệ rớt:" + tylerot);
        System.out.println("Tỷ lệ đậu:" + tyledau);
        return true;
    }

    private static void ShowPointTableMSSV(String User) {
        System.out.println("================" + User + "================");
        for (int i = 0; i< PointTable.size(); i++){
            for (int j=0;i<PointTable.get(i).size();j++){
                System.out.println(PointTable.get(i).get(j)._MSSV+  '\t' + PointTable.get(i).get(j)._HOTEN + '\t' + PointTable.get(i).get(j)._DiemGK + '\t' +
                    PointTable.get(i).get(j)._DiemCK + '\t' +
                    PointTable.get(i).get(j)._DiemKhac + '\t' +
                    PointTable.get(i).get(j)._DiemTong + '\t' +
                    PointTable.get(i).get(j)._Danhgia);
            } 
        }
    }
}
